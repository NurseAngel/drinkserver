package mods.nurseangel.drinkserver.block;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import mods.nurseangel.drinkserver.ModDrinkServer;
import mods.nurseangel.drinkserver.Reference;
import mods.nurseangel.drinkserver.data.IServer;
import mods.nurseangel.drinkserver.data.item.IServerItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ドリンクサーバクラス
 *
 * メタデータの使い方<br />
 * 0-3:ひとつめのブロック、数値は正面方向を表す<br />
 * 4-7:ふたつめ 8-11:みっつめ 12-15:よっつめ
 *
 */
public class BlockDrinkServer extends Block {

	// ドリンクサーバのデータ全部
	private IServer drinkServerData;

	/**
	 * コンストラクタ
	 *
	 * @param int BlockID
	 * @param IServer
	 *            素材とか全部入ってるの
	 */
	public BlockDrinkServer(int blockID, IServer serverData) {

		super(blockID, Material.iron);

		setHardness(2.5F);
		setLightValue(1.0F);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		setDrinkServerData(serverData);
	}

	/**
	 * データをセットするだけ
	 *
	 * @param serverData
	 */
	private void setDrinkServerData(IServer serverData) {
		this.drinkServerData = serverData;
	}

	/**
	 * ブロックが右クリックされたときに呼ばれる
	 *
	 * @param World
	 * @param int x,y,z (Yが上)
	 * @param EntityPlayer
	 *            プレイヤー
	 */
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		// メタデータを取得
		int metaData = world.getBlockMetadata(i, j, k);
		int metaNum = metaData / 4;
		//message("metaData:" + Integer.toString(metaData) + "metaNum:" + Integer.toString(metaNum));

		// ブロックに対応したアイテムデータ
		IServerItem dataItem = drinkServerData.getItemList().get(metaNum);

		// 現在の手持ちアイテムを取得
		ItemStack equipedItem = entityplayer.getCurrentEquippedItem();

		// アイテムによってそれぞれ実行
		if (equipedItem != null) {
			if (equipedItem.getItem() == Item.bucketEmpty) {
				// 空バケツだった
				dataItem.onBlockRightClickBucket(world, entityplayer, equipedItem);
			} else if (equipedItem.getItem() == Item.glassBottle) {
				// ガラス瓶だった
				dataItem.onBlockRightClickBottle(world, entityplayer, equipedItem);
			} else {
				// それ以外のアイテムだった
				dataItem.onBlockRightClickHand(world, entityplayer, equipedItem);
			}
		} else {
			// 素手
			dataItem.onBlockRightClickHand(world, entityplayer, equipedItem);
		}

		return true;
	}

	/**
	 * クリエイティブタブ
	 */
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < drinkServerData.getItemList().size(); i++) {
			par3List.add(new ItemStack(par1, 1, i * 4));
		}
	}

	Icon[] icon = new Icon[30];

	/**
	 * 使用するアイコンをセット
	 *
	 * @param iconRegister
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		int loop = 0;
		for (IServerItem serverItem : drinkServerData.getItemList().values()) {

			// 素材名からアイコンファイル名を作成
			String texturePath = Reference.TEXTURE_PATH + serverItem.getName() + "Server";

			// 下上東西南北:012345 / 6-11 / 12-17 / 18-23
			for (int i = 0; i <= 5; i++) {
				String iconPath = texturePath + i;
				icon[loop * 6 + i] = iconRegister.registerIcon(iconPath);
			}

			loop++;
		}
	}

	/**
	 * アイコンを取得
	 *
	 * @param 取得する方角
	 * @param メタデータ
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int direction, int metadata) {
		// 取得時に足す基準値
		int multiply = metadata / 4;

		// 設置時の方角
		int sideMeta = metadata % 4;

		// 設置時の方角と取得する方角を合わせて返す
		int iconInt = iconDirection[sideMeta][direction];
		return icon[(multiply * 6) + iconInt];

	}

	/**
	 * 設置時の方角とアイコン取得時の方角を合わせる用のデータ<br />
	 * なんかうまいことできるんだろうけどよくわからん<br />
	 */
	static int iconDirection[][] = { { 0, 1, 4, 2, 5, 3 }, { 0, 1, 4, 3, 2, 5 }, { 0, 1, 2, 4, 3, 5 }, { 0, 1, 3, 5, 4, 2 } };

	/**
	 * ブロックを設置した
	 *
	 * @param World
	 * @param int i,j,k
	 * @param EntityLiving
	 *            基本的にプレイヤー
	 */
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack par6ItemStack) {
		if (!(entityliving instanceof EntityPlayer)) {
			return;
		}

		/**
		 * 向きはForgeDirectionで取得できるが、値は0-6になる。 0-4が必要なので手動で。 どうにかできるとは思うがよくわからん。
		 */

		// これで向きを検知できるようだ 値は0～3
		int l = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 2.5D) & 3;

		// アイテムのメタデータを取得 値は0、4、8、12
		EntityPlayer entityplayer = (EntityPlayer) entityliving;
		int metadata = entityplayer.getCurrentEquippedItem().getItemDamageForDisplay();

		// ブロックにメタデータを設定
		world.setBlockMetadataWithNotify(i, j, k, metadata + l, 1);

		message("set block l:" + l + "  meta:" + Integer.toString(l));
	}

	/**
	 * ドロップするアイテムのメタデータ
	 *
	 * @param int メタデータ
	 * @return int ダメージ値
	 */

	@Override
	public int damageDropped(int par1) {
		// 0,4,8,12
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