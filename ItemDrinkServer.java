package nurseangel.DrinkServer;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDrinkServer extends ItemBlock {
	/**
	 * コンストラクタ
	 *
	 * @param アイテムID
	 */
	public ItemDrinkServer(int i) {
		super(i);
		setMaxStackSize(1);
		setMaxDamage(0);
		setHasSubtypes(true);
		setNoRepair();
	}

	/**
	 * メタデータ(=subType)を返す <br />
	 * ブロックの設置時に使用
	 */
	@Override
	public int getMetadata(int i) {
		return i;
	}

	/**
	 * 内部的ブロックの名前を返す<br />
	 * メタデータごとに名前を設定する場合必須<br />
	 * getLocalItemNameのほうはとりあえず置いてるけど不明
	 *
	 */
	@Override
	public String getItemNameIS(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		return (new StringBuilder()).append(getItemName()).append(i).toString();
	}

	@Override
	public String getLocalItemName(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		return (new StringBuilder()).append(getItemName()).append(i).toString();
	}
}
