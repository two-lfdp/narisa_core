package nnnnarisa.narisacore.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.init.NCItems;

public class NCBlockOreMisc extends INCBlockMulti.Impl {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("type", EnumType.class);
    private static final EnumType[] TYPE_VALUES = EnumType.values();

    public NCBlockOreMisc(){
        super(Material.IRON);

        setUnlocalizedName("oreMisc");
        setRegistryName(NarisaCore.MODID, "ore_misc");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(0.8F);
        setSoundType(SoundType.STONE);
        setDefaultState(getBlockState().getBaseState().withProperty(VARIANT, EnumType.SULFUR));

        for(int i = 0 ; i < TYPE_VALUES.length ; i++){
            EnumType current = TYPE_VALUES[i];
            setHarvestLevel("pickaxe", current.getBlockLevel(), getStateFromMeta(current.ordinal()));
        }
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
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player){
        return true;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0)
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return (i + 1);
        }
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if(state.getValue(VARIANT) == EnumType.QUARTZ){
            return Items.QUARTZ;
        }
        return NCItems.DUST;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);
        for (int i = 0; i < count; i++)
        {
            Item item = this.getItemDropped(state, rand, fortune);
            if (item != Items.AIR)
            {
                drops.add(new ItemStack(item, 1, state.getValue(VARIANT).getItemMeta()));
            }
        }
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (state.getValue(VARIANT) == EnumType.QUARTZ)
        {
            return MathHelper.getInt(rand, 2, 5);
        }
        return MathHelper.getInt(rand, 0, 2);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
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

    public enum EnumType implements IStringSerializable{
        SULFUR("sulfur", "Sulfur", 1, 0),
        NITER("niter", "Niter", 1, 1),
        QUARTZ("quartz", "Quartz", 0, 0);

        private final String lowerName, headCapitalName;
        private final int blockLevel, itemMeta;

        EnumType(String lowerName, String headCapitalName, int blockLevel, int itemMeta) {
            this.lowerName = lowerName;
            this.headCapitalName = headCapitalName;
            this.blockLevel = blockLevel;
            this.itemMeta = itemMeta;
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

        public int getItemMeta() {
            return itemMeta;
        }

        @Override
        public String getName() {
            return lowerName;
        }

        public static EnumType[] getAdditionalType(){
            return new EnumType[]{SULFUR, NITER};
        }
    }
}
