package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCItemAxe extends ItemAxe {
    private final ItemStack smeltResult;

    private final String modId, snakeName;

    public NCItemAxe(ToolMaterial material, String modId, String headCapitalName, String snakeName,
                     float damage, float speed, ItemStack smeltResult){
        super(material, damage, speed);

        this.modId = modId;
        this.snakeName = snakeName;

        this.smeltResult = smeltResult;

        setUnlocalizedName("axe" + headCapitalName);
        setRegistryName(modId, "axe_" + snakeName);
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(
                new ResourceLocation(modId, "tools/axe_" + snakeName),
                "inventory"));
    }

    public void registerSmeltingRecipes(){
        if(smeltResult != null){
            GameRegistry.addSmelting(new ItemStack(this), smeltResult, 0.1f);
        }
    }
}
