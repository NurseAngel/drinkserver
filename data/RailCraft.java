package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.RCCreosoteOil;

public class RailCraft extends AbstractServer {
	{
		blockName = "DrinkServerRC";
		itemList.put(0, new RCCreosoteOil());

	}

	/**
	 * RailCraftが存在するかチェック
	 */
	public static boolean isExist() {
		try {
			Class.forName("mods.railcraft.common.liquids");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
