package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.ImmibisLxpBucket;

public class ImmibisLxp extends AbstractServer {

	/**
	 * IMMIBIS'S Liquid XP
	 * http://www.minecraftforum.net/topic/1001131-
	 */
	{
		blockName = "DrinkServerLxp";
		itemList.put(0, new ImmibisLxpBucket());
	}


	/**
	 * Liquid Lxpが存在するかチェック
	 */
	public static boolean isExist() {
		try {
			Class.forName("mods.immibis.lxp.LiquidXPMod");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
