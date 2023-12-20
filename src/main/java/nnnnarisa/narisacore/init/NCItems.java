package nnnnarisa.narisacore.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.item.*;

public class NCItems {
    public static final NCItemMaterialMetal INGOT = new NCItemMaterialMetal("ingot");;
    public static final NCItemMaterialMetal NUGGET = new NCItemMaterialMetal("nugget");
    public static final NCItemCoalCoke COAL_COKE = new NCItemCoalCoke();
    public static final NCItemMaterialDust DUST = new NCItemMaterialDust();

    public static final ToolMaterial TOOL_STEEL = EnumHelper.addToolMaterial("NNNN_STEEL",
            3, 800, 7.0f, 2.5f, 17)
            .setRepairItem(new ItemStack(INGOT, 1, 8));

    public static final NCItemPickaxe PICKAXE_STEEL = new NCItemPickaxe(TOOL_STEEL, NarisaCore.MODID,
            "Steel", "steel", new ItemStack(NUGGET, 1, 8));
    public static final NCItemShovel SHOVEL_STEEL = new NCItemShovel(TOOL_STEEL, NarisaCore.MODID,
            "Steel", "steel", new ItemStack(NUGGET, 1, 8));
    public static final NCItemAxe AXE_STEEL = new NCItemAxe(TOOL_STEEL, NarisaCore.MODID,
            "Steel", "steel", 8.0f, -3.0f, new ItemStack(NUGGET, 1, 8));
    public static final NCItemSword SWORD_STEEL = new NCItemSword(TOOL_STEEL, NarisaCore.MODID,
            "Steel", "steel", new ItemStack(NUGGET, 1, 8));
    public static final NCItemHoe HOE_STEEL = new NCItemHoe(TOOL_STEEL, NarisaCore.MODID,
            "Steel", "steel", new ItemStack(NUGGET, 1, 8));

    public static final ArmorMaterial ARMOR_STEEL = EnumHelper.addArmorMaterial("NNNN_STEEL",
            "steel", 20, new int[]{3, 6, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f)
            .setRepairItem(new ItemStack(INGOT, 1, 8));

    public static final NCItemArmor HELMET_STEEL = new NCItemArmor(ARMOR_STEEL, NarisaCore.MODID,
            "helmetSteel", "helmet_steel", "steel",
            EntityEquipmentSlot.HEAD, new ItemStack(NUGGET, 1, 8));
    public static final NCItemArmor CHESTPLATE_STEEL = new NCItemArmor(ARMOR_STEEL, NarisaCore.MODID,
            "chestplateSteel", "chestplate_steel", "steel",
            EntityEquipmentSlot.CHEST, new ItemStack(NUGGET, 1, 8));
    public static final NCItemArmor LEGGINGS_STEEL = new NCItemArmor(ARMOR_STEEL, NarisaCore.MODID,
            "leggingsSteel", "leggings_steel", "steel",
            EntityEquipmentSlot.LEGS, new ItemStack(NUGGET, 1, 8));
    public static final NCItemArmor BOOTS_STEEL = new NCItemArmor(ARMOR_STEEL, NarisaCore.MODID,
            "bootsSteel", "boots_steel", "steel",
            EntityEquipmentSlot.FEET, new ItemStack(NUGGET, 1, 8));

    public static void registerItems(IForgeRegistry<Item> registry){
        INGOT.registerItems(registry);
        NUGGET.registerItems(registry);
        COAL_COKE.registerItems(registry);
        DUST.registerItems(registry);

        PICKAXE_STEEL.registerItems(registry);
        SHOVEL_STEEL.registerItems(registry);
        AXE_STEEL.registerItems(registry);
        SWORD_STEEL.registerItems(registry);
        HOE_STEEL.registerItems(registry);

        HELMET_STEEL.registerItems(registry);
        CHESTPLATE_STEEL.registerItems(registry);
        LEGGINGS_STEEL.registerItems(registry);
        BOOTS_STEEL.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event){
        INGOT.registerModels(event);
        NUGGET.registerModels(event);
        COAL_COKE.registerModels(event);
        DUST.registerModels(event);

        PICKAXE_STEEL.registerModels(event);
        SHOVEL_STEEL.registerModels(event);
        AXE_STEEL.registerModels(event);
        SWORD_STEEL.registerModels(event);
        HOE_STEEL.registerModels(event);

        HELMET_STEEL.registerModels(event);
        CHESTPLATE_STEEL.registerModels(event);
        LEGGINGS_STEEL.registerModels(event);
        BOOTS_STEEL.registerModels(event);
    }

    public static void registerSmeltingRecipes(){
        PICKAXE_STEEL.registerSmeltingRecipes();
        SHOVEL_STEEL.registerSmeltingRecipes();
        AXE_STEEL.registerSmeltingRecipes();
        SWORD_STEEL.registerSmeltingRecipes();
        HOE_STEEL.registerSmeltingRecipes();

        HELMET_STEEL.registerSmeltingRecipes();
        CHESTPLATE_STEEL.registerSmeltingRecipes();
        LEGGINGS_STEEL.registerSmeltingRecipes();
        BOOTS_STEEL.registerSmeltingRecipes();
    }
}
