package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public interface INCBlockGenSwampHouse {
    void setSeed(long seed);

    IBlockState getMainBlockState();
    IBlockState getLogYAxisBlockState();
    IBlockState getLogXAxisBlockState();
    IBlockState getLogZAxisBlockState();
    IBlockState getPaneBlockState();
    IBlockState getFenceBlockState();
    IBlockState getStairsBlockStateFromDir(EnumFacing direction);
}
