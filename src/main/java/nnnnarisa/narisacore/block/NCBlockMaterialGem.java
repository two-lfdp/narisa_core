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
import nnnnarisa.narisacore.util.EnumGemType;

public class NCBlockMaterialGem extends INCBlockMulti.Impl {
    public static final PropertyEnum<EnumGemType> VARIANT = PropertyEnum.create("type", EnumGemType.class);
    private static final EnumGemType[] TYPE_VALUES = EnumGemType.values();

    public NCBlockMaterialGem(){
        super(Material.IRON);

        setUnlocalizedName("storageGem");
        setRegistryName(NarisaCore.MODID, "storage_gem");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.METAL);
        setDefaultState(getBlockState().getBaseState().withProperty(VARIANT, EnumGemType.RUBY));

        setHarvestLevel("pickaxe", 2);
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
