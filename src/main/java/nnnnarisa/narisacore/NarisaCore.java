package nnnnarisa.narisacore;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nnnnarisa.narisacore.init.*;

@Mod(modid = NarisaCore.MODID, name = NarisaCore.NAME, version = NarisaCore.VERSION)
public class NarisaCore {
    public static final String MODID = "narisacore";
    public static final String NAME = "NarisaCore";
    public static final String VERSION = "0.14.0";

    public static final CreativeTabs TAB_NARISACORE = new CreativeTabNarisaCore();

    //private static final NCDebugWorldGen WORLDGEN = new NCDebugWorldGen(); // DEBUG

    @EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(WORLDGEN); // DEBUG
        //MinecraftForge.TERRAIN_GEN_BUS.register(WORLDGEN); // DEBUG
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        NCBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        NCBlocks.registerBlockItems(event.getRegistry());
        NCItems.registerItems(event.getRegistry());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        NCBlocks.registerModels(event);
        NCItems.registerModels(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        NCBlocks.registerSmeltingRecipes();
        NCItems.registerSmeltingRecipes();
        NCStructures.registerStructures();

        //WORLDGEN.init(); // DEBUG
    }
}
