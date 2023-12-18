package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.util.EnumMetalType;

public class NCBlockMaterialMetal extends INCBlockMulti.Impl {
    public static final PropertyEnum<EnumMetalType> VARIANT = PropertyEnum.create("type", EnumMetalType.class);
    private static final EnumMetalType[] TYPE_VALUES = EnumMetalType.values();

    public NCBlockMaterialMetal(){
        super(Material.IRON);

        setUnlocalizedName("storageMetal");
        setRegistryName(NarisaCore.MODID, "storage_metal");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.METAL);
        setDefaultState(getBlockState().getBaseState().withProperty(VARIANT, EnumMetalType.COPPER));

        for(int i = 0 ; i < TYPE_VALUES.length ; i++){
            EnumMetalType current = TYPE_VALUES[i];
            setHarvestLevel("pickaxe", current.getBlockLevel(), getStateFromMeta(current.ordinal()));
        }
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(VARIANT, TYPE_VALUES[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    public int damageDropped(IBlockState state){
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items){
        for (int i = 0; i < TYPE_VALUES.length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    protected String getMetaName(int meta){
        return TYPE_VALUES[meta].getLowerName();
    }

    public void registerBlocks(IForgeRegistry<Block> registry){
        registry.register(this);
    }
}
