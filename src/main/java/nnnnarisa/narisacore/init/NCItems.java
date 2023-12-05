package nnnnarisa.narisacore.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCItems {
    public static Item ingot;

    public static void initItems(IForgeRegistry<Item> registry){
        ingot = new Item()
                .setUnlocalizedName("ingot")
                .setRegistryName("narisacore", "ingot")
                .setCreativeTab(CreativeTabs.MATERIALS);

        registry.register(ingot);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(ingot, 0,
                new ModelResourceLocation(
                        new ResourceLocation(NarisaCore.MODID, "ingot"),
                        "inventory"));
    }
}
