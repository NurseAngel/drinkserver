package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.InkInk;

public class InkMod extends AbstractServer {
/**
 * InkMod<br />
 * http://forum.minecraftuser.jp/viewtopic.php?f=13&t=3397
 */
	{
		blockName = "DrinkServerInk";
		itemList.put(0, new InkInk());
	}


	/**
	 * InkModが存在するかチェック
	 */
	public static boolean isExist() {
		try {
			Class.forName("ayamitsu.ink.InkMod");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
