package mods.nurseangel.drinkserver.data.item;

import thermalexpansion.api.item.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class TEGlowstone extends AbstractItem {
	/**
	 * TEの液体グロウストーン
	 */
	{
		try {
			itemName = "Energized Glowstone";
			materialItem = ItemRegistry.getItem("bucketGlowstone",1);
		} catch (Exception e) {
		}
	}

	/**
	 * 右クリック
	 */
	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		// 速度、ジャンプブースト5秒
		entityplayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 1));
		entityplayer.addPotionEffect(new PotionEffect(Potion.jump.id, 100, 1));
	}

}
