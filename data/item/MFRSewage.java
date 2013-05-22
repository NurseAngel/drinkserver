package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class MFRSewage extends AbstractItem {
	/**
	 * MFRの汚水(茶色い方)
	 *
	 */
	{
		try {
			itemName = "Sewage";
			Item item = (Item) Class.forName("powercrystals.minefactoryreloaded.MineFactoryReloadedCore").getDeclaredField("sewageBucketItem").get(null);
			materialItem = new ItemStack(item);
		} catch (Exception e) {
		}
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 800, 1));
		entityplayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 800, 1));
	}

}
