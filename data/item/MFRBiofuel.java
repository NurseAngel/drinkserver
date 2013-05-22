package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class MFRBiofuel extends AbstractItem {

	/**
	 * MFRのバイオ燃料<br />
	 * FFMのとは別
	 */
	{
		try {
			itemName = "MFR Biofuel";
			Item item = (Item) Class.forName("powercrystals.minefactoryreloaded.MineFactoryReloadedCore").getDeclaredField("bioFuelBucketItem").get(null);
			materialItem = new ItemStack(item);
		} catch (Exception e) {
		}
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 800, 1));
		entityplayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 800, 1));
	}

}
