package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 水
 */
public class Water extends AbstractItem {

	{
		itemName = "Water";
		materialItem = Item.bucketWater;
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.heal(1);
	}

	// ガラス瓶
	@Override
	public void onBlockRightClickBottle(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		--itemstack.stackSize;
		entityplayer.inventory.addItemStackToInventory(new ItemStack(Item.potion));
	}
}
