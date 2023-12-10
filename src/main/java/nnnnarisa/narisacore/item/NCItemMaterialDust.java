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

public class NCItemMaterialDust extends Item{
    public NCItemMaterialDust(){
        setUnlocalizedName("dust");
        setRegistryName(NarisaCore.MODID, "dust");
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems){
        if(this.isInCreativeTab(tab)){
            for(int i = 0; i < EnumMaterialType.values().length ; i++){
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
        return super.getUnlocalizedName() + EnumMaterialType.values()[i].getHeadCapitalName();
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);

        for(int i = 0 ; i < EnumMaterialType.values().length ; i++) {
            OreDictionary.registerOre("dust" + EnumMaterialType.values()[i].getHeadCapitalName(),
                    new ItemStack(this, 1, i));
        }
        OreDictionary.registerOre("dustSaltpeter",
                new ItemStack(this, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelResourceLocation[] models = new ModelResourceLocation[EnumMaterialType.values().length];

        for(int i = 0 ; i < models.length ; i++){
            models[i] = new ModelResourceLocation(
                    new ResourceLocation(NarisaCore.MODID,
                            "dust_" + EnumMaterialType.values()[i].getLowerName()),
                    "inventory");
        }

        ModelBakery.registerItemVariants(this, models);

        for(int i = 0 ; i < models.length ; i++){
            ModelLoader.setCustomModelResourceLocation(this, i, models[i]);
        }
    }

    public enum EnumMaterialType {
        SULFUR("sulfur", "Sulfur"),
        NITER("niter", "Niter");

        private final String lowerName, headCapitalName;

        EnumMaterialType(String lowerName, String headCapitalName) {
            this.lowerName = lowerName;
            this.headCapitalName = headCapitalName;
        }

        public String getLowerName() {
            return lowerName;
        }

        public String getHeadCapitalName() {
            return headCapitalName;
        }
    }
}
