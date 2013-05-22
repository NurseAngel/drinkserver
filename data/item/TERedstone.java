package mods.nurseangel.drinkserver.data.item;

import thermalexpansion.api.item.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class TERedstone extends AbstractItem {

	/**
	 * TEの液体レッドストーン
	 */
	{
		try {
			itemName = "Destabilized Redstone";
			materialItem = ItemRegistry.getItem("bucketRedstone",1);
		} catch (Exception e) {
		}
	}

	/**
	 * 右クリック
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		//何も起こらない?
	}
}
