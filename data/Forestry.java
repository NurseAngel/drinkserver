package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.FFMBiofuel;
import mods.nurseangel.drinkserver.data.item.FFMBiomass;
import mods.nurseangel.drinkserver.data.item.FFMShortMead;

public class Forestry extends AbstractServer {

	{
		blockName = "DrinkServerFFM";
		itemList.put(0, new FFMBiomass());
		itemList.put(1, new FFMBiofuel());

		/*
		 * TODO なんか動かないのでとりあえず保留
		 */
//		itemList.put(2, new FFMShortMead());
	}


	/**
	 * FFMが存在するかチェック
	 */
	public static boolean isExist() {
		try {
			Class.forName("forestry.api.core.ItemInterface");
			forestry.api.core.ItemInterface.getItem("bucketBiomass").getItem();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
