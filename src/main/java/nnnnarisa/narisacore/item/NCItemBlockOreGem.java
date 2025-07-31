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
import nnnnarisa.narisacore.block.NCBlockOreGem;
import nnnnarisa.narisacore.init.NCItems;
import nnnnarisa.narisacore.util.EnumGemType;

public class NCItemBlockOreGem extends NCItemBlockGeneral {
    private static final EnumGemType[] TYPE_VALUES = EnumGemType.values();

    public NCItemBlockOreGem(NCBlockOreGem block){
        super(block, true);

        setRegistryName(NarisaCore.MODID, "ore_gem");
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);

        for(int i = 0; i < TYPE_VALUES.length ; i++){
            OreDictionary.registerOre("ore" + TYPE_VALUES[i].getOreDictName(),
                    new ItemStack(this, 1, i));
        }
    }

    public void registerSmeltingRecipes(){
        for(int i = 0; i < TYPE_VALUES.length ; i++){
            GameRegistry.addSmelting(new ItemStack(this, 1, i),
                    new ItemStack(NCItems.GEM, 1, i), 1.0f);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelResourceLocation[] models = new ModelResourceLocation[TYPE_VALUES.length];

        for(int i = 0 ; i < models.length ; i++){
            models[i] = new ModelResourceLocation(
                    new ResourceLocation(NarisaCore.MODID,
                            "ore/ore_" + TYPE_VALUES[i].getLowerName()),
                    "inventory");
        }

        ModelBakery.registerItemVariants(this, models);

        for(int i = 0 ; i < models.length ; i++){
            ModelLoader.setCustomModelResourceLocation(this, i, models[i]);
        }
    }
}
