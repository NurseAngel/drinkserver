package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * FFMのバイオ燃料
 */
public class FFMBiomass extends AbstractItem {

	{
		try {
			itemName = "Biomass";
			materialItem = forestry.api.core.ItemInterface.getItem("bucketBiomass").getItem();
		} catch (Exception e) {
		}
	}

	/**
	 * 右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		/*
		 * いまいちそれっぽい効果が見当たらないので適当
		 */
		entityplayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 1));

	}

}
