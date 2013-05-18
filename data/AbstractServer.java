package mods.nurseangel.drinkserver.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mods.nurseangel.drinkserver.data.item.IServerItem;

public class AbstractServer implements IServer {

	/**
	 * ブロック名
	 */
	public String blockName;

	@Override
	public String getBlockName() {
		return blockName;
	}

	/**
	 * ひとつのブロックで扱うアイテムのリスト
	 */
	Map<Integer, IServerItem> itemList = new LinkedHashMap<Integer, IServerItem>(4);

	@Override
	public Map<Integer, IServerItem> getItemList() {
		return itemList;
	}


}
