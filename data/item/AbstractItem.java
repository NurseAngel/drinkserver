package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 自販機それぞれのアイテムについて
 */
abstract public class AbstractItem implements IServerItem {

	/**
	 * 名前。<br />
	 * 継承先で必ず定義しないといけない定数、みたいなのってないんかな?
	 */
	public String itemName;

	/**
	 * クラフト素材<br />
	 * これも継承先で必ず実装が必要
	 */
	public ItemStack materialItem;

	/**
	 * 名前を返す
	 */
	@Override
	public String getName() {
		return itemName;
	}

	/**
	 * 素材を返す
	 */
	@Override
	public ItemStack getMaterial() {
		return materialItem;
	}

	// 素手
	@Override
	public abstract void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack);

	// ガラス瓶
	@Override
	public void onBlockRightClickBottle(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		// デフォは素手。必要であればOverrideする
		onBlockRightClickHand(world, entityplayer, itemstack);
	}

	// バケツ
	@Override
	public void onBlockRightClickBucket(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		/**
		 * デフォは手持ちアイテムを一個減らし、素材アイテムを一個追加する。<br />
		 * 必要であればOverrideする。<br />
		 */
		changeItem(entityplayer, itemstack, getMaterial());
	}

	@Override
	public void changeItem(EntityPlayer entityplayer, ItemStack minusItem, ItemStack plusItem) {

		if (minusItem == null) {
			return;
		}

		// +するItemStackはそのまま使うと何故か使えない謎アイテムになってしまうので、いったん作り直す。 個数に0が入ってるとなるっぽい
		ItemStack hoge = new ItemStack(plusItem.getItem(), 1, plusItem.getItemDamage());

		if (--minusItem.stackSize <= 0) {
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, hoge);
		} else if (!entityplayer.inventory.addItemStackToInventory(hoge)) {
			entityplayer.dropPlayerItem(hoge);
		}
	}
}
