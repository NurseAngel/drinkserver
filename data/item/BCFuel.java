package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * BCの金オイル
 */
public class BCFuel extends AbstractItem {

	{
		try {
			itemName = "Fuel";
			Item item = (Item) Class.forName("buildcraft.BuildCraftEnergy").getDeclaredField("bucketFuel").get(null);
			materialItem = new ItemStack(item);
		} catch (Exception e) {
		}
	}

	/**
	 * 素手で右クリックした
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.attackEntityFrom(DamageSource.lava, 19);
	}

}
