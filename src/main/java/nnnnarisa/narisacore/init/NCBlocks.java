package nnnnarisa.narisacore.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.block.*;
import nnnnarisa.narisacore.item.*;

public class NCBlocks {
    public static final NCBlockMaterialMetal STORAGE_METAL = new NCBlockMaterialMetal();
    public static final NCBlockMaterialGem STORAGE_GEM = new NCBlockMaterialGem();
    public static final NCBlockMaterialFuel STORAGE_FUEL = new NCBlockMaterialFuel();
    public static final NCBlockMaterialMisc STORAGE_MISC = new NCBlockMaterialMisc();

    public static final NCBlockOreMetal ORE_METAL = new NCBlockOreMetal();
    public static final NCBlockOreGem ORE_GEM = new NCBlockOreGem();
    public static final NCBlockOreMisc ORE_MISC = new NCBlockOreMisc();
    public static final NCBlockOreDense ORE_DENSE = new NCBlockOreDense();

    public static final NCBlockDecorativeLapis LAPIS_DECO = new NCBlockDecorativeLapis();
    public static final NCBlockLapisSlab LAPIS_SLAB_HALF = new NCBlockLapisSlab.Half();
    public static final NCBlockLapisSlab LAPIS_SLAB_DOUBLE = new NCBlockLapisSlab.Double();
    public static final NCBlockLapisStairs LAPIS_STAIRS = new NCBlockLapisStairs(LAPIS_DECO);

    public static final NCItemBlockMaterialMetal ITEM_STORAGE_METAL = new NCItemBlockMaterialMetal(STORAGE_METAL);
    public static final NCItemBlockMaterialGem ITEM_STORAGE_GEM = new NCItemBlockMaterialGem(STORAGE_GEM);
    public static final NCItemBlockMaterialFuel ITEM_STORAGE_FUEL = new NCItemBlockMaterialFuel(STORAGE_FUEL);
    public static final NCItemBlockMaterialMisc ITEM_STORAGE_MISC = new NCItemBlockMaterialMisc(STORAGE_MISC);

    public static final NCItemBlockOreMetal ITEM_ORE_METAL = new NCItemBlockOreMetal(ORE_METAL);
    public static final NCItemBlockOreGem ITEM_ORE_GEM = new NCItemBlockOreGem(ORE_GEM);
    public static final NCItemBlockOreMisc ITEM_ORE_MISC = new NCItemBlockOreMisc(ORE_MISC);
    public static final NCItemBlockOreDense ITEM_ORE_DENSE = new NCItemBlockOreDense(ORE_DENSE);

    public static final NCItemBlockDecorativeLapis ITEM_LAPIS_DECO = new NCItemBlockDecorativeLapis(LAPIS_DECO);
    public static final NCItemBlockLapisSlab ITEM_LAPIS_SLAB = new NCItemBlockLapisSlab(LAPIS_SLAB_HALF, LAPIS_SLAB_DOUBLE);
    public static final NCItemBlockLapisStairs ITEM_LAPIS_STAIRS = new NCItemBlockLapisStairs(LAPIS_STAIRS);

    public static void registerBlocks(IForgeRegistry<Block> registry){
        STORAGE_METAL.registerBlocks(registry);
        STORAGE_GEM.registerBlocks(registry);
        STORAGE_FUEL.registerBlocks(registry);
        STORAGE_MISC.registerBlocks(registry);

        ORE_METAL.registerBlocks(registry);
        ORE_GEM.registerBlocks(registry);
        ORE_MISC.registerBlocks(registry);
        ORE_DENSE.registerBlocks(registry);

        LAPIS_DECO.registerBlocks(registry);
        LAPIS_SLAB_HALF.registerBlocks(registry);
        LAPIS_SLAB_DOUBLE.registerBlocks(registry);
        LAPIS_STAIRS.registerBlocks(registry);
    }

    public static void registerBlockItems(IForgeRegistry<Item> registry){
        ITEM_STORAGE_METAL.registerItems(registry);
        ITEM_STORAGE_GEM.registerItems(registry);
        ITEM_STORAGE_FUEL.registerItems(registry);
        ITEM_STORAGE_MISC.registerItems(registry);

        ITEM_ORE_METAL.registerItems(registry);
        ITEM_ORE_GEM.registerItems(registry);
        ITEM_ORE_MISC.registerItems(registry);
        ITEM_ORE_DENSE.registerItems(registry);

        ITEM_LAPIS_DECO.registerItems(registry);
        ITEM_LAPIS_SLAB.registerItems(registry);
        ITEM_LAPIS_STAIRS.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event){
        ITEM_STORAGE_METAL.registerModels(event);
        ITEM_STORAGE_GEM.registerModels(event);
        ITEM_STORAGE_FUEL.registerModels(event);
        ITEM_STORAGE_MISC.registerModels(event);

        ITEM_ORE_METAL.registerModels(event);
        ITEM_ORE_GEM.registerModels(event);
        ITEM_ORE_MISC.registerModels(event);
        ITEM_ORE_DENSE.registerModels(event);

        ITEM_LAPIS_DECO.registerModels(event);
        ITEM_LAPIS_SLAB.registerModels(event);
        ITEM_LAPIS_STAIRS.registerModels(event);
    }

    public static void registerSmeltingRecipes(){
        ITEM_ORE_METAL.registerSmeltingRecipes();
        ITEM_ORE_GEM.registerSmeltingRecipes();
        ITEM_ORE_MISC.registerSmeltingRecipes();
        ITEM_ORE_DENSE.registerSmeltingRecipes();
    }
}
