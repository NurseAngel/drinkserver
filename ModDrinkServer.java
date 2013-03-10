package nurseangel.DrinkServer;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import nurseangel.DrinkServer.proxy.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.modloader.ModLoaderModContainer;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModDrinkServer {
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	// IDとか
	public static int DrinkServerID;
	public static int DrinkServerBCID;
	public static int DrinkServerFFMID;
	public static int DrinkServerInkID;
	public static int blockMaterial;
	public static boolean isTest = false;

	/**
	 * コンストラクタ的なもの
	 *
	 * @param event
	 */
	@Mod.PreInit
	public void myPreInitMethod(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		int itemIdStart = 1250;

		try {
			cfg.load();
			DrinkServerID = cfg.getBlock("DrinkServerID", itemIdStart++).getInt();
			DrinkServerBCID = cfg.getBlock("DrinkServerBCID", itemIdStart++).getInt();
			DrinkServerFFMID = cfg.getBlock("DrinkServerFFMID", itemIdStart++).getInt();
			DrinkServerInkID = cfg.getBlock("DrinkServerInkID", itemIdStart++).getInt();

			blockMaterial = cfg.get(Configuration.CATEGORY_GENERAL, "blockMaterial", 1, "0:ingotIron, 1:ingotGold").getInt();
			isTest = cfg.get(Configuration.CATEGORY_GENERAL, "isTest", false, "Always false").getBoolean(false);

			if (blockMaterial < 0) {
				blockMaterial = 0;
			} else if (blockMaterial > 3) {
				blockMaterial = 3;
			}
		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " configuration loadding failed");
		} finally {
			cfg.save();
		}

		proxy.registerRenderers();
	}

	static ItemStack itemStackBlockMaterial;

	/**
	 * load()なもの
	 *
	 * @param event
	 */
	@Mod.Init
	public void myInitMethod(FMLInitializationEvent event) {
		// 素材を設定
		Object[] blockMaterialList = { Item.ingotIron, Item.ingotGold, Block.blockSteel, Block.blockGold, Block.blockDiamond };

		if (blockMaterialList[blockMaterial] instanceof Item) {
			itemStackBlockMaterial = new ItemStack((Item) blockMaterialList[blockMaterial], 1);
		} else {
			itemStackBlockMaterial = new ItemStack((Block) blockMaterialList[blockMaterial], 1);
		}

		// バニラのドリンクサーバ追加
		this.addDrinkServer();
	}

	/**
	 * modsLoaded()的なもの
	 *
	 * @param event
	 */
	@Mod.PostInit
	public void myPostInitMethod(FMLPostInitializationEvent event) {
		// BC
		if (DrinkServerBCID > 1) {
			try {
				this.addDrinkServerBC();
			} catch (Exception e) {
				FMLLog.log(Level.SEVERE, Reference.MOD_NAME + " DrinkServerBCID found but BC not found");
			}
		}

		// FFM
		if (DrinkServerFFMID > 1) {
			try {
				this.addDrinkServerFFM();
			} catch (Exception e) {
				FMLLog.log(Level.SEVERE, Reference.MOD_NAME + " DrinkServerFFMID found but FFM not found");
			}
		}
		// InkMod
		if (DrinkServerInkID > 1) {
			try {
				this.addDrinkServerInk();
			} catch (Exception e) {
				FMLLog.log(Level.SEVERE, Reference.MOD_NAME + " DrinkServerInkID found but InkMOD not found");
			}
		}
	}

	/**
	 * バニラのドリンクサーバ追加
	 */
	public static BlockDrinkServerVanilla blockDrinkServer;

	private void addDrinkServer() {
		// アイテム
		Item[] itemType = { Item.bucketWater, Item.bucketMilk, Item.bucketLava };
		String[] itemName = { "Water", "Milk", "Lava" };
		// ブロック
		blockDrinkServer = new BlockDrinkServerVanilla(DrinkServerID, 0, itemType);
		blockDrinkServer.setBlockName("DrinkServer");
		// 登録
		GameRegistry.registerBlock(blockDrinkServer, ItemDrinkServer.class, "DrinkServer");

		// 名前、レシピ
		for (int i = 0; i < itemType.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockDrinkServer, 1, i * 4), itemName[i] + " Server");
			GameRegistry.addRecipe(new ItemStack(blockDrinkServer, 1, i * 4), new Object[] { "XXX", "XYX", "XXX", 'X', itemStackBlockMaterial, 'Y',
					new ItemStack(itemType[i], 1) });
		}

		if (isTest) {
			GameRegistry.addRecipe(new ItemStack(blockDrinkServer, 1, 0), new Object[] { "D D", "DD ", 'D', Block.dirt });
			GameRegistry.addRecipe(new ItemStack(blockDrinkServer, 1, 4), new Object[] { "D D", "D D", 'D', Block.dirt });
			GameRegistry.addRecipe(new ItemStack(blockDrinkServer, 1, 8), new Object[] { "D D", " DD", 'D', Block.dirt });
		}
	}

	/**
	 * BCの液体を追加
	 */
	public static BlockDrinkServerBC blockDrinkServerBC;

	private void addDrinkServerBC() throws Exception {
		/*
		 * BuildCraftEnergyでpublic static Item bucketOil等で定義されている
		 * Class.forNameとかgetDeclaredFieldとかで無理矢理持ってきてる
		 * BuildCraftAPIを使えばいいんだろうけどよくわからん
		 */
		// 素材になるアイテム
		// BCが無ければClassNotFoundExceptionとかになって何も起こらない、になるはず
		Class BCEnergy = Class.forName("buildcraft.BuildCraftEnergy");
		Item itemBucketOil = (Item) BCEnergy.getDeclaredField("bucketOil").get(null);
		Item itemGoldOil = (Item) BCEnergy.getDeclaredField("bucketFuel").get(null);
		Item[] itemTypeDrinkServerBC = { itemBucketOil, itemGoldOil };
		String[] itemNameDrinkServerBC = { "Oil", "Fuel" };
		// 登録
		blockDrinkServerBC = new BlockDrinkServerBC(DrinkServerBCID, 2, itemTypeDrinkServerBC);
		blockDrinkServerBC.setBlockName("DrinkServerBC");
		GameRegistry.registerBlock(blockDrinkServerBC, ItemDrinkServer.class, "DrinkServerBC");

		// 名前、レシピ
		for (int i = 0; i < itemTypeDrinkServerBC.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockDrinkServerBC, 1, i * 4), itemNameDrinkServerBC[i] + " Server");
			GameRegistry.addRecipe(new ItemStack(blockDrinkServerBC, 1, i * 4), new Object[] { "XXX", "XYX", "XXX", 'X', itemStackBlockMaterial, 'Y',
					new ItemStack(itemTypeDrinkServerBC[i], 1) });
		}

		if (isTest) {
			GameRegistry.addRecipe(new ItemStack(itemBucketOil, 1, 0), new Object[] { "DDD", "DDD", " D ", 'D', Block.dirt });
			GameRegistry.addRecipe(new ItemStack(itemGoldOil, 1, 0), new Object[] { "DDD", "DDD", "D  ", 'D', Block.dirt });
		}
	}

	/**
	 * FFMの液体を追加
	 */
	public static BlockDrinkServerFFM blockDrinkServerFFM;

	private void addDrinkServerFFM() throws Exception {
		// FFM存在チェック
		Class hoge = Class.forName("forestry.api.core.ItemInterface");
		/*
		 * いきなりforestry.api.core.ItemInterfaceを使うと、NoClassDefFoundErrorになりcatchできない
		 * Class.forNameだとClassNotFoundExceptionが飛ぶのでcatchできる
		 *
		 * FFMのアイテム取得は、ItemInterfaceでpublic static getItem(String
		 * itemName)で定義されてる getItemって名前のわりに返り値はItemStack
		 */
		// 素材になるアイテム
		Item bucketBiomass = forestry.api.core.ItemInterface.getItem("bucketBiomass").getItem();
		Item bucketBiofuel = forestry.api.core.ItemInterface.getItem("bucketBiofuel").getItem();
		Item[] itemTypeDrinkServerFFM = { bucketBiomass, bucketBiofuel };
		String[] itemNameDrinkServerFFM = { "Biomass", "Biofuel" };
		// 登録
		blockDrinkServerFFM = new BlockDrinkServerFFM(DrinkServerFFMID, 3, itemTypeDrinkServerFFM);
		blockDrinkServerFFM.setBlockName("DrinkServerFFM");
		GameRegistry.registerBlock(blockDrinkServerFFM, ItemDrinkServer.class, "DrinkServerFFM");

		// 名前、レシピ
		for (int i = 0; i < itemTypeDrinkServerFFM.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockDrinkServerFFM, 1, i * 4), itemNameDrinkServerFFM[i] + " Server");
			ModLoader.addRecipe(new ItemStack(blockDrinkServerFFM, 1, i * 4), new Object[] { "XXX", "XYX", "XXX", 'X', itemStackBlockMaterial, 'Y',
					new ItemStack(itemTypeDrinkServerFFM[i], 1) });
		}

		if (isTest) {
			GameRegistry.addRecipe(new ItemStack(bucketBiomass, 1, 0), new Object[] { "DDD", "DDD", "DD ", 'D', Block.dirt });
			GameRegistry.addRecipe(new ItemStack(bucketBiofuel, 1, 0), new Object[] { "DDD", "DDD", "D D", 'D', Block.dirt });
		}
	}

	/**
	 * InkModのイカスミバケツ
	 */
	public static BlockDrinkServerInk blockDrinkServerInk;

	private void addDrinkServerInk() throws Exception {

		/*
		 * FMLCommonHandler/ModContainerあたりから取ってこれそうだがよくわからん
		 */
		// 取ってくる
		Class InkMod = Class.forName("ayamitsu.ink.InkMod");

		Item itemBucketInk = (Item) InkMod.getDeclaredField("bucketInk").get(null);
		Item[] itemTypeDrinkServer = { itemBucketInk };
		String[] itemNameDrinkServer = { "Ink " };
		// 登録
		blockDrinkServerInk = new BlockDrinkServerInk(DrinkServerInkID, 4, itemTypeDrinkServer);
		blockDrinkServerInk.setBlockName("DrinkServerInk");
		GameRegistry.registerBlock(blockDrinkServerInk, ItemDrinkServer.class, "DrinkServerInk");

		// 名前、レシピ
		for (int i = 0; i < itemTypeDrinkServer.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockDrinkServerInk, 1, i * 4), itemNameDrinkServer[i] + " Server");
			GameRegistry.addRecipe(new ItemStack(blockDrinkServerInk, 1, i * 4), new Object[] { "XXX", "XYX", "XXX", 'X', itemStackBlockMaterial, 'Y',
					new ItemStack(itemTypeDrinkServer[i], 1) });
		}

	}
}