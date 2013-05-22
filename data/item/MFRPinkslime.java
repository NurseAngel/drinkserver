package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MFRPinkslime extends AbstractItem {
	/**
	 * MFRのピンクスライム
	 */
	{
		try {
			itemName = "MFR Pinkslime";
			Item item = (Item) Class.forName("powercrystals.minefactoryreloaded.MineFactoryReloadedCore").getDeclaredField("pinkSlimeBucketItem").get(null);
			materialItem = new ItemStack(item);
		} catch (Exception e) {
		}
	}

	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		// TODO 効果がわかったら対応

	}

}
