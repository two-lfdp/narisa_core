package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.block.*;
import net.minecraft.block.BlockDoor.*;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class StructureSmallHousePieces {
    public static void registerSmallHousePieces(){
        MapGenStructureIO.registerStructureComponent(StructureSmallHousePieces.House.class, "NCSHHouse");
    }

    public static class House extends StructureComponent{
        private static final int WIDTH = 12;
        private static final int HEIGHT = 6;
        private static final int DEPTH = 8;
        private static final int MARGIN = 5;
        private static final int BOUNDARY_WIDTH = MARGIN + WIDTH + MARGIN;
        private static final int BOUNDARY_HEIGHT = MARGIN - 1 + HEIGHT + 1 + MARGIN;
        private static final int BOUNDARY_DEPTH = MARGIN + DEPTH + MARGIN;

        private String namespace, location;
        private boolean placedChest1, placedChest2;
        private boolean isFloating;
        private String blockGenName;

        public House(){}

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound){
            tagCompound.setString("namespace", this.namespace);
            tagCompound.setString("location", this.location);
            tagCompound.setBoolean("placedChest1", this.placedChest1);
            tagCompound.setBoolean("placedChest2", this.placedChest2);
            tagCompound.setBoolean("isFloating", this.isFloating);
            tagCompound.setString("blockGenName", this.blockGenName);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager){
            this.namespace = tagCompound.getString("namespace");
            this.location = tagCompound.getString("location");
            this.placedChest1 = tagCompound.getBoolean("placedChest1");
            this.placedChest2 = tagCompound.getBoolean("placedChest2");
            this.isFloating = tagCompound.getBoolean("isFloating");
            this.blockGenName = tagCompound.getString("blockGenName");
        }

        public House(Random rand, int x, int fixedHeight, int z,
                     String namespace, String location,
                     boolean isFloating, String blockGenName){
            super(0);

            // Initialize variables
            this.namespace = namespace;
            this.location = location;
            this.placedChest1 = false;
            this.placedChest2 = false;
            this.isFloating = isFloating;
            this.blockGenName = blockGenName;

            // Randomize facing of the house
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            // Setting the bounding box
            if(this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z){
                this.boundingBox = new StructureBoundingBox(x, fixedHeight - MARGIN, z,
                        x + BOUNDARY_WIDTH - 1, fixedHeight - MARGIN + BOUNDARY_HEIGHT - 1, z + BOUNDARY_DEPTH - 1);
            }
            else{
                this.boundingBox = new StructureBoundingBox(x, fixedHeight - MARGIN, z,
                        x + BOUNDARY_DEPTH - 1, fixedHeight - MARGIN + BOUNDARY_HEIGHT - 1, z + BOUNDARY_WIDTH - 1);
            }
        }

        @Override
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
            INCBlockGenSmallHouse blockGen = NCMapGenSmallHouse.getBlockGen(this.blockGenName);
            blockGen.setSeed(randomIn.nextLong());

            // Fill with air as space
            for(int i = 0 ; i < MARGIN ; i++){
                this.fillWithAir(worldIn, structureBoundingBoxIn,
                        i, MARGIN * 2 - 1 - i, i,
                        BOUNDARY_WIDTH - 1 - i, BOUNDARY_HEIGHT - 1, BOUNDARY_DEPTH - 1 - i);
            }

            // Build the house
            // Wall
            IBlockState tempWall = blockGen.getWallBlockState();
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN, MARGIN - 1, MARGIN,
                    MARGIN + WIDTH - 1, MARGIN - 1 + HEIGHT - 1, MARGIN + DEPTH - 1,
                    tempWall, Blocks.AIR.getDefaultState(), false);
            // Floor
            IBlockState tempFloorCeil = blockGen.getFloorCeilBlockState();
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN, MARGIN - 1, MARGIN,
                    MARGIN + WIDTH - 1, MARGIN - 1, MARGIN + DEPTH - 1,
                    tempFloorCeil, tempFloorCeil, false);
            // Floor light
            IBlockState tempLight = blockGen.getLightBlockState();
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 3, MARGIN - 1, MARGIN + 2,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 3, MARGIN - 1, MARGIN + 5,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 8, MARGIN - 1, MARGIN + 2,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 8, MARGIN - 1, MARGIN + 5,
                    structureBoundingBoxIn);
            // Ceil
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN, MARGIN - 1 + HEIGHT - 1, MARGIN,
                    MARGIN + WIDTH - 1, MARGIN - 1 + HEIGHT - 1, MARGIN + DEPTH - 1,
                    tempFloorCeil, tempFloorCeil, false);
            this.setBlockState(worldIn, tempFloorCeil,
                    MARGIN + 11, MARGIN - 1 + HEIGHT, MARGIN + 1,
                    structureBoundingBoxIn);
            // Ceil light
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 3, MARGIN - 1 + HEIGHT - 1, MARGIN + 5,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 3, MARGIN - 1 + HEIGHT - 1, MARGIN + 2,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 8, MARGIN - 1 + HEIGHT - 1, MARGIN + 2,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempLight,
                    MARGIN + 8, MARGIN - 1 + HEIGHT - 1, MARGIN + 5,
                    structureBoundingBoxIn);
            // Pillars
            IBlockState tempPillar = blockGen.getPillarBlockState();
            for(int i = 0 ; i < 4 ; i++){
                this.setBlockState(worldIn, tempPillar,
                        MARGIN, MARGIN + i, MARGIN,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempPillar,
                        MARGIN + WIDTH - 1, MARGIN + i, MARGIN,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempPillar,
                        MARGIN, MARGIN + i, MARGIN + DEPTH - 1,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempPillar,
                        MARGIN + WIDTH - 1, MARGIN + i, MARGIN + DEPTH - 1,
                        structureBoundingBoxIn);
                this.setBlockState(worldIn, tempPillar,
                        MARGIN + 9, MARGIN + i, MARGIN + 1,
                        structureBoundingBoxIn);
            }
            // Glass pane
            IBlockState tempGlassPane = blockGen.getGlassPaneBlockState();
            // Front
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN + 4, MARGIN + 1, MARGIN,
                    MARGIN + 7, MARGIN + 2, MARGIN,
                    tempGlassPane, tempGlassPane, false);
            // Back
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN + 6, MARGIN + 1, MARGIN + DEPTH - 1,
                    MARGIN + 9, MARGIN + 2, MARGIN + DEPTH - 1,
                    tempGlassPane, tempGlassPane, false);
            // Right
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN, MARGIN + 1, MARGIN + 2,
                    MARGIN, MARGIN + 2, MARGIN + 5,
                    tempGlassPane, tempGlassPane, false);
            // Left
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    MARGIN + WIDTH - 1, MARGIN + 1, MARGIN + 2,
                    MARGIN + WIDTH - 1, MARGIN + 2, MARGIN + 5,
                    tempGlassPane, tempGlassPane, false);
            // Ladders
            IBlockState tempLadder = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.EAST);
            for(int i = 0 ; i < 5 ; i++){
                this.setBlockState(worldIn, tempLadder,
                        MARGIN + 10, MARGIN + i, MARGIN + 1,
                        structureBoundingBoxIn);
            }
            // Door
            BlockDoor tempDoorBlock = blockGen.getDoorBlock();
            IBlockState tempDoorL = tempDoorBlock.getDefaultState()
                    .withProperty(BlockDoor.FACING, EnumFacing.SOUTH)
                    .withProperty(BlockDoor.HINGE, EnumHingePosition.RIGHT);
            IBlockState tempDoorR = tempDoorL.withProperty(BlockDoor.HINGE, EnumHingePosition.LEFT);
            this.setBlockState(worldIn, tempDoorL,
                    MARGIN + 1, MARGIN, MARGIN,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempDoorL.withProperty(BlockDoor.HALF, EnumDoorHalf.UPPER),
                    MARGIN + 1, MARGIN + 1, MARGIN,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempDoorR,
                    MARGIN + 2, MARGIN, MARGIN,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, tempDoorR.withProperty(BlockDoor.HALF, EnumDoorHalf.UPPER),
                    MARGIN + 2, MARGIN + 1, MARGIN,
                    structureBoundingBoxIn);
            // Pressure plate
            this.setBlockState(worldIn, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(),
                    MARGIN + 1, MARGIN, MARGIN + 1,
                    structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(),
                    MARGIN + 2, MARGIN, MARGIN + 1,
                    structureBoundingBoxIn);
            // Furnaces
            for(int iy = 0 ; iy < 4 ; iy++){
                for(int ix = 0 ; ix < 4 ; ix++){
                    this.setBlockState(worldIn, Blocks.FURNACE.getDefaultState().withProperty(BlockFurnace.FACING, EnumFacing.NORTH),
                            MARGIN + 1 + ix, MARGIN + iy, MARGIN + DEPTH - 2,
                            structureBoundingBoxIn);
                }
            }
            // Crafting table
            this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(),
                    MARGIN + 5, MARGIN, MARGIN + DEPTH - 2,
                    structureBoundingBoxIn);
            // Chests
            ResourceLocation locationChest = new ResourceLocation(this.namespace, this.location);
            if(!placedChest1){
                placedChest1 = this.generateChest(worldIn, structureBoundingBoxIn, randomIn,
                        MARGIN + 6, MARGIN, MARGIN + DEPTH - 2,
                        locationChest);
            }
            if(!placedChest2){
                placedChest2 = this.generateChest(worldIn, structureBoundingBoxIn, randomIn,
                        MARGIN + 7, MARGIN, MARGIN + DEPTH - 2,
                        locationChest);
            }

            // Generate base
            if(!isFloating){
                // Front-Right
                for(int iz = 0 ; iz < MARGIN ; iz++){
                    for(int ix = 0 ; ix < MARGIN ; ix++){
                        int iy = Math.max(ix, iz);
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN - 1 - ix, MARGIN - 1 - iy, MARGIN - 1 - iz,
                                structureBoundingBoxIn);
                    }
                }
                // Front-Center
                for(int iz = 0 ; iz < MARGIN ; iz++){
                    for(int ix = 0 ; ix < WIDTH ; ix++){
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN + ix, MARGIN - 1 - iz, MARGIN - 1 - iz,
                                structureBoundingBoxIn);
                    }
                }
                // Front-Left
                for(int iz = 0 ; iz < MARGIN ; iz++){
                    for(int ix = 0 ; ix < MARGIN ; ix++){
                        int iy = Math.max(ix, iz);
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN + WIDTH + ix, MARGIN - 1 - iy, MARGIN - 1 - iz,
                                structureBoundingBoxIn);
                    }
                }
                // Center-Right
                for(int iz = 0 ; iz < DEPTH ; iz++){
                    for(int ix = 0 ; ix < MARGIN ; ix++){
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN - 1 - ix, MARGIN - 1 - ix, MARGIN + iz,
                                structureBoundingBoxIn);
                    }
                }
                // Center-Center
                for(int iz = 0 ; iz < DEPTH ; iz++) {
                    for (int ix = 0; ix < WIDTH; ix++) {
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN + ix, MARGIN - 2, MARGIN + iz,
                                structureBoundingBoxIn);
                    }
                }
                // Center-Left
                for(int iz = 0 ; iz < DEPTH ; iz++){
                    for(int ix = 0 ; ix < MARGIN ; ix++){
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN + WIDTH + ix, MARGIN - 1 - ix, MARGIN + iz,
                                structureBoundingBoxIn);
                    }
                }
                // Back-Right
                for(int iz = 0 ; iz < MARGIN ; iz++){
                    for(int ix = 0 ; ix < MARGIN ; ix++){
                        int iy = Math.max(ix, iz);
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN - 1 - ix, MARGIN - 1 - iy, MARGIN + DEPTH + iz,
                                structureBoundingBoxIn);
                    }
                }
                // Back-Center
                for(int iz = 0 ; iz < MARGIN ; iz++){
                    for(int ix = 0 ; ix < WIDTH ; ix++){
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN + ix, MARGIN - 1 - iz, MARGIN + DEPTH + iz,
                                structureBoundingBoxIn);
                    }
                }
                // Back-Left
                for(int iz = 0 ; iz < MARGIN ; iz++){
                    for(int ix = 0 ; ix < MARGIN ; ix++){
                        int iy = Math.max(ix, iz);
                        this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(),
                                MARGIN + WIDTH + ix, MARGIN - 1 - iy, MARGIN + DEPTH + iz,
                                structureBoundingBoxIn);
                    }
                }
            }

            return true;
        }
    }
}
