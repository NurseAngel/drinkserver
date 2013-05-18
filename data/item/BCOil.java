package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * 黒オイル
 */
public class BCOil extends AbstractItem {

	{
		try {
			itemName = "Oil";
			materialItem = (Item) Class.forName("buildcraft.BuildCraftEnergy").getDeclaredField("bucketOil").get(null);
		} catch (Exception e) {
		}
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 400, 1));
	}

}
