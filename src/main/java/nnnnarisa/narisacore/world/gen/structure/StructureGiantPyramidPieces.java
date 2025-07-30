package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class StructureGiantPyramidPieces {
    public static void registerGiantPyramidPieces(){
        MapGenStructureIO.registerStructureComponent(StructureGiantPyramidPieces.Pyramid.class, "NCGPPyramid");
    }

    public static class Pyramid extends StructureComponent{
        private static final int WIDTH = 31;
        private static final int HEIGHT = 15;
        private static final int DEPTH = 31;
        private static final int MARGIN = 5;
        private static final int CENTER = 15;
        private static final int BOUNDARY_WIDTH = MARGIN + WIDTH + MARGIN;
        private static final int BOUNDARY_HEIGHT = MARGIN - 1 + HEIGHT + MARGIN;
        private static final int BOUNDARY_DEPTH = MARGIN + DEPTH + MARGIN;

        private boolean isFloating;
        private String blockGenName;

        public Pyramid(){}

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound){
            tagCompound.setBoolean("isFloating", this.isFloating);
            tagCompound.setString("blockGenName", this.blockGenName);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager){
            this.isFloating = tagCompound.getBoolean("isFloating");
            this.blockGenName = tagCompound.getString("blockGenName");
        }

        public Pyramid(Random rand, int x, int y, int z,
                     boolean isFloating, String blockGenName){
            super(0);

            // Initialize variables
            this.isFloating = isFloating;
            this.blockGenName = blockGenName;

            // Randomize facing of the house
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            // Setting the bounding box
            if(this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z){
                this.boundingBox = new StructureBoundingBox(x, y - MARGIN, z,
                        x + BOUNDARY_WIDTH - 1, y - MARGIN + BOUNDARY_HEIGHT - 1, z + BOUNDARY_DEPTH - 1);
            }
            else{
                this.boundingBox = new StructureBoundingBox(x, y - MARGIN, z,
                        x + BOUNDARY_DEPTH - 1, y - MARGIN + BOUNDARY_HEIGHT - 1, z + BOUNDARY_WIDTH - 1);
            }
        }

        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
            INCBlockGenGiantPyramid blockGen = NCMapGenGiantPyramid.getBlockGen(this.blockGenName);
            blockGen.setSeed(randomIn.nextLong());

            // List up the BlockStates
            IBlockState tempNormal = blockGen.getMainBlockState();
            IBlockState tempSmooth = blockGen.getSmoothBlockState();
            IBlockState tempChiseled = blockGen.getChiseledBlockState();
            IBlockState tempTerracotta = blockGen.getTerracottaBlockState();
            IBlockState tempAccent = blockGen.getAccentTerracottaBlockState();

            // Build the exterior
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN, MARGIN - 1, MARGIN,
                    MARGIN + WIDTH - 1, MARGIN - 1, MARGIN + DEPTH - 1,
                    tempNormal, tempNormal, false);
            for (int i = HEIGHT - 1 ; i >= 1 ; i--){
                this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                        MARGIN + i, MARGIN - 1 + i, MARGIN + i,
                        MARGIN + WIDTH - 1 - i, MARGIN - 1 + i, MARGIN + DEPTH - 1 - i,
                        tempNormal, tempNormal, false);
                this.fillWithAir(worldIn, structureBoundingBoxIn,
                        MARGIN + i + 1, MARGIN - 1 + i, MARGIN + i + 1,
                        MARGIN + WIDTH - 1 - i - 1, MARGIN - 1 + i, MARGIN + DEPTH - 1 - i - 1);
            }

            // Paint floor mark
            // Center
            this.setBlockState(worldIn, tempAccent,
                    MARGIN + CENTER, MARGIN - 1, MARGIN + CENTER,
                    structureBoundingBoxIn);
            // Circle
            for (int i = -1 ; i <= 1 ; i++){
                // North
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER + 2, MARGIN - 1, MARGIN + CENTER + i,
                        structureBoundingBoxIn);
                // East
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER + i, MARGIN - 1, MARGIN + CENTER + 2,
                        structureBoundingBoxIn);
                // South
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER - 2, MARGIN - 1, MARGIN + CENTER + i,
                        structureBoundingBoxIn);
                // West
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER + i, MARGIN - 1, MARGIN + CENTER - 2,
                        structureBoundingBoxIn);
            }
            // Spreader
            // Orthogonal
            for (int i = 0 ; i < EnumFacing.HORIZONTALS.length ; i++){
                BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(MARGIN + CENTER, MARGIN - 1, MARGIN + CENTER);
                pos.move(EnumFacing.getHorizontal(i), 4);
                for (int j = 0 ; j < 4 ; j++){
                    this.setBlockState(worldIn, tempTerracotta,
                            pos.getX(), pos.getY(), pos.getZ(),
                            structureBoundingBoxIn);
                    pos.move(EnumFacing.getHorizontal(i));
                }
            }
            // Diagonal
            for (int i = 3 ; i <= 5 ; i++){
                // North-east
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER + i, MARGIN - 1, MARGIN + CENTER + i,
                        structureBoundingBoxIn);
                // North-west
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER - i, MARGIN - 1, MARGIN + CENTER + i,
                        structureBoundingBoxIn);
                // South-east
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER + i, MARGIN - 1, MARGIN + CENTER - i,
                        structureBoundingBoxIn);
                // South-west
                this.setBlockState(worldIn, tempTerracotta,
                        MARGIN + CENTER - i, MARGIN - 1, MARGIN + CENTER - i,
                        structureBoundingBoxIn);
            }

            // Second floor
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN + 7, MARGIN - 1 + 7, MARGIN + 7,
                    MARGIN + WIDTH - 1 - 7, MARGIN - 1 + 7, MARGIN + DEPTH - 1 - 7,
                    tempNormal, tempNormal, false);
            this.fillWithAir(worldIn, structureBoundingBoxIn,
                    MARGIN + CENTER - 3, MARGIN - 1 + 7, MARGIN + CENTER - 3,
                    MARGIN + CENTER + 3, MARGIN - 1 + 7, MARGIN + CENTER + 3);

            // Pillar
            // Floor
            for (int i = 0 ; i < 6 ; i++){
                IBlockState pillarBlockState = tempSmooth;
                if (i == 2 || i == 3){
                    pillarBlockState = tempChiseled;
                }
                // North-east
                this.setBlockState(worldIn, pillarBlockState,
                        MARGIN + CENTER + 4, MARGIN - 1 + 1 + i, MARGIN + CENTER + 4,
                        structureBoundingBoxIn);
                // North-west
                this.setBlockState(worldIn, pillarBlockState,
                        MARGIN + CENTER - 4, MARGIN - 1 + 1 + i, MARGIN + CENTER + 4,
                        structureBoundingBoxIn);
                // South-east
                this.setBlockState(worldIn, pillarBlockState,
                        MARGIN + CENTER + 4, MARGIN - 1 + 1 + i, MARGIN + CENTER - 4,
                        structureBoundingBoxIn);
                // North-west
                this.setBlockState(worldIn, pillarBlockState,
                        MARGIN + CENTER - 4, MARGIN - 1 + 1 + i, MARGIN + CENTER - 4,
                        structureBoundingBoxIn);
            }
            // Wall
            for (int i = 0 ; i < 7 ; i++){
                for (int j = 0 ; j < 5 ; j++){
                    IBlockState pillarBlockState = tempSmooth;
                    if (j == 2){
                        pillarBlockState = tempChiseled;
                    }
                    // West
                    this.setBlockState(worldIn, pillarBlockState,
                            MARGIN + 6, MARGIN - 1 + 1 + j, MARGIN + 6 + i * 3,
                            structureBoundingBoxIn);
                    // East
                    this.setBlockState(worldIn, pillarBlockState,
                            MARGIN + WIDTH - 1 - 6, MARGIN - 1 + 1 + j, MARGIN + 6 + i * 3,
                            structureBoundingBoxIn);
                }
            }
            // Deep west
            this.setBlockState(worldIn, tempSmooth,
                    MARGIN + 6, MARGIN - 1 + 1, MARGIN + DEPTH - 1 - 3,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempSmooth,
                    MARGIN + 6, MARGIN - 1 + 2, MARGIN + DEPTH - 1 - 3,
                    structureBoundingBoxIn);
            // Deep east
            this.setBlockState(worldIn, tempSmooth,
                    MARGIN + WIDTH - 1 - 6, MARGIN - 1 + 1, MARGIN + DEPTH - 1 - 3,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempSmooth,
                    MARGIN + WIDTH - 1 - 6, MARGIN - 1 + 2, MARGIN + DEPTH - 1 - 3,
                    structureBoundingBoxIn);

            // Front
            // West
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN, MARGIN - 1, MARGIN,
                    MARGIN + 6, MARGIN - 1 + 11, MARGIN + 6,
                    tempNormal, Blocks.AIR.getDefaultState(), false);
            // East
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN + WIDTH - 1 - 6, MARGIN - 1, MARGIN,
                    MARGIN + WIDTH - 1, MARGIN - 1 + 11, MARGIN + 6,
                    tempNormal, Blocks.AIR.getDefaultState(), false);
            // Center
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN + 6, MARGIN - 1, MARGIN,
                    MARGIN + WIDTH - 1 - 6, MARGIN - 1 + 5, MARGIN + 5,
                    tempNormal, Blocks.AIR.getDefaultState(), false);
            // Entrance
            // Center
            this.fillWithAir(worldIn, structureBoundingBoxIn,
                    MARGIN + CENTER - 1, MARGIN - 1 + 1, MARGIN,
                    MARGIN + CENTER + 1, MARGIN - 1 + 3, MARGIN);
            this.fillWithAir(worldIn, structureBoundingBoxIn,
                    MARGIN + CENTER - 1, MARGIN - 1 + 1, MARGIN + 5,
                    MARGIN + CENTER + 1, MARGIN - 1 + 3, MARGIN + 5);
            // West
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + 6, MARGIN - 1 + 1, MARGIN + 3,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + 6, MARGIN - 1 + 2, MARGIN + 3,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + 3, MARGIN - 1 + 1, MARGIN + 6,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + 3, MARGIN - 1 + 2, MARGIN + 6,
                    structureBoundingBoxIn);
            // East
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + WIDTH - 1 - 6, MARGIN - 1 + 1, MARGIN + 3,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + WIDTH - 1 - 6, MARGIN - 1 + 2, MARGIN + 3,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + WIDTH - 1 - 3, MARGIN - 1 + 1, MARGIN + 6,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + WIDTH - 1 - 3, MARGIN - 1 + 2, MARGIN + 6,
                    structureBoundingBoxIn);
            // Panel
            for (int i = 0 ; i < 3 ; i++){
                this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                        MARGIN + CENTER - 6 + i, MARGIN - 1 + 6 + i, MARGIN,
                        MARGIN + CENTER + 6 - i, MARGIN - 1 + 6 + i, MARGIN + 3 - i,
                        tempNormal, tempNormal, false);
            }
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN + CENTER - 2, MARGIN - 1 + 9, MARGIN,
                    MARGIN + CENTER + 2, MARGIN - 1 + 12, MARGIN,
                    tempNormal, tempNormal, false);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + CENTER - 2, MARGIN - 1 + 12, MARGIN,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(),
                    MARGIN + CENTER + 2, MARGIN - 1 + 12, MARGIN,
                    structureBoundingBoxIn);

            // Paint front mark
            // West and east
            for (int ix = 0 ; ix < 2 ; ix++){
                int centerX = MARGIN + 3 + ix * (-3 + WIDTH - 1 - 3);
                // Center
                this.setBlockState(worldIn, tempAccent,
                        centerX, MARGIN - 1 + 6, MARGIN,
                        structureBoundingBoxIn);
                // Ring
                for (int jy = -2 ; jy <= 2 ; jy++){
                    for (int jx = -1 ; jx <= 1 ; jx++){
                        if (Math.abs(jy) == 2 || Math.abs(jx) == 1){
                            this.setBlockState(worldIn, tempTerracotta,
                                    centerX + jx, MARGIN - 1 + 6 + jy, MARGIN,
                                    structureBoundingBoxIn);
                        }
                    }
                }
                // Spreader
                // Horizontal
                this.setBlockState(worldIn, tempTerracotta,
                        centerX - 2, MARGIN - 1 + 6, MARGIN,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempTerracotta,
                        centerX + 2, MARGIN - 1 + 6, MARGIN,
                        structureBoundingBoxIn);
                // Vertical
                this.setBlockState(worldIn, tempTerracotta,
                        centerX, MARGIN - 1 + 2, MARGIN,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempTerracotta,
                        centerX, MARGIN - 1 + 3, MARGIN,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempTerracotta,
                        centerX, MARGIN - 1 + 9, MARGIN,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempTerracotta,
                        centerX, MARGIN - 1 + 10, MARGIN,
                        structureBoundingBoxIn);
            }
            // Panel
            for (int iy = -1 ; iy <= 1 ; iy++){
                for (int ix = -1 ; ix <= 1 ; ix++){
                    int dst = Math.abs(ix) + Math.abs(iy);
                    IBlockState tempBlockState = null;
                    if (dst == 0){
                        tempBlockState = tempAccent;
                    }
                    else if (dst == 1){
                        tempBlockState = tempTerracotta;
                    }
                    if (tempBlockState != null){
                        this.setBlockState(worldIn, tempBlockState,
                                MARGIN + CENTER + ix, MARGIN - 1 + 10 + iy, MARGIN,
                                structureBoundingBoxIn);
                    }
                }
            }

            // Generate base
            if (!isFloating){
                for (int iz = 0 ; iz < DEPTH ; iz++){
                    for (int ix = 0; ix < WIDTH; ix++){
                        this.replaceAirAndLiquidDownwards(worldIn, tempNormal,
                                MARGIN + ix, MARGIN - 2, MARGIN + iz,
                                structureBoundingBoxIn);
                    }
                }
            }

            return true;
        }
    }
}
