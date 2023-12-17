package nnnnarisa.narisacore.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.block.NCBlockMaterialFuel;
import nnnnarisa.narisacore.block.NCBlockMaterialMetal;
import nnnnarisa.narisacore.item.NCItemBlockMaterialFuel;
import nnnnarisa.narisacore.item.NCItemBlockMaterialMetal;

public class NCBlocks {
    public static final NCBlockMaterialMetal STORAGE_METAL = new NCBlockMaterialMetal();
    public static final NCBlockMaterialFuel STORAGE_FUEL = new NCBlockMaterialFuel();
    public static final NCItemBlockMaterialMetal ITEM_STORAGE_METAL = new NCItemBlockMaterialMetal(STORAGE_METAL);
    public static final NCItemBlockMaterialFuel ITEM_STORAGE_FUEL = new NCItemBlockMaterialFuel(STORAGE_FUEL);

    public static void registerBlockItems(IForgeRegistry<Item> registry){
        ITEM_STORAGE_METAL.registerItems(registry);
        ITEM_STORAGE_FUEL.registerItems(registry);
    }

    public static void registerBlocks(IForgeRegistry<Block> registry){
        STORAGE_METAL.registerBlocks(registry);
        STORAGE_FUEL.registerBlocks(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event){
        ITEM_STORAGE_METAL.registerModels(event);
        ITEM_STORAGE_FUEL.registerModels(event);
    }
}
