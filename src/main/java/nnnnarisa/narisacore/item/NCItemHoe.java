package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCItemHoe extends ItemHoe {
    private final ItemStack smeltResult;

    private final String modId, snakeName;

    public NCItemHoe(ToolMaterial material, String modId, String headCapitalName, String snakeName,
                     ItemStack smeltResult){
        super(material);

        this.modId = modId;
        this.snakeName = snakeName;

        this.smeltResult = smeltResult;

        setUnlocalizedName("hoe" + headCapitalName);
        setRegistryName(modId, "hoe_" + snakeName);
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(
                new ResourceLocation(modId, "tools/hoe_" + snakeName),
                "inventory"));
    }

    public void registerSmeltingRecipes(){
        if(smeltResult != null){
            GameRegistry.addSmelting(new ItemStack(this), smeltResult, 0.1f);
        }
    }
}
