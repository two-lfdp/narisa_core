package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
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
import nnnnarisa.narisacore.block.NCBlockOreDense;
import nnnnarisa.narisacore.block.NCBlockOreDense.EnumType;
import nnnnarisa.narisacore.init.NCItems;

public class NCItemBlockOreDense extends NCItemBlock{
    private static final EnumType[] VALUE_TYPE = EnumType.values();

    public NCItemBlockOreDense(NCBlockOreDense block){
        super(block, true);

        setRegistryName(NarisaCore.MODID, "ore_dense");
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);

        for(int i = 0; i < VALUE_TYPE.length ; i++){
            OreDictionary.registerOre("oreDense" + VALUE_TYPE[i].getHeadCapitalName(),
                    new ItemStack(this, 1, i));
        }
        OreDictionary.registerOre("oreDenseSaltpeter",
                new ItemStack(this, 1, EnumType.NITER.ordinal()));
    }

    public void registerSmeltingRecipes(){
        GameRegistry.addSmelting(new ItemStack(this, 1, 0),
                new ItemStack(Items.IRON_INGOT, 2, 0), 1.4f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 1),
                new ItemStack(Items.GOLD_INGOT, 2, 0), 2.0f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 2),
                new ItemStack(NCItems.INGOT, 2, 0), 1.4f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 3),
                new ItemStack(NCItems.INGOT, 2, 1), 1.4f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 4),
                new ItemStack(NCItems.INGOT, 2, 2), 1.4f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 5),
                new ItemStack(NCItems.INGOT, 2, 3), 1.4f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 6),
                new ItemStack(NCItems.INGOT, 2, 4), 1.4f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 7),
                new ItemStack(NCItems.DUST, 2, 0), 0.2f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 8),
                new ItemStack(NCItems.DUST, 2, 1), 0.2f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 9),
                new ItemStack(Items.QUARTZ, 2, 0), 0.4f);
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
