package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCBlockMaterialMisc extends NCBlockMulti {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("type", EnumType.class);


    public NCBlockMaterialMisc(){
        super(Material.IRON);

        setUnlocalizedName("storageMisc");
        setRegistryName(NarisaCore.MODID, "storage_misc");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(0.8F);
        setSoundType(SoundType.STONE);
        setDefaultState(getBlockState().getBaseState().withProperty(VARIANT, EnumType.SULFUR));

        for(int i = 0 ; i < EnumType.values().length ; i++){
            EnumType current = EnumType.values()[i];
            setHarvestLevel("pickaxe", current.getBlockLevel(), getStateFromMeta(current.ordinal()));
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(VARIANT, EnumType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumType.values().length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    protected String getMetaName(int meta){
        return EnumType.values()[meta].getLowerName();
    }

    public void registerBlocks(IForgeRegistry<Block> registry){
        registry.register(this);
    }

    public enum EnumType implements IStringSerializable{
        SULFUR("sulfur", "Sulfur", 1),
        NITER("niter", "Niter", 1),
        QUARTZ("quartz", "Quartz", 0);

        private final String lowerName, headCapitalName;
        private final int blockLevel;

        EnumType(String lowerName, String headCapitalName, int blockLevel) {
            this.lowerName = lowerName;
            this.headCapitalName = headCapitalName;
            this.blockLevel = blockLevel;
        }

        public String getLowerName() {
            return lowerName;
        }

        public String getHeadCapitalName() {
            return headCapitalName;
        }

        public int getBlockLevel() {
            return blockLevel;
        }

        public static EnumType[] getAdditionalType(){
            return new EnumType[]{SULFUR, NITER};
        }

        @Override
        public String getName() {
            return lowerName;
        }
    }
}