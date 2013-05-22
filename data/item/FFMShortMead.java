package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FFMShortMead extends AbstractItem {
	{
		try {
			itemName = "ShortMead";
			materialItem = forestry.api.core.ItemInterface.getItem("shortMead");
		} catch (Exception e) {
		}
	}

	// ガラス瓶しかない
	@Override
	public void onBlockRightClickBottle(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		changeItem(entityplayer, itemstack, materialItem);
	}

	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 400, 1));
	}

	// バケツは素手と同じ
	@Override
	public void onBlockRightClickBucket(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		onBlockRightClickHand(world, entityplayer, itemstack);
	}

}
