package nurseangel.DrinkServer;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * ドリンクサーバのベースクラス<br />
 * blockActivatedを実装
 *
 * メタデータの使い方<br />
 * 0-3:ひとつめのブロック、数値は正面方向を表す<br />
 * 4-7:ふたつめ 8-11:みっつめ 12-15:よっつめ
 *
 */
public abstract class BlockDrinkServerAbstract extends Block {
	//
	/** テクスチャの開始番号列 */
	private int textureStart;
	/** そのBlockIDで使うBlockの数 */
	private int blockNum;

	/**
	 * コンストラクタ
	 *
	 * @param int BlockID
	 * @param int imageServerTop テクスチャの使用する列
	 * @param int 幾つブロックが追加されるか
	 */
	public BlockDrinkServerAbstract(int blockID, int textureStartCoord, int blockNum) {
		super(blockID, textureStartCoord * 16, Material.iron);
		setTextureFile(Reference.TEXTURE_FILE);
		setHardness(2.5F);
		setLightValue(1.0F);
		setRequiresSelfNotify();
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.textureStart = textureStartCoord * 16;
		this.blockNum = blockNum;
	}

	/**
	 * 方向およびメタデータからテクスチャを返す
	 *
	 * @param int 方向 下上東西南北:012345
	 * @param int メタデータ
	 */
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {

		int returnTexture = textureStart;
		int sideMeta = meta % 4;

		// ブロック種別による補正
		if (meta >= 8) {
			if (meta >= 12) {
				returnTexture += 23;
			} else {
				returnTexture += 16;
			}
		} else {
			if (meta >= 4) {
				returnTexture += 7;
			}
		}

		// 下
		if (side == 0) {
			return returnTexture + 5;
		}
		// 上
		if (side == 1) {
			return returnTexture + 4;
		}

		// 前後左右
		// 絶対ここなんかもっと楽な書き方があるだろ
		if (sideMeta == 0) {
			if (side == 2) {
				return returnTexture + 2;
			} else if (side == 3) {
				return returnTexture;
			} else if (side == 4) {
				return returnTexture + 3;
			} else if (side == 5) {
				return returnTexture + 1;
			}
		}

		if (sideMeta == 1) {
			if (side == 2) {
				return returnTexture + 3;
			} else if (side == 3) {
				return returnTexture + 1;
			} else if (side == 4) {
				return returnTexture;
			} else if (side == 5) {
				return returnTexture + 2;
			}
		}
		if (sideMeta == 2) {
			if (side == 2) {
				return returnTexture;
			} else if (side == 3) {
				return returnTexture + 2;
			} else if (side == 4) {
				return returnTexture + 1;
			} else if (side == 5) {
				return returnTexture + 3;
			}
		}
		if (sideMeta == 3) {
			if (side == 2) {
				return returnTexture + 1;
			} else if (side == 3) {
				return returnTexture + 3;
			} else if (side == 4) {
				return returnTexture + 2;
			} else if (side == 5) {
				return returnTexture;
			}
		}

		// それ以外(無い)
		return returnTexture;
	}

	/**
	 * ブロックを右クリックした<br />
	 * 空バケツであれば掬うあたりは共通化できそうだけどめんどいからまあいいや
	 *
	 * @param World
	 * @param int x,y,z (Yが上)
	 * @param EntityPlayer
	 *            プレイヤー
	 * @param int ?
	 * @param float プレイヤーとの距離？
	 */
	@Override
	public abstract boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9);

	/**
	 * ブロックを設置した
	 *
	 * @param World
	 * @param int i,j,k
	 * @param EntityLiving
	 *            基本的にプレイヤー
	 */
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving) {
		if (!(entityliving instanceof EntityPlayer)) {
			return;
		}

		// これで向きを検知できるようだ 値は0～3
		int l = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 2.5D) & 3;

		// アイテムのメタデータを取得 値は0、4、8、12
		EntityPlayer entityplayer = (EntityPlayer) entityliving;
		int metadata = entityplayer.getCurrentEquippedItem().getItemDamageForDisplay();
		// ブロックにメタデータを設定
		world.setBlockMetadataWithNotify(i, j, k, metadata + l);
		message("set block meta::" + Integer.toString(l));
	}

	/**
	 * ブロックを破壊した
	 *
	 * @param World
	 * @param EntityPlayer
	 *            プレイヤー
	 * @param int i,j,k
	 * @param int メタデータ
	 */
	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
		// メタデータは0、4、8、12に収束させる
		int metadata = l / 4 * 4;
		message("harvest block meta:" + Integer.toString(metadata));
		super.harvestBlock(world, entityplayer, i, j, k, metadata);
	}

	/**
	 * ドロップするアイテムのメタデータ
	 *
	 * @param int メタデータ
	 * @return int ダメージ値
	 */

	@Override
	public int damageDropped(int par1) {
		// TNTとかでドロップするとharvestBlockを経由しない
		return par1 / 4 * 4;
	}

	/**
	 * 画面にメッセージを表示。テスト時のみ
	 *
	 * @param str
	 */
	protected void message(String str) {
		if (ModDrinkServer.isTest) {
			if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
				FMLClientHandler.instance().getClient().thePlayer.addChatMessage(str);
			}
		}
	}
}