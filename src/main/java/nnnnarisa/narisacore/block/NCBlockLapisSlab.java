package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.init.NCBlocks;

import java.util.Random;

public abstract class NCBlockLapisSlab extends BlockPurpurSlab{
    public NCBlockLapisSlab(){
        super();

        if(isDouble()){
            setUnlocalizedName("lapisSlabDouble");
            setRegistryName(NarisaCore.MODID, "lapis_slab_double");
        }
        else{
            setUnlocalizedName("lapisSlabHalf");
            setRegistryName(NarisaCore.MODID, "lapis_slab_half");
        }
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);

        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos){
        return MapColor.LAPIS;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(NCBlocks.LAPIS_SLAB_HALF);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        return new ItemStack(NCBlocks.LAPIS_SLAB_HALF);
    }

    public void registerBlocks(IForgeRegistry<Block> registry){
        registry.register(this);
    }

    public static class Double extends NCBlockLapisSlab{
        public boolean isDouble(){
            return true;
        }
    }

    public static class Half extends NCBlockLapisSlab{
        public boolean isDouble(){
            return false;
        }
    }
}
