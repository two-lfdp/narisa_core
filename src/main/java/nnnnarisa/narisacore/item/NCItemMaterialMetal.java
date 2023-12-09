package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.util.EnumMetalType;

public class NCItemMaterialMetal extends Item{
    private final String baseName;

    public NCItemMaterialMetal(String baseName){
        this.baseName = baseName;

        setUnlocalizedName(baseName);
        setRegistryName(NarisaCore.MODID, baseName);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems){
        if(this.isInCreativeTab(tab)){
            for(int i = 0; i < EnumMetalType.values().length ; i++){
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + EnumMetalType.values()[i].getHeadCapitalName();
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);

        for(int i = 0 ; i < EnumMetalType.values().length ; i++){
            OreDictionary.registerOre(baseName + EnumMetalType.values()[i].getHeadCapitalName(),
                    new ItemStack(this, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelResourceLocation[] models = new ModelResourceLocation[EnumMetalType.values().length];

        for(int i = 0 ; i < models.length ; i++){
            models[i] = new ModelResourceLocation(
                    new ResourceLocation(NarisaCore.MODID,
                            baseName + "/" + baseName + "_" + EnumMetalType.values()[i].getLowerName()),
                    "inventory");
        }

        ModelBakery.registerItemVariants(this, models);

        for(int i = 0 ; i < models.length ; i++){
            ModelLoader.setCustomModelResourceLocation(this, i, models[i]);
        }
    }

}
