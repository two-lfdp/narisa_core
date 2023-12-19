package nnnnarisa.narisacore.item;

import net.minecraft.block.BlockQuartz.EnumType;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.block.NCBlockLapisStairs;

public class NCItemBlockLapisStairs extends NCItemBlockGeneral {
    public NCItemBlockLapisStairs(NCBlockLapisStairs block){
        super(block, false);

        setRegistryName(NarisaCore.MODID, "lapis_stairs");
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(
                new ResourceLocation(NarisaCore.MODID, "lapis_stairs"),
                "inventory"));
    }
}
