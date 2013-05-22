package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import thermalexpansion.api.item.ItemRegistry;

public class TEEnder extends AbstractItem {

	/**
	 * TEの液体エンダーパール
	 */
	{
		try {
			itemName = "Resonant Ender";
			materialItem = ItemRegistry.getItem("bucketEnder", 1);
		} catch (Exception e) {
		}
	}

	/**
	 * 右クリック
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		// 近所にワープ
		int oldX = (int) entityplayer.posX;
		int oldY = (int) entityplayer.posY;
		int oldZ = (int) entityplayer.posZ;

		int x = oldX - 8 + world.rand.nextInt(17);
		int y = oldY + world.rand.nextInt(8);
		int z = oldZ - 8 + world.rand.nextInt(17);
		EnderTeleportEvent event = new EnderTeleportEvent(entityplayer, x, y, z, 0);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return;
		}

		entityplayer.setPosition(x, y, z);
		entityplayer.worldObj.playSoundEffect(oldX, oldY, oldZ, "mob.endermen.portal", 1.0F, 1.0F);
		entityplayer.playSound("mob.endermen.portal", 1.0F, 1.0F);

	}

}
