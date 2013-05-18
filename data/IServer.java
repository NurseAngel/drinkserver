package mods.nurseangel.drinkserver.data;

import java.util.LinkedHashMap;
import java.util.Map;

import mods.nurseangel.drinkserver.data.item.IServerItem;

/**
 * ドリンクサーバを表すマーカーインターフェース
 */
public interface IServer {

	public String getBlockName();
	public Map<Integer, IServerItem> getItemList();
}
