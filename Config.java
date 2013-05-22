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
	public static int DrinkServerLxpID;
	public static int DrinkServerMFR1ID;
	public static int DrinkServerMFR2ID;
	public static int DrinkServerTEID;
	public static int DrinkServerRCID;

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

		String commentV = "Vanilla: Water, Milk, Lava";
		String commentBC = "BuildCraft: Oil, Fuel";
		String commentFFM = "Forestry: Biomass, Ethanol";
		String commentInk = "InkMod: Ink";
		String commentLxp = "Immibis Liquid XP: LXP";
		String commentMFR = "MineFactoryReloaded: Sludge, Sewage, Mob Essense, MFR Biofuel, Meat, Pinkslime";
		String commentTE = "ThermalExpansion: Redstone, Glowstone, Ender";

		try {
			cfg.load();
			// ブロックID
			DrinkServerID = cfg.getBlock("DrinkServerID", blockIdStart++, commentV).getInt();
			DrinkServerBCID = cfg.getBlock("DrinkServerBCID", blockIdStart++, commentBC).getInt();
			DrinkServerFFMID = cfg.getBlock("DrinkServerFFMID", blockIdStart++, commentFFM).getInt();
			DrinkServerInkID = cfg.getBlock("DrinkServerInkID", blockIdStart++, commentInk).getInt();
			DrinkServerLxpID = cfg.getBlock("DrinkServerLxpID", blockIdStart++, commentLxp).getInt();
			DrinkServerMFR1ID = cfg.getBlock("DrinkServerMFR1ID", blockIdStart++, commentMFR).getInt();
			DrinkServerMFR2ID = cfg.getBlock("DrinkServerMFR2ID", blockIdStart++).getInt();
			DrinkServerTEID = cfg.getBlock("DrinkServerTEID", blockIdStart++, commentTE).getInt();
//			DrinkServerRCID = cfg.getBlock("DrinkServerRCID", blockIdStart++).getInt();

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


}
