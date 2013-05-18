package mods.nurseangel.drinkserver;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import mods.nurseangel.drinkserver.block.BlockDrinkServer;
import mods.nurseangel.drinkserver.data.BuildCraft;
import mods.nurseangel.drinkserver.data.Forestry;
import mods.nurseangel.drinkserver.data.IServer;
import mods.nurseangel.drinkserver.data.InkMod;
import mods.nurseangel.drinkserver.data.Vanilla;
import mods.nurseangel.drinkserver.data.item.IServerItem;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModDrinkServer {

	/**
	 * ドリンクサーバのブロック
	 */
	public static BlockDrinkServer[] blockDrinkServer = new BlockDrinkServer[10];
	private int drinkServerCount = 0;

	public static boolean isTest = false;
	Config config;

	/**
	 * コンストラクタ的なもの
	 *
	 * @param event
	 */
	@Mod.PreInit
	public void myPreInitMethod(FMLPreInitializationEvent event) {
		config = new Config(event);
	}

	/**
	 * load()なもの
	 *
	 * @param event
	 */
	@Mod.Init
	public void myInitMethod(FMLInitializationEvent event) {
		// バニラのドリンクサーバ
		if (config.DrinkServerID > 0) {
			addServer(config.DrinkServerID, new Vanilla());
		}
	}

	/**
	 * modsLoaded()的なもの
	 *
	 * @param event
	 */
	@Mod.PostInit
	public void myPostInitMethod(FMLPostInitializationEvent event) {

		// BC
		if (config.DrinkServerBCID > 0 && config.isExistBC()) {
			addServer(config.DrinkServerBCID, new BuildCraft());
		}
		// FFM
		if (config.DrinkServerFFMID > 0 && config.isExistFFM()) {
			addServer(config.DrinkServerFFMID, new Forestry());
		}
		// Ink
		if (config.DrinkServerInkID > 0 && config.isExistInk()) {
			addServer(config.DrinkServerInkID, new InkMod());
		}

	}

	/**
	 * ドリンクサーバ追加
	 *
	 * @param server
	 */
	private void addServer(int blockId, IServer server) {
		// ブロック
		blockDrinkServer[drinkServerCount] = new BlockDrinkServer(blockId, server);
		blockDrinkServer[drinkServerCount].setUnlocalizedName("DrinkServer" + blockId);


		Map<Integer, IServerItem> aaaa = server.getItemList();
		Collection<IServerItem> aaa2 = aaaa.values();
		Object[] aaa3 = aaa2.toArray();
		String aaa4 = aaa3.toString();
		String aaa5 = server.getBlockName();

		// 登録
		GameRegistry.registerBlock(blockDrinkServer[drinkServerCount], ItemDrinkServer.class, server.getBlockName());

		// 名前、レシピ
		for (Entry<Integer, IServerItem> e : server.getItemList().entrySet()) {
			LanguageRegistry.addName(new ItemStack(blockDrinkServer[drinkServerCount], 1, e.getKey() * 4), e.getValue().getName() + " Server");

			GameRegistry.addRecipe(new ItemStack(blockDrinkServer[drinkServerCount], 1, e.getKey() * 4), new Object[] { "XXX", "XYX", "XXX", 'X',
					config.itemStackBlockMaterial, 'Y', new ItemStack(e.getValue().getMaterial(), 1) });
		}

		drinkServerCount++;

	}

}