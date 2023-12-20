package nnnnarisa.narisacore.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCItemArmor extends ItemArmor{
    private final ItemStack smeltResult;

    private final String modId, snakeName, renderName;

    public NCItemArmor(ArmorMaterial material, String modId, String camelName, String snakeName,
                       String renderName, EntityEquipmentSlot slot, ItemStack smeltResult){
        super(material, 3, slot);

        this.modId = modId;
        this.snakeName = snakeName;
        this.renderName = renderName;

        this.smeltResult = smeltResult;

        setUnlocalizedName(camelName);
        setRegistryName(modId, snakeName);
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type){
        return String.format(modId + ":textures/models/armor/%s_layer_%d.png", renderName, slot == EntityEquipmentSlot.LEGS ? 2 : 1);
    }

    public void registerItems(IForgeRegistry<Item> registry){
        registry.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(
                new ResourceLocation(modId, "armor/" + snakeName),
                "inventory"));
    }

    public void registerSmeltingRecipes(){
        if(smeltResult != null){
            GameRegistry.addSmelting(new ItemStack(this), smeltResult, 0.1f);
        }
    }
}
