package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCItemSword extends ItemSword {
    private final ItemStack smeltResult;

    private final String modId, snakeName;

    public NCItemSword(ToolMaterial material, String modId, String headCapitalName, String snakeName,
                       ItemStack smeltResult){
        super(material);

        this.modId = modId;
        this.snakeName = snakeName;

        this.smeltResult = smeltResult;

        setUnlocalizedName("sword" + headCapitalName);
        setRegistryName(modId, "sword_" + snakeName);
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(
                new ResourceLocation(modId, "tools/sword_" + snakeName),
                "inventory"));
    }

    public void registerSmeltingRecipes(){
        if(smeltResult != null){
            GameRegistry.addSmelting(new ItemStack(this), smeltResult, 0.1f);
        }
    }
}
