package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCBlockMaterialFuel extends INCBlockMulti.Impl {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("type", EnumType.class);
    private static final EnumType[] TYPE_VALUES = EnumType.values();

    public NCBlockMaterialFuel(){
        super(Material.ROCK, MapColor.BLACK);

        setUnlocalizedName("storageFuel");
        setRegistryName(NarisaCore.MODID, "storage_fuel");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        setDefaultState(getBlockState().getBaseState().withProperty(VARIANT, EnumType.CHARCOAL));

        setHarvestLevel("pickaxe", 0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
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
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < TYPE_VALUES.length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }

    @Override
    protected String getMetaName(int meta){
        return TYPE_VALUES[meta].getLowerName();
    }

    public void registerBlocks(IForgeRegistry<Block> registry){
        registry.register(this);
    }

    public enum EnumType implements IStringSerializable{
        CHARCOAL("charcoal", "Charcoal", "charcoal", 20 * 80 * 10),
        COAL_COKE("coalCoke", "CoalCoke", "coal_coke", 20 * 160 * 10);

        private final String lowerName, headCapitalName, snakeName;
        private final int burningTime;

        EnumType(String lowerName, String headCapitalName, String snakeName, int burningTime) {
            this.lowerName = lowerName;
            this.headCapitalName = headCapitalName;
            this.snakeName = snakeName;
            this.burningTime = burningTime;
        }


        public String getLowerName() {
            return lowerName;
        }

        public String getHeadCapitalName() {
            return headCapitalName;
        }

        public String getSnakeName() {
            return snakeName;
        }

        public int getBurningTime() {
            return burningTime;
        }

        @Override
        public String getName() {
            return snakeName;
        }
    }
}
