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
import nnnnarisa.narisacore.block.NCBlockOreMisc;
import nnnnarisa.narisacore.block.NCBlockOreMisc.EnumType;
import nnnnarisa.narisacore.init.NCItems;

public class NCItemBlockOreMisc extends NCItemBlock{
    private static final EnumType[] ADDITIONAL_TYPE = EnumType.getAdditionalType();

    public NCItemBlockOreMisc(NCBlockOreMisc block){
        super(block, true);

        setRegistryName(NarisaCore.MODID, "ore_misc");
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);

        for(int i = 0; i < ADDITIONAL_TYPE.length ; i++){
            OreDictionary.registerOre("ore" + ADDITIONAL_TYPE[i].getHeadCapitalName(),
                    new ItemStack(this, 1, i));
        }
        OreDictionary.registerOre("oreSaltpeter",
                new ItemStack(this, 1, EnumType.NITER.ordinal()));
    }

    public void registerSmeltingRecipes(){
        GameRegistry.addSmelting(new ItemStack(this, 1, 0),
                new ItemStack(NCItems.DUST, 1, 0), 0.1f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 1),
                new ItemStack(NCItems.DUST, 1, 1), 0.1f);
        GameRegistry.addSmelting(new ItemStack(this, 1, 2),
                new ItemStack(Items.QUARTZ, 1, 0), 0.2f);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelResourceLocation[] models = new ModelResourceLocation[EnumType.values().length];

        for(int i = 0 ; i < models.length ; i++){
            models[i] = new ModelResourceLocation(
                    new ResourceLocation(NarisaCore.MODID,
                            "ore/ore_" + EnumType.values()[i].getLowerName()),
                    "inventory");
        }

        ModelBakery.registerItemVariants(this, models);

        for(int i = 0 ; i < models.length ; i++){
            ModelLoader.setCustomModelResourceLocation(this, i, models[i]);
        }
    }
}
