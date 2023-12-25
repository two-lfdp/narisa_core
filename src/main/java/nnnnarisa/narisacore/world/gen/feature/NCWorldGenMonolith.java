package nnnnarisa.narisacore.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class NCWorldGenMonolith extends WorldGenerator{
    private final IBlockState blockState;
    private int min, max;

    public NCWorldGenMonolith(int min, int max){
        this(Blocks.STONE.getDefaultState(), min, max);
    }

    public NCWorldGenMonolith(IBlockState blockState, int min, int max){
        this.blockState = blockState;
        this.min = min;
        this.max = max;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position){
        BlockPos.MutableBlockPos tempPos = new BlockPos.MutableBlockPos();
        int randRad = MathHelper.getInt(rand, min, max);
        for(int iz = -randRad ; iz <= randRad ; iz++){
            for(int ix = -randRad ; ix <= randRad ; ix++){
                tempPos.setPos(position.getX() + ix, position.getY(), position.getZ() + iz);
                worldIn.setBlockState(tempPos, blockState, 2);
            }
        }

        return true;
    }
}
