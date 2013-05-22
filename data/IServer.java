package mods.nurseangel.drinkserver.data;

import java.util.LinkedHashMap;
import java.util.Map;

import mods.nurseangel.drinkserver.data.item.IServerItem;

/**
 * ドリンクサーバを表す
 */
public interface IServer {

	/**
	 * ブロック名を取得する
	 *
	 * @return
	 */
	public String getBlockName();

	/**
	 * ブロックに含まれるサーバのリスト
	 */
	public Map<Integer, IServerItem> getItemList();

	/**
	 * そのMODが存在するか否かをチェックする<br />
	 * でも何故かstaticなinterfaceは書けない。
	 *
	 * @return boolean 存在すればtrue
	 */
	// public static boolean isExist();
}
