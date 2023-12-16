package nnnnarisa.narisacore.init;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.item.NCItemCoalCoke;
import nnnnarisa.narisacore.item.NCItemMaterialMetal;
import nnnnarisa.narisacore.item.NCItemMaterialDust;

public class NCItems {
    public static final NCItemMaterialMetal INGOT = new NCItemMaterialMetal("ingot");;
    public static final NCItemMaterialMetal NUGGET = new NCItemMaterialMetal("nugget");
    public static final NCItemCoalCoke COAL_COKE = new NCItemCoalCoke();
    public static final NCItemMaterialDust DUST = new NCItemMaterialDust();

    public static void registerItems(IForgeRegistry<Item> registry){
        INGOT.registerItems(registry);
        NUGGET.registerItems(registry);
        COAL_COKE.registerItems(registry);
        DUST.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event){
        INGOT.registerModels(event);
        NUGGET.registerModels(event);
        COAL_COKE.registerModels(event);
        DUST.registerModels(event);
    }
}
