package nnnnarisa.narisacore.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class NCWorldGenSpikes extends WorldGenerator{
    private final IBlockState blockState;
    private final Block base;
    private final EnumSize size;

    public NCWorldGenSpikes(IBlockState blockState, EnumSize size){
        this(blockState, size, Blocks.STONE);
    }

    public NCWorldGenSpikes(IBlockState blockState, EnumSize size, Block base){
        this.blockState = blockState;
        this.size = size;
        this.base = base;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position){
        // Detect lowest Y-position
        int baseY = 256;
        if(size == EnumSize.SMALL){
            // Small-sized
            for(int iz = -1 ; iz <= 1 ; iz++){
                for(int ix = -1 ; ix <= 1 ; ix++){
                    if(MathHelper.abs(iz) + MathHelper.abs(ix) <= 1){
                        BlockPos posDetection = new BlockPos(position.getX() + ix, 0, position.getZ() + iz);
                        baseY = Math.min(baseY, worldIn.getTopSolidOrLiquidBlock(posDetection).getY());
                    }
                }
            }
        }
        else if(size == EnumSize.MIDDLE){
            // Middle-sized
            for(int iz = -1 ; iz <= 1 ; iz++){
                for(int ix = -1 ; ix <= 1 ; ix++){
                    BlockPos posDetection = new BlockPos(position.getX() + ix, 0, position.getZ() + iz);
                    baseY = Math.min(baseY, worldIn.getTopSolidOrLiquidBlock(posDetection).getY());
                }
            }
        }
        else{
            // Large-sized
            for(int iz = -2 ; iz <= 2 ; iz++){
                for(int ix = -2 ; ix <= 2 ; ix++){
                    if(MathHelper.abs(iz) + MathHelper.abs(ix) <= 2){
                        BlockPos posDetection = new BlockPos(position.getX() + ix, 0, position.getZ() + iz);
                        baseY = Math.min(baseY, worldIn.getTopSolidOrLiquidBlock(posDetection).getY());
                    }
                }
            }
        }

        if(baseY >= 256){
            return false;
        }

        // Place blocks
        // Replace dirt or grass blocks into specified blocks
        if(size == EnumSize.SMALL){
            // Small-sized
            for(int iz = -1 ; iz <= 1 ; iz++){
                for(int ix = -1 ; ix <= 1 ; ix++){
                    if(MathHelper.abs(iz) + MathHelper.abs(ix) <= 1){
                        replaceUndergroundBlock(worldIn, position.getX() + ix, baseY, position.getZ() + iz);
                    }
                }
            }
        }
        else if(size == EnumSize.MIDDLE){
            // Middle-sized
            for(int iz = -1 ; iz <= 1 ; iz++){
                for(int ix = -1 ; ix <= 1 ; ix++){
                    replaceUndergroundBlock(worldIn, position.getX() + ix, baseY, position.getZ() + iz);
                }
            }
        }
        else{
            // Large-sized
            for(int iz = -2 ; iz <= 2 ; iz++){
                for(int ix = -2 ; ix <= 2 ; ix++){
                    if(MathHelper.abs(iz) + MathHelper.abs(ix) <= 2){
                        replaceUndergroundBlock(worldIn, position.getX() + ix, baseY, position.getZ() + iz);
                    }
                }
            }
        }
        // Place specified block from above the surface
        int currentY = baseY;
        BlockPos.MutableBlockPos tempPos = new BlockPos.MutableBlockPos();
        if(size == EnumSize.LARGE){
            // Large-sized
            for(int iz = -2 ; iz <= 2 ; iz++){
                for(int ix = -2 ; ix <= 2 ; ix++){
                    if(MathHelper.abs(iz) + MathHelper.abs(ix) <= 2){
                        for (int iy = 0 ; iy < 4 ; iy++){
                            tempPos.setPos(position.getX() + ix, currentY + iy, position.getZ() + iz);
                            worldIn.setBlockState(tempPos, blockState, 2);
                        }
                    }
                }
            }
            currentY += 4;
        }
        if(size == EnumSize.MIDDLE || size == EnumSize.LARGE){
            // Middle-sized or more
            for(int iz = -1 ; iz <= 1 ; iz++){
                for(int ix = -1 ; ix <= 1 ; ix++){
                    for (int iy = 0 ; iy < 3 ; iy++){
                        tempPos.setPos(position.getX() + ix, currentY + iy, position.getZ() + iz);
                        worldIn.setBlockState(tempPos, blockState, 2);
                    }
                }
            }
            currentY += 3;
        }
        // Every size
        for(int iz = -1 ; iz <= 1 ; iz++){
            for(int ix = -1 ; ix <= 1 ; ix++){
                if(MathHelper.abs(iz) + MathHelper.abs(ix) <= 1){
                    for (int iy = 0 ; iy < 2 ; iy++){
                        tempPos.setPos(position.getX() + ix, currentY + iy, position.getZ() + iz);
                        worldIn.setBlockState(tempPos, blockState, 2);
                    }
                }
            }
        }
        currentY += 2;
        tempPos.setPos(position.getX(), currentY, position.getZ());
        worldIn.setBlockState(tempPos, blockState, 2);

        return true;
    }

    private void replaceUndergroundBlock(World worldIn, int x, int baseY, int z){
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, baseY, z);
        // Move the marker down if marked block isn't specified base block
        while(pos.getY() >= 0 && worldIn.getBlockState(pos).getBlock() != base){
            pos.move(EnumFacing.DOWN);
        }
        // Reached void
        if(pos.getY() < 0){
            return;
        }
        // Reached specified base block
        // Replace blocks above the base block into specified block
        pos.move(EnumFacing.UP);
        for( ; pos.getY() < baseY ; pos.move(EnumFacing.UP)){
            worldIn.setBlockState(pos, blockState, 2);
        }
    }

    public enum EnumSize{
        SMALL, MIDDLE, LARGE
    }
}
