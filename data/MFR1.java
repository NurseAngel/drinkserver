package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.MFRBiofuel;
import mods.nurseangel.drinkserver.data.item.MFRMobEssense;
import mods.nurseangel.drinkserver.data.item.MFRSewage;
import mods.nurseangel.drinkserver.data.item.MFRSludge;

/**
 * MFRその1<br />
 * 汚泥、汚水、モブエッセンス、MFRのバイオ燃料
 *
 */
public class MFR1 extends AbstractServer {

	{
		blockName = "DrinkServerMFR1";
		itemList.put(0, new MFRSludge());
		itemList.put(1, new MFRSewage());
		itemList.put(2, new MFRMobEssense());
		itemList.put(3, new MFRBiofuel());
	}

	/**
	 * MFRが存在するかチェック<br />
	 */
	public static boolean isExist() {
		try {
			Class.forName("powercrystals.minefactoryreloaded.MineFactoryReloadedCore");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
