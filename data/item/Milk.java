package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Milk extends AbstractItem {

	{
		itemName = "Milk";
		materialItem = Item.bucketMilk;
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		if (!world.isRemote) {
			entityplayer.curePotionEffects(new ItemStack(Item.bucketMilk, 1));
		}

	}

}
