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

public class NCBlockOreMetal extends INCBlockMulti.Impl {
    public static final PropertyEnum<EnumMetalType> VARIANT =
            PropertyEnum.create("type", EnumMetalType.class, EnumMetalType.getSimpleMetalList());
    private static final EnumMetalType[] TYPE_SIMPLE = EnumMetalType.getSimpleMetal();

    public NCBlockOreMetal(){
        super(Material.ROCK);

        setUnlocalizedName("oreMetal");
        setRegistryName(NarisaCore.MODID, "ore_metal");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
        setDefaultState(getBlockState().getBaseState().withProperty(VARIANT, EnumMetalType.COPPER));

        for(int i = 0 ; i < TYPE_SIMPLE.length ; i++){
            EnumMetalType current = TYPE_SIMPLE[i];
            setHarvestLevel("pickaxe", current.getBlockLevel(), getStateFromMeta(current.ordinal()));
        }
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(VARIANT, TYPE_SIMPLE[meta]);
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
        for (int i = 0; i < TYPE_SIMPLE.length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    protected String getMetaName(int meta){
        return TYPE_SIMPLE[meta].getLowerName();
    }

    public void registerBlocks(IForgeRegistry<Block> registry){
        registry.register(this);
    }
}
