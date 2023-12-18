package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import nnnnarisa.narisacore.init.NCBlocks;
import nnnnarisa.narisacore.init.NCItems;

import java.util.Random;

public class NCBlockOreDense extends INCBlockMulti.Impl {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("type", EnumType.class);
    private static final EnumType[] TYPE_VALUES = EnumType.values();

    public NCBlockOreDense(){
        super(Material.IRON);

        setUnlocalizedName("oreDense");
        setRegistryName(NarisaCore.MODID, "ore_dense");
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
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player){
        return true;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random){
        if (fortune > 0)
        {
            return (random.nextInt(fortune * 2) + 2);
        }
        return 2;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        switch(state.getValue(VARIANT)){
            case IRON:
                return Item.getItemFromBlock(Blocks.IRON_ORE);
            case GOLD:
                return Item.getItemFromBlock(Blocks.GOLD_ORE);
            case COPPER:
            case TIN:
            case ALUMINUM:
            case LEAD:
            case SILVER:
                return NCBlocks.ITEM_ORE_METAL;
            case SULFUR:
            case NITER:
                return NCItems.DUST;
        }
        return Items.QUARTZ;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
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
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune){
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        return state.getValue(VARIANT).getRandomExpValue(rand);
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

    public enum EnumType implements IStringSerializable{
        IRON("iron", "Iron", 1, 0),
        GOLD("gold", "Gold", 2, 0),
        COPPER("copper", "Copper", 1, 0),
        TIN("tin", "Tin", 1, 1),
        ALUMINUM("aluminum", "Aluminum", 1, 2),
        LEAD("lead", "Lead", 2, 3),
        SILVER("silver", "Silver", 2, 4),
        SULFUR("sulfur", "Sulfur", 1, 0){
            @Override
            public int getRandomExpValue(Random rand){
                return MathHelper.getInt(rand, 0, 2);
            }
        },
        NITER("niter", "Niter", 1, 1){
            @Override
            public int getRandomExpValue(Random rand){
                return MathHelper.getInt(rand, 0, 2);
            }
        },
        QUARTZ("quartz", "Quartz", 0, 0){
            @Override
            public int getRandomExpValue(Random rand){
                return MathHelper.getInt(rand, 2, 5);
            }
        };

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

        public int getRandomExpValue(Random rand){
            return 0;
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
