package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.ItemDrinkServer;
import mods.nurseangel.drinkserver.block.BlockDrinkServer;
import mods.nurseangel.drinkserver.data.item.IServerItem;
import mods.nurseangel.drinkserver.data.item.Lava;
import mods.nurseangel.drinkserver.data.item.Milk;
import mods.nurseangel.drinkserver.data.item.Water;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * バニラのドリンクサーバ
 *
 * 0:水 4:牛乳 8:溶岩
 */
public class Vanilla extends AbstractServer {

	{
		blockName = "DrinkServer";
		itemList.put(0, new Water());
		itemList.put(1, new Milk());
		itemList.put(2, new Lava());
	}

	public static boolean isExist() {
		return true;
	}
}
