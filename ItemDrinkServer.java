package mods.nurseangel.drinkserver;

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
	 * ブロック設置時のメタデータ。指定しないと0
	 *
	 * @param int アイテムのダメージ値
	 * @return int メタデータ
	 */
	@Override
	public int getMetadata(int i) {
		return i;
	}

	/**
	 * 内部的ブロックの名前を返す
	 *
	 * @param ItenStack
	 * @return String
	 */
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		return (new StringBuilder()).append(getUnlocalizedName()).append(i).toString();

	}

}
