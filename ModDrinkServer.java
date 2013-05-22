package mods.nurseangel.drinkserver;

import java.util.Map.Entry;

import mods.nurseangel.drinkserver.block.BlockDrinkServer;
import mods.nurseangel.drinkserver.data.BuildCraft;
import mods.nurseangel.drinkserver.data.Forestry;
import mods.nurseangel.drinkserver.data.IServer;
import mods.nurseangel.drinkserver.data.ImmibisLxp;
import mods.nurseangel.drinkserver.data.InkMod;
import mods.nurseangel.drinkserver.data.MFR1;
import mods.nurseangel.drinkserver.data.MFR2;
import mods.nurseangel.drinkserver.data.ThermalExpansion;
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

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "after:BuildCraft|Core;" + "after:Forestry;"
		+ "after:MineFactoryReloaded;" + "after:LiquidXP;" + "after:MineFactoryReloaded;" + "after:ThermalExpansion;")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModDrinkServer {

	/**
	 * ドリンクサーバのブロック
	 */
	public static BlockDrinkServer[] blockDrinkServer = new BlockDrinkServer[10];
	private int drinkServerCount = 0;

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
		if (config.DrinkServerBCID > 0 && BuildCraft.isExist()) {
			addServer(config.DrinkServerBCID, new BuildCraft());
		}
		// FFM
		if (config.DrinkServerFFMID > 0 && Forestry.isExist()) {
			addServer(config.DrinkServerFFMID, new Forestry());
		}
		// Ink
		if (config.DrinkServerInkID > 0 && InkMod.isExist()) {
			addServer(config.DrinkServerInkID, new InkMod());
		}
		// IMMIBIS'S Lxp
		if (config.DrinkServerLxpID > 0 && ImmibisLxp.isExist()) {
			addServer(config.DrinkServerLxpID, new ImmibisLxp());
		}
		// MFR
		if (config.DrinkServerMFR1ID > 0 && MFR1.isExist()) {
			addServer(config.DrinkServerMFR1ID, new MFR1());
		}
		if (config.DrinkServerMFR2ID > 0 && MFR2.isExist()) {
			addServer(config.DrinkServerMFR2ID, new MFR2());
		}
		// TE
		if (config.DrinkServerTEID > 0 && ThermalExpansion.isExist()) {
			addServer(config.DrinkServerTEID, new ThermalExpansion());
		}

		// RailCraft
		// TODO なんか動かない
		// if (config.DrinkServerRCID > 0 && RailCraft.isExist()) {
		// addServer(config.DrinkServerRCID, new RailCraft());
		// }
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

		// 登録
		GameRegistry.registerBlock(blockDrinkServer[drinkServerCount], ItemDrinkServer.class, server.getBlockName());

		// 名前、レシピ
		for (Entry<Integer, IServerItem> e : server.getItemList().entrySet()) {
			LanguageRegistry.addName(new ItemStack(blockDrinkServer[drinkServerCount], 1, e.getKey() * 4), e.getValue().getName() + " Server");

			GameRegistry.addRecipe(new ItemStack(blockDrinkServer[drinkServerCount], 1, e.getKey() * 4), new Object[] { "XXX", "XYX", "XXX", 'X',
					config.itemStackBlockMaterial, 'Y', e.getValue().getMaterial() });
		}

		drinkServerCount++;

	}

}