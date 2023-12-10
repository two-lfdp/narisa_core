package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCItemCoalCoke extends Item{
    private static final int BURN_TIME = 3200;

    public NCItemCoalCoke(){
        setUnlocalizedName("coalCoke");
        setRegistryName(NarisaCore.MODID, "coal_coke");
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    @Override
    public int getItemBurnTime(ItemStack stack){
        return BURN_TIME;
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);

        OreDictionary.registerOre("fuelCoke", new ItemStack(this, 1, 0));
        OreDictionary.registerOre("itemCoalCoke", new ItemStack(this, 1, 0));
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(
                new ResourceLocation(NarisaCore.MODID, "fuel_coke"),
                "inventory"));
    }
}
