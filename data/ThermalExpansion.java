package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.TEEnder;
import mods.nurseangel.drinkserver.data.item.TEGlowstone;
import mods.nurseangel.drinkserver.data.item.TERedstone;
import cpw.mods.fml.common.Loader;

public class ThermalExpansion extends AbstractServer {
	/**
	 * ThermalExpansion http://www.minecraftforum.net/topic/1297506-
	 */

	{
		blockName = "DrinkServerTE";
		itemList.put(0, new TERedstone());
		itemList.put(1, new TEGlowstone());
		itemList.put(2, new TEEnder());
		;
	}

	/**
	 * TEが存在するかチェック
	 */
	public static boolean isExist() {

		if (Loader.isModLoaded("ThermalExpansion")) {
			try {
				Class.forName("thermalexpansion.liquid.TELiquids");
			} catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;

	}

}
