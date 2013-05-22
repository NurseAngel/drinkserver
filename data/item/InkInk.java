package mods.nurseangel.drinkserver.data.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * InkModのイカスミバケツ<br />
 *
 * @author ayamitsu
 *
 */
public class InkInk extends AbstractItem {

	{
		try {
			itemName = "Ink";
			Item item = (Item) Class.forName("ayamitsu.ink.InkMod").getDeclaredField("bucketInk").get(null);
			materialItem = new ItemStack(item);
		} catch (Exception e) {
		}
	}

	@Override
	public void onBlockRightClickHand(World world, EntityPlayer entityplayer, ItemStack itemstack) {
		entityplayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 1));
	}

}
