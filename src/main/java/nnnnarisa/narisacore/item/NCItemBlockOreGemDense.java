package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.block.NCBlockOreGemDense;
import nnnnarisa.narisacore.init.NCItems;
import nnnnarisa.narisacore.util.EnumGemType;

public class NCItemBlockOreGemDense extends NCItemBlockGeneral {
    private static final EnumGemType[] VALUE_TYPE = EnumGemType.values();

    public NCItemBlockOreGemDense(NCBlockOreGemDense block){
        super(block, true);

        setRegistryName(NarisaCore.MODID, "ore_gem_dense");
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);

        for(int i = 0; i < VALUE_TYPE.length ; i++){
            OreDictionary.registerOre("oreDense" + VALUE_TYPE[i].getOreDictName(),
                    new ItemStack(this, 1, i));
        }
    }

    public void registerSmeltingRecipes(){
        for (int i = 0; i < VALUE_TYPE.length ; i++){
            GameRegistry.addSmelting(new ItemStack(this, 1, i),
                    new ItemStack(NCItems.GEM, 2, i), 2.0f);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelResourceLocation[] models = new ModelResourceLocation[VALUE_TYPE.length];

        for(int i = 0 ; i < models.length ; i++){
            models[i] = new ModelResourceLocation(
                    new ResourceLocation(NarisaCore.MODID,
                            "ore/dense/ore_dense_" + VALUE_TYPE[i].getLowerName()),
                    "inventory");
        }

        ModelBakery.registerItemVariants(this, models);

        for(int i = 0 ; i < models.length ; i++){
            ModelLoader.setCustomModelResourceLocation(this, i, models[i]);
        }
    }
}
