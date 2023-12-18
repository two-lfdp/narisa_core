package nnnnarisa.narisacore.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.block.NCBlockMaterialFuel;
import nnnnarisa.narisacore.block.NCBlockMaterialMetal;
import nnnnarisa.narisacore.block.NCBlockMaterialMisc;
import nnnnarisa.narisacore.block.NCBlockOreDense;
import nnnnarisa.narisacore.block.NCBlockOreMetal;
import nnnnarisa.narisacore.block.NCBlockOreMisc;
import nnnnarisa.narisacore.item.NCItemBlockMaterialFuel;
import nnnnarisa.narisacore.item.NCItemBlockMaterialMetal;
import nnnnarisa.narisacore.item.NCItemBlockMaterialMisc;
import nnnnarisa.narisacore.item.NCItemBlockOreDense;
import nnnnarisa.narisacore.item.NCItemBlockOreMetal;
import nnnnarisa.narisacore.item.NCItemBlockOreMisc;

public class NCBlocks {
    public static final NCBlockMaterialMetal STORAGE_METAL = new NCBlockMaterialMetal();
    public static final NCBlockMaterialFuel STORAGE_FUEL = new NCBlockMaterialFuel();
    public static final NCBlockMaterialMisc STORAGE_MISC = new NCBlockMaterialMisc();
    public static final NCBlockOreMetal ORE_METAL = new NCBlockOreMetal();
    public static final NCBlockOreMisc ORE_MISC = new NCBlockOreMisc();
    public static final NCBlockOreDense ORE_DENSE = new NCBlockOreDense();
    public static final NCItemBlockMaterialMetal ITEM_STORAGE_METAL = new NCItemBlockMaterialMetal(STORAGE_METAL);
    public static final NCItemBlockMaterialFuel ITEM_STORAGE_FUEL = new NCItemBlockMaterialFuel(STORAGE_FUEL);
    public static final NCItemBlockMaterialMisc ITEM_STORAGE_MISC = new NCItemBlockMaterialMisc(STORAGE_MISC);
    public static final NCItemBlockOreMetal ITEM_ORE_METAL = new NCItemBlockOreMetal(ORE_METAL);
    public static final NCItemBlockOreMisc ITEM_ORE_MISC = new NCItemBlockOreMisc(ORE_MISC);
    public static final NCItemBlockOreDense ITEM_ORE_DENSE = new NCItemBlockOreDense(ORE_DENSE);

    public static void registerBlocks(IForgeRegistry<Block> registry){
        STORAGE_METAL.registerBlocks(registry);
        STORAGE_FUEL.registerBlocks(registry);
        STORAGE_MISC.registerBlocks(registry);
        ORE_METAL.registerBlocks(registry);
        ORE_MISC.registerBlocks(registry);
        ORE_DENSE.registerBlocks(registry);
    }

    public static void registerBlockItems(IForgeRegistry<Item> registry){
        ITEM_STORAGE_METAL.registerItems(registry);
        ITEM_STORAGE_FUEL.registerItems(registry);
        ITEM_STORAGE_MISC.registerItems(registry);
        ITEM_ORE_METAL.registerItems(registry);
        ITEM_ORE_MISC.registerItems(registry);
        ITEM_ORE_DENSE.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event){
        ITEM_STORAGE_METAL.registerModels(event);
        ITEM_STORAGE_FUEL.registerModels(event);
        ITEM_STORAGE_MISC.registerModels(event);
        ITEM_ORE_METAL.registerModels(event);
        ITEM_ORE_MISC.registerModels(event);
        ITEM_ORE_DENSE.registerModels(event);
    }

    public static void registerSmeltingRecipes(){
        ITEM_ORE_METAL.registerSmeltingRecipes();
        ITEM_ORE_MISC.registerSmeltingRecipes();
        ITEM_ORE_DENSE.registerSmeltingRecipes();
    }
}
