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
    public static NCItemMaterialMetal ingot;
    public static NCItemMaterialMetal nugget;
    public static NCItemCoalCoke coalCoke;
    public static NCItemMaterialDust dust;

    public static void initItems(IForgeRegistry<Item> registry){
        ingot = new NCItemMaterialMetal("ingot");
        nugget = new NCItemMaterialMetal("nugget");
        coalCoke = new NCItemCoalCoke();
        dust = new NCItemMaterialDust();

        ingot.registerItems(registry);
        nugget.registerItems(registry);
        coalCoke.registerItems(registry);
        dust.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels(ModelRegistryEvent event){
        ingot.registerModels(event);
        nugget.registerModels(event);
        coalCoke.registerModels(event);
        dust.registerModels(event);
    }
}
