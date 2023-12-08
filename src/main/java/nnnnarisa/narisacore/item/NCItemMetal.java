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
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCItemMetal extends Item{
    private String baseName;

    public NCItemMetal (String baseName){
        this.baseName = baseName;

        setUnlocalizedName(baseName);
        setRegistryName(NarisaCore.MODID, baseName);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.MATERIALS);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems){
        if(this.isInCreativeTab(tab)){
            for(int i = 0 ; i < EnumMetalType.values().length ; i++){
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
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelResourceLocation[] models = new ModelResourceLocation[EnumMetalType.values().length];

        for(int i = 0 ; i < models.length ; i++){
            models[i] = new ModelResourceLocation(
                    new ResourceLocation(NarisaCore.MODID,
                            baseName + "_" + EnumMetalType.values()[i]),
                    "inventory");
        }

        ModelBakery.registerItemVariants(this, models);

        for(int i = 0 ; i < models.length ; i++){
            ModelLoader.setCustomModelResourceLocation(this, i, models[i]);
        }
    }

    public enum EnumMetalType {
        COPPER("copper", "Copper"),
        TIN("tin", "Tin"),
        ALUMINUM("aluminum", "Aluminum"),
        LEAD("lead", "Lead"),
        SILVER("silver", "Silver"),
        BRONZE("bronze", "Bronze"),
        ELECTRUM("electrum", "Electrum"),
        ALUBRASS("alubrass", "Alubrass"),
        STEEL("steel", "Steel");

        private String lowerName, headCapitalName;

        EnumMetalType(String lowerName, String headCapitalName){
            this.lowerName = lowerName;
            this.headCapitalName = headCapitalName;
        }

        public String getLowerName(){
            return lowerName;
        }

        public String getHeadCapitalName(){
            return headCapitalName;
        }
    }
}
