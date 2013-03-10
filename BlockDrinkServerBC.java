package nurseangel.DrinkServer;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * BuildCraft
 *
 * 0:黒オイル 4:金オイル
 */
public class BlockDrinkServerBC extends BlockDrinkServerAbstract {
	// 素材アイテム
	public static Item[] itemType;

	public BlockDrinkServerBC(int blockID, int textureStartCoord, Item[] itemType) {
		super(blockID, textureStartCoord, 2);
		this.itemType = itemType;
	}

	/**
	 * ブロックが右クリックされたときに呼ばれる
	 *
	 * @param World
	 *
	 * @param int x,y,z (Yが上)
	 *
	 * @param EntityPlayer
	 *            プレイヤー
	 */
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		// メタデータを取得
		int metaData = world.getBlockMetadata(i, j, k);
		// テクスチャ番号
		int metaNum = metaData / 4;
		message("metaData:" + Integer.toString(metaData) + "metaNum:" + Integer.toString(metaNum));
		// 現在の手持ちアイテムが空バケツであれば掬う
		ItemStack equipedItem = entityplayer.getCurrentEquippedItem();

		if ((equipedItem != null) && (equipedItem.getItem() == Item.bucketEmpty)) {
			--equipedItem.stackSize;
			entityplayer.inventory.addItemStackToInventory(new ItemStack(itemType[metaNum], 1, 0));
			return true;
		}

		// それ以外であれば

		// 黒
		if (metaNum == 0) {
			entityplayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 400, 1));
			return true;
		}

		// 金
		if (metaNum == 1) {
			entityplayer.attackEntityFrom(DamageSource.lava, 19);
			return true;
		}

		return true;
	}

	/**
	 * クリエイティブタブ
	 */
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < itemType.length; i++) {
			par3List.add(new ItemStack(par1, 1, i * 4));
		}
	}
}