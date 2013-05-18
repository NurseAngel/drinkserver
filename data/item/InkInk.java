package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * InkModのイカスミバケツ<br />
 * http://forum.minecraftuser.jp/viewtopic.php?f=13&t=3397
 *
 * @author ayamitsu
 *
 */
public class InkInk extends AbstractItem {

	{
		try {
			itemName = "Ink";
			materialItem = (Item) Class.forName("ayamitsu.ink.InkMod").getDeclaredField("bucketInk").get(null);
		} catch (Exception e) {
		}
	}

	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 300, 1));
	}

}
