package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * FFMのバイオエタノール
 *
 */
public class FFMBiofuel extends AbstractItem {

	{
		try {
			itemName = "Biofuel";
			materialItem = forestry.api.core.ItemInterface.getItem("bucketBiofuel").getItem();
		} catch (Exception e) {
		}
	}

	/**
	 * それメタノールや
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 400, 1));
	}

}
