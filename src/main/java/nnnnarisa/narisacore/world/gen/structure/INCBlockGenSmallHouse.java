package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;

public interface INCBlockGenSmallHouse {
    void setSeed(long seed);

    IBlockState getWallBlockState();
    IBlockState getFloorCeilBlockState();
    IBlockState getLightBlockState();
    IBlockState getPillarBlockState();
    IBlockState getGlassPaneBlockState();
    BlockDoor getDoorBlock();
}
