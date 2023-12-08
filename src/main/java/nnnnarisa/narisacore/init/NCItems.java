package nnnnarisa.narisacore.init;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.item.NCItemMetal;

public class NCItems {
    public static NCItemMetal ingot;

    public static void initItems(IForgeRegistry<Item> registry){
        ingot = new NCItemMetal("ingot");

        ingot.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels(ModelRegistryEvent event){
        ingot.registerModels(event);
    }
}
