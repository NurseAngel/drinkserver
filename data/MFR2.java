package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.MFRMeat;
import mods.nurseangel.drinkserver.data.item.MFRPinkslime;

/**
 * MFRその2<br />
 * 謎肉、ピンクスライム
 *
 */
public class MFR2 extends AbstractServer {

	{
		blockName = "DrinkServerMFR2";
		itemList.put(0, new MFRMeat());
		itemList.put(1, new MFRPinkslime());
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
