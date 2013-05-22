package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.BCFuel;
import mods.nurseangel.drinkserver.data.item.BCOil;

/**
 * BCのドリンクサーバ
 *
 */
public class BuildCraft extends AbstractServer {

	{
		blockName = "DrinkServerBC";
		itemList.put(0, new BCOil());
		itemList.put(1, new BCFuel());
	}

	/**
	 * BuildCraftが存在するかチェック<br />
	 * FMLCommonHandler/ModContainerあたりから取ってこれそうな気がしないでもないがよくわからん
	 */
	public static boolean isExist() {
		try {
			Class.forName("buildcraft.BuildCraftEnergy");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
