package nnnnarisa.narisacore;

import net.minecraft.block.Block;
//import net.minecraft.block.BlockStone; // DEBUG
//import net.minecraft.block.state.IBlockState; // DEBUG
import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.init.Blocks; // DEBUG
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.event.terraingen.DecorateBiomeEvent; // DEBUG
//import net.minecraft.util.math.BlockPos; // DEBUG
//import net.minecraft.util.math.ChunkPos; // DEBUG
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nnnnarisa.narisacore.init.NCBlocks;
import nnnnarisa.narisacore.init.NCItems;
//import nnnnarisa.narisacore.world.gen.feature.NCWorldGenSpikes; // DEBUG

//import java.util.Random; // DEBUG

@Mod(modid = NarisaCore.MODID, name = NarisaCore.NAME, version = NarisaCore.VERSION)
public class NarisaCore {
    public static final String MODID = "narisacore";
    public static final String NAME = "NarisaCore";
    public static final String VERSION = "0.12.0";

    public static final CreativeTabs TAB_NARISACORE = new CreativeTabNarisaCore();

    //private static NCWorldGenSpikes testGenerator1 = null; // DEBUG
    //private static NCWorldGenSpikes testGenerator2 = null; // DEBUG
    //private static NCWorldGenSpikes testGenerator3 = null; // DEBUG

    @EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.TERRAIN_GEN_BUS.register(this); // DEBUG
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

        // DEBUG
        /*
        IBlockState stone = Blocks.STONE.getDefaultState();
        if (testGenerator1 == null){
            testGenerator1 = new NCWorldGenSpikes(
                    Blocks.GLOWSTONE.getDefaultState(),
                    NCWorldGenSpikes.EnumSize.SMALL);
        }
        if (testGenerator2 == null){
            testGenerator2 = new NCWorldGenSpikes(
                    stone.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH),
                    NCWorldGenSpikes.EnumSize.MIDDLE);
        }
        if (testGenerator3 == null){
            testGenerator3 = new NCWorldGenSpikes(
                    stone,
                    NCWorldGenSpikes.EnumSize.LARGE);
        }
        // */
    }

    // DEBUG
    /*
    @SubscribeEvent
    public void onPostBiomeDecorate(DecorateBiomeEvent.Post event){
        Random rand = event.getRand();
        ChunkPos chunkPos = event.getChunkPos();
        BlockPos pos = new BlockPos((chunkPos.x << 4) + rand.nextInt(16) + 8,
                0, (chunkPos.z << 4) + rand.nextInt(16) + 8);
        int randVar = rand.nextInt(3);
        switch(randVar){
            case 1:
                testGenerator1.generate(event.getWorld(), rand, pos);
                break;
            case 2:
                testGenerator2.generate(event.getWorld(), rand, pos);
                break;
            default:
                testGenerator3.generate(event.getWorld(), rand, pos);
        }
    }
    // */
}
