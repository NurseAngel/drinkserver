package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MFRMeat extends AbstractItem {
	/**
	 * MFRの謎肉
	 */
	{
		try {
			itemName = "MFR Meat";
			Item item = (Item) Class.forName("powercrystals.minefactoryreloaded.MineFactoryReloadedCore").getDeclaredField("meatBucketItem").get(null);
			materialItem = new ItemStack(item);
		} catch (Exception e) {
		}
	}

	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		// TODO MFRの謎肉
		entityplayer.getFoodStats().addStats(1, 0);
	}

}
