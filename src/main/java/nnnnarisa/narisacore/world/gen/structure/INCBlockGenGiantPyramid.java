package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.block.state.IBlockState;

public interface INCBlockGenGiantPyramid {
    void setSeed(long seed);

    IBlockState getMainBlockState();
    IBlockState getSmoothBlockState();
    IBlockState getChiseledBlockState();
    IBlockState getTerracottaBlockState();
    IBlockState getAccentTerracottaBlockState();
}
