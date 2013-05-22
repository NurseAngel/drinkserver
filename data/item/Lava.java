package mods.nurseangel.drinkserver.data.item;

import thermalexpansion.api.item.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Lava extends AbstractItem {

	{
		itemName = "Lava";
		materialItem = new ItemStack(Item.bucketLava);
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.setFire(10);
	}

}
