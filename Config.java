package mods.nurseangel.drinkserver;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

/**
 * コンフィグファイル関連<br />
 * IDチェック、および他MODの存在チェック
 *
 */
public class Config {

	public static int DrinkServerID;
	public static int DrinkServerBCID;
	public static int DrinkServerFFMID;
	public static int DrinkServerInkID;

	// テスト
	public static boolean isTest = false;
	public static ItemStack itemStackBlockMaterial;

	/**
	 * コンストラクタ
	 *
	 * @param cfg
	 * @return
	 */
	public Config(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		readConfig(cfg);
	}

	/**
	 * コンフィグファイルから読み込み
	 * @param cfg
	 */
	private void readConfig(Configuration cfg) {
		int blockIdStart = 1250;

		try {
			cfg.load();
			// ブロックID
			DrinkServerID = cfg.getBlock("DrinkServerID", blockIdStart++).getInt();
			DrinkServerBCID = cfg.getBlock("DrinkServerBCID", blockIdStart++).getInt();
			DrinkServerFFMID = cfg.getBlock("DrinkServerFFMID", blockIdStart++).getInt();
			DrinkServerInkID = cfg.getBlock("DrinkServerInkID", blockIdStart++).getInt();

			isTest = cfg.get(Configuration.CATEGORY_GENERAL, "isTest", false, "Always false").getBoolean(false);

			// 素材ブロック
			int blockMaterial = cfg.get(Configuration.CATEGORY_GENERAL, "blockMaterial", 1, "0:ingotIron, 1:ingotGold").getInt();
			if (blockMaterial < 0) {
				blockMaterial = 0;
			} else if (blockMaterial > 5) {
				blockMaterial = 5;
			}
			setMaterialItemStack(blockMaterial);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, Reference.MOD_NAME + " configuration loadding failed");
		} finally {
			cfg.save();
		}
	}

	/**
	 * クラフト時の素材を取得
	 */
	private void setMaterialItemStack(int blockMaterial) {
		// 素材を設定
		Object[] blockMaterialList = { Item.ingotIron, Item.ingotGold, Block.blockIron, Block.blockGold, Item.diamond, Block.blockDiamond };
		if (blockMaterialList[blockMaterial] instanceof Item) {
			itemStackBlockMaterial = new ItemStack((Item) blockMaterialList[blockMaterial], 1);
		} else {
			itemStackBlockMaterial = new ItemStack((Block) blockMaterialList[blockMaterial], 1);
		}

	}

	/**
	 * BuildCraftが存在するかチェック<br />
	 * FMLCommonHandler/ModContainerあたりから取ってこれそうな気がしないでもないがよくわからん
	 */
	public boolean isExistBC() {
		try {
			Class.forName("buildcraft.BuildCraftEnergy");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * FFMが存在するかチェック
	 */
	public boolean isExistFFM() {
		try {
			Class.forName("forestry.api.core.ItemInterface");
			forestry.api.core.ItemInterface.getItem("bucketBiomass").getItem();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * BuildCraftが存在するかチェック
	 */
	public boolean isExistInk() {
		try {
			Class.forName("ayamitsu.ink.InkMod");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
