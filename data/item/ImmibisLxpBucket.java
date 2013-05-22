package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ImmibisLxpBucket extends AbstractItem {

	{
		try {
			itemName = "Liquid XP";
			Item item = (Item) Class.forName("mods.immibis.lxp.LiquidXPMod").getDeclaredField("bucket").get(null);
			materialItem = new ItemStack(item);
		} catch (Exception e) {
		}
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addExperience(1);
	}

}
