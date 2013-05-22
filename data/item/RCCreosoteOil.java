package mods.nurseangel.drinkserver.data.item;

import java.lang.reflect.Field;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RCCreosoteOil extends AbstractItem {

	/**
	 * RailCraftのクレオソート油
	 */
	{
		try {
			itemName = "Creosote Oil";

			// privateなので無理矢理ゲット
			Field field = Class.forName("mods.railcraft.common.liquids").getDeclaredField("itemCreosoteOilBucket");
			field.setAccessible(true);
			Item item = (Item) field.get(null);
			materialItem = new ItemStack(item);

			// ついでにボトルも取っておく
			Field field2 = Class.forName("mods.railcraft.common.liquids").getDeclaredField("itemCreosoteOilBottle");
			field2.setAccessible(true);
			Item item2 = (Item) field2.get(null);
			itemCreosoteOilBottle = new ItemStack(item2);

		} catch (Exception e) {
		}
	}

	private ItemStack itemCreosoteOilBottle;

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		// TODO なにか
	}

	// ガラス瓶
	@Override
	public void onBlockRightClickBottle(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		changeItem(entityplayer, itemstack, itemCreosoteOilBottle);
	}
}
