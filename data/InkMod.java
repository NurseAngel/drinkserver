package mods.nurseangel.drinkserver.data;

import mods.nurseangel.drinkserver.data.item.InkInk;

public class InkMod extends AbstractServer {

	{
		blockName = "DrinkServerInk";
		itemList.put(0, new InkInk());
	}
}
