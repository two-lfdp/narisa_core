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
import nnnnarisa.narisacore.init.NCBlocks;
import nnnnarisa.narisacore.init.NCItems;

@Mod(modid = NarisaCore.MODID, name = NarisaCore.NAME, version = NarisaCore.VERSION)
public class NarisaCore {
    public static final String MODID = "narisacore";
    public static final String NAME = "NarisaCore";
    public static final String VERSION = "0.9.0";

    public static final CreativeTabs TAB_NARISACORE = new CreativeTabNarisaCore();

    @EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        NCBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        NCItems.registerItems(event.getRegistry());
        NCBlocks.registerBlockItems(event.getRegistry());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        NCItems.registerModels(event);
        NCBlocks.registerModels(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        NCBlocks.registerSmeltingRecipes();
    }
}
