package nnnnarisa.narisacore.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.block.NCBlockMaterialFuel;
import nnnnarisa.narisacore.block.NCBlockMaterialMetal;
import nnnnarisa.narisacore.block.NCBlockMaterialMisc;
import nnnnarisa.narisacore.block.NCBlockOreMetal;
import nnnnarisa.narisacore.item.NCItemBlockMaterialFuel;
import nnnnarisa.narisacore.item.NCItemBlockMaterialMetal;
import nnnnarisa.narisacore.item.NCItemBlockMaterialMisc;
import nnnnarisa.narisacore.item.NCItemBlockOreMetal;

public class NCBlocks {
    public static final NCBlockMaterialMetal STORAGE_METAL = new NCBlockMaterialMetal();
    public static final NCBlockMaterialFuel STORAGE_FUEL = new NCBlockMaterialFuel();
    public static final NCBlockMaterialMisc STORAGE_MISC = new NCBlockMaterialMisc();
    public static final NCBlockOreMetal ORE_METAL = new NCBlockOreMetal();
    public static final NCItemBlockMaterialMetal ITEM_STORAGE_METAL = new NCItemBlockMaterialMetal(STORAGE_METAL);
    public static final NCItemBlockMaterialFuel ITEM_STORAGE_FUEL = new NCItemBlockMaterialFuel(STORAGE_FUEL);
    public static final NCItemBlockMaterialMisc ITEM_STORAGE_MISC = new NCItemBlockMaterialMisc(STORAGE_MISC);
    public static final NCItemBlockOreMetal ITEM_ORE_METAL = new NCItemBlockOreMetal(ORE_METAL);

    public static void registerBlockItems(IForgeRegistry<Item> registry){
        ITEM_STORAGE_METAL.registerItems(registry);
        ITEM_STORAGE_FUEL.registerItems(registry);
        ITEM_STORAGE_MISC.registerItems(registry);
        ITEM_ORE_METAL.registerItems(registry);
    }

    public static void registerBlocks(IForgeRegistry<Block> registry){
        STORAGE_METAL.registerBlocks(registry);
        STORAGE_FUEL.registerBlocks(registry);
        STORAGE_MISC.registerBlocks(registry);
        ORE_METAL.registerBlocks(registry);
    }

    public static void registerSmeltingRecipes(){
        ITEM_ORE_METAL.registerSmeltingRecipes();
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event){
        ITEM_STORAGE_METAL.registerModels(event);
        ITEM_STORAGE_FUEL.registerModels(event);
        ITEM_STORAGE_MISC.registerModels(event);
        ITEM_ORE_METAL.registerModels(event);
    }
}
