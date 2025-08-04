package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.*;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.*;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class StructureSwampHousePieces {
    public static void registerSwampHousePieces(){
        MapGenStructureIO.registerStructureComponent(StructureSwampHousePieces.Core.class, "NCSWCore");
        MapGenStructureIO.registerStructureComponent(StructureSwampHousePieces.Veranda.class, "NCSWVeranda");
        MapGenStructureIO.registerStructureComponent(StructureSwampHousePieces.Roof.class, "NCSWRoof");
    }

    public static class Core extends StructureComponent{
        private static final int WIDTH = 7;
        private static final int HEIGHT = 5;
        private static final int DEPTH = 10;

        private String namespace, location;
        private boolean placedChest;
        private boolean hasWitch;
        private String blockGenName;

        public Core(){}

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound){
            tagCompound.setString("namespace", this.namespace);
            tagCompound.setString("location", this.location);
            tagCompound.setBoolean("placedChest", this.placedChest);
            tagCompound.setBoolean("hasWitch", this.hasWitch);
            tagCompound.setString("blockGenName", this.blockGenName);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager){
            this.namespace = tagCompound.getString("namespace");
            this.location = tagCompound.getString("location");
            this.placedChest = tagCompound.getBoolean("placedChest");
            this.hasWitch = tagCompound.getBoolean("hasWitch");
            this.blockGenName = tagCompound.getString("blockGenName");
        }

        public Core(Random rand, int x, int fixedHeight, int z,
                    String namespace, String location,
                    String blockGenName){
            super(0);

            // Initialize variables
            this.namespace = namespace;
            this.location = location;
            this.placedChest = false;
            this.hasWitch = false;
            this.blockGenName = blockGenName;

            // Randomize facing of the house
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            // Setting the bounding box
            if(this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z){
                this.boundingBox = new StructureBoundingBox(x, fixedHeight, z,
                        x + WIDTH - 1, fixedHeight + HEIGHT - 1, z + DEPTH - 1);
            }
            else{
                this.boundingBox = new StructureBoundingBox(x, fixedHeight, z,
                        x + DEPTH - 1, fixedHeight + HEIGHT - 1, z + WIDTH - 1);
            }
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            StructureSwampHousePieces.Veranda veranda =
                    new StructureSwampHousePieces.Veranda(
                            this.getXWithOffset(0, -1), this.getYWithOffset(1), this.getZWithOffset(0, -1),
                            this.getCoordBaseMode(), this.blockGenName);
            listIn.add(veranda);
            StructureSwampHousePieces.Roof roof =
                    new StructureSwampHousePieces.Roof(
                            this.getBoundingBox().minX, this.getYWithOffset(HEIGHT), this.getBoundingBox().minZ,
                            this.getCoordBaseMode(), this.blockGenName);
            listIn.add(roof);
        }

        @Override
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
            INCBlockGenSwampHouse blockGen = NCMapGenSwampHouse.getBlockGen(this.blockGenName);
            blockGen.setSeed(randomIn.nextLong());

            // List up the BlockStates
            IBlockState tempLogY = blockGen.getLogYAxisBlockState();
            IBlockState tempLogX = blockGen.getLogXAxisBlockState();
            IBlockState tempLogZ = blockGen.getLogZAxisBlockState();
            IBlockState tempPlanks = blockGen.getMainBlockState();
            IBlockState tempPane = blockGen.getPaneBlockState();

            // Frame
            // Vertical
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 1, 0,
                    0, HEIGHT - 1, 0,
                    tempLogY, tempLogY, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    WIDTH - 1, 1, 0,
                    WIDTH - 1, HEIGHT - 1, 0,
                    tempLogY, tempLogY, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 1, DEPTH - 1,
                    0, HEIGHT - 1, DEPTH - 1,
                    tempLogY, tempLogY, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    WIDTH - 1, 1, DEPTH - 1,
                    WIDTH - 1, HEIGHT - 1, DEPTH - 1,
                    tempLogY, tempLogY, false);
            // Horizontal
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    1, 1, 0,
                    WIDTH - 2, 1, 0,
                    tempLogX, tempLogX, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    1, 1, DEPTH - 1,
                    WIDTH - 2, 1, DEPTH - 1,
                    tempLogX, tempLogX, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 1, 1,
                    0, 1, DEPTH - 2,
                    tempLogZ, tempLogZ, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    WIDTH - 1, 1, 1,
                    WIDTH - 1, 1, DEPTH - 2,
                    tempLogZ, tempLogZ, false);

            // Floor and wall
            // Floor
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    1, 1, 1,
                    WIDTH - 2, 1, DEPTH - 2,
                    tempPlanks, tempPlanks, false);
            // Wall
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    1, 2, 0,
                    WIDTH - 2, HEIGHT - 1, 0,
                    tempPlanks, tempPlanks, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    1, 2, DEPTH - 1,
                    WIDTH - 2, HEIGHT - 1, DEPTH - 1,
                    tempPlanks, tempPlanks, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 2, 1,
                    0, HEIGHT - 1, DEPTH - 2,
                    tempPlanks, tempPlanks, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    WIDTH - 1, 2, 1,
                    WIDTH - 1, HEIGHT - 1, DEPTH - 2,
                    tempPlanks, tempPlanks, false);

            // Entrance
            this.fillWithAir(worldIn, structureBoundingBoxIn,
                    2, 2, 0,
                    2, 3, 0);

            // Pane
            this.setBlockState(worldIn, tempPane,
                    4, 3, 0, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 3, 3,
                    0, 3, DEPTH - 4,
                    tempPane, tempPane, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    WIDTH - 1, 3, 3,
                    WIDTH - 1, 3, DEPTH - 4,
                    tempPane, tempPane, false);

            // Deco
            // Clear interior
            this.fillWithAir(worldIn, structureBoundingBoxIn,
                    1, 2, 1,
                    WIDTH - 2, HEIGHT - 1, DEPTH - 2);
            // Others
            this.setBlockState(worldIn, Blocks.CAULDRON.getDefaultState(),
                    2, 2, DEPTH - 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(),
                    3, 2, DEPTH - 2, structureBoundingBoxIn);
            // Mushroom pot
            BlockPos pos = new BlockPos(
                    this.getXWithOffset(5, DEPTH - 2),
                    this.getYWithOffset(2),
                    this.getZWithOffset(5, DEPTH - 2));
            if (structureBoundingBoxIn.isVecInside(pos) && worldIn.getBlockState(pos).getBlock() != Blocks.FLOWER_POT){
                // Set pot
                this.setBlockState(worldIn, Blocks.FLOWER_POT.getDefaultState(), 5, 2, DEPTH - 2, structureBoundingBoxIn);
                // Get tile entity
                TileEntity tempTileEntity = worldIn.getTileEntity(pos);
                if (tempTileEntity instanceof TileEntityFlowerPot){
                    // Set block
                    ((TileEntityFlowerPot)tempTileEntity).setItemStack(new ItemStack(Blocks.BROWN_MUSHROOM));
                }
            }
            // Chest
            pos = new BlockPos(
                    this.getXWithOffset(1, DEPTH - 2),
                    this.getYWithOffset(2),
                    this.getZWithOffset(1, DEPTH - 2));
            ResourceLocation locationChest = new ResourceLocation(this.namespace, this.location);
            if(!this.placedChest){
                this.placedChest = this.generateChest(worldIn, structureBoundingBoxIn, randomIn,
                        pos, locationChest,
                        Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, this.getCoordBaseMode().getOpposite()));
            }

            // Fill with pillar
            this.replaceAirAndLiquidDownwards(worldIn, tempLogY,
                    0, 0, 0,
                    structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, tempLogY,
                    WIDTH - 1, 0, 0,
                    structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, tempLogY,
                    0, 0, DEPTH - 1,
                    structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(worldIn, tempLogY,
                    WIDTH - 1, 0, DEPTH - 1,
                    structureBoundingBoxIn);

            // Spawn a witch
            if (!this.hasWitch)
            {
                int ix = this.getXWithOffset(3, 4);
                int iy = this.getYWithOffset(2);
                int iz = this.getZWithOffset(3, 4);

                if (structureBoundingBoxIn.isVecInside(new BlockPos(ix, iy, iz)))
                {
                    this.hasWitch = true;
                    EntityWitch entitywitch = new EntityWitch(worldIn);
                    entitywitch.enablePersistence();
                    entitywitch.setLocationAndAngles((double)ix + 0.5D, (double)iy, (double)iz + 0.5D, 0.0F, 0.0F);
                    entitywitch.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(ix, iy, iz)), (IEntityLivingData)null);
                    worldIn.spawnEntity(entitywitch);
                }
            }

            return true;
        }
    }

    public static class Veranda extends StructureComponent{
        private static final int WIDTH = 7;
        private static final int DEPTH = 3;

        private String blockGenName;

        public Veranda(){}

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound){
            tagCompound.setString("blockGenName", this.blockGenName);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager){
            this.blockGenName = tagCompound.getString("blockGenName");
        }

        public Veranda(int x, int y, int z,
                       EnumFacing coordBaseModeIn,
                       String blockGenName){
            super(1);

            // Initialize variables
            this.blockGenName = blockGenName;

            // Set facing of this veranda
            this.setCoordBaseMode(coordBaseModeIn);

            // Setting the bounding box
            if(this.getCoordBaseMode() == EnumFacing.SOUTH){
                this.boundingBox = new StructureBoundingBox(x, y, z - (DEPTH - 1),
                        x + (WIDTH - 1), y + 1, z);
            }
            else if(this.getCoordBaseMode() == EnumFacing.WEST){
                this.boundingBox = new StructureBoundingBox(x, y, z,
                        x + (DEPTH - 1), y + 1, z + (WIDTH - 1));
            }
            else if(this.getCoordBaseMode() == EnumFacing.NORTH){
                this.boundingBox = new StructureBoundingBox(x, y, z,
                        x + (WIDTH - 1), y + 1, z + (DEPTH - 1));
            }
            else{
                this.boundingBox = new StructureBoundingBox(x - (DEPTH - 1), y, z,
                        x, y + 1, z + (WIDTH - 1));
            }
        }

        @Override
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
            INCBlockGenSwampHouse blockGen = NCMapGenSwampHouse.getBlockGen(this.blockGenName);
            blockGen.setSeed(randomIn.nextLong());

            // List up the BlockStates
            IBlockState tempPlanks = blockGen.getMainBlockState();
            IBlockState tempFence = blockGen.getFenceBlockState();
            IBlockState tempAir = Blocks.AIR.getDefaultState();

            // Generate
            // Base
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 0, 0,
                    WIDTH - 1, 0, DEPTH - 1,
                    tempPlanks, tempPlanks, false);
            this.setBlockState(worldIn, tempAir,
                    0, 0, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, tempAir,
                    WIDTH - 1, 0, 0, structureBoundingBoxIn);
            // Deco
            // Mushroom pot
            BlockPos pos = new BlockPos(
                    this.getXWithOffset(5, DEPTH - 1),
                    this.getYWithOffset(1),
                    this.getZWithOffset(5, DEPTH - 1));
            if (structureBoundingBoxIn.isVecInside(pos) && worldIn.getBlockState(pos).getBlock() != Blocks.FLOWER_POT){
                // Set pot
                this.setBlockState(worldIn, Blocks.FLOWER_POT.getDefaultState(), 5, 1, DEPTH - 1, structureBoundingBoxIn);
                // Get tile entity
                TileEntity tempTileEntity = worldIn.getTileEntity(pos);
                if (tempTileEntity instanceof TileEntityFlowerPot){
                    // Set block
                    ((TileEntityFlowerPot)tempTileEntity).setItemStack(new ItemStack(Blocks.RED_MUSHROOM));
                }
            }
            // Fence
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 1, 1,
                    0, 1, 2,
                    tempFence, tempFence, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    WIDTH - 1, 1, 1,
                    WIDTH - 1, 1, 2,
                    tempFence, tempFence, false);

            return true;
        }
    }

    public static class Roof extends StructureComponent{
        private static final int WIDTH = 9;
        private static final int DEPTH = 12;

        private String blockGenName;

        public Roof(){}

        @Override
        protected void writeStructureToNBT(NBTTagCompound tagCompound){
            tagCompound.setString("blockGenName", this.blockGenName);
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager){
            this.blockGenName = tagCompound.getString("blockGenName");
        }

        public Roof(int x, int y, int z,
                    EnumFacing coordBaseModeIn,
                    String blockGenName){
            super(1);

            // Initialize variables
            this.blockGenName = blockGenName;

            // Randomize facing of the house
            this.setCoordBaseMode(coordBaseModeIn);

            // Setting the bounding box
            if(this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z){
                this.boundingBox = new StructureBoundingBox(x - 1, y, z - 1,
                        x - 1 + WIDTH - 1, y, z - 1 + DEPTH - 1);
            }
            else{
                this.boundingBox = new StructureBoundingBox(x - 1, y, z - 1,
                        x - 1 + DEPTH - 1, y, z - 1 + WIDTH - 1);
            }
        }

        @Override
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn){
            INCBlockGenSwampHouse blockGen = NCMapGenSwampHouse.getBlockGen(this.blockGenName);
            blockGen.setSeed(randomIn.nextLong());

            // List up the BlockStates
            IBlockState tempPlanks = blockGen.getMainBlockState();
            IBlockState tempStairsN = blockGen.getStairsBlockStateFromDir(EnumFacing.NORTH);
            IBlockState tempStairsE = blockGen.getStairsBlockStateFromDir(EnumFacing.EAST);
            IBlockState tempStairsS = blockGen.getStairsBlockStateFromDir(EnumFacing.SOUTH);
            IBlockState tempStairsW = blockGen.getStairsBlockStateFromDir(EnumFacing.WEST);

            // Generate
            // Roof
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    1, 0, 1,
                    WIDTH - 2, 0, DEPTH - 2,
                    tempPlanks, tempPlanks, false);
            // Edge
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 0, 0,
                    WIDTH - 1, 0, 0,
                    tempStairsN, tempStairsN, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 0, DEPTH - 1,
                    WIDTH - 1, 0, DEPTH - 1,
                    tempStairsS, tempStairsS, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    0, 0, 1,
                    0, 0, DEPTH - 2,
                    tempStairsE, tempStairsE, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn,
                    WIDTH - 1, 0, 1,
                    WIDTH - 1, 0, DEPTH - 2,
                    tempStairsW, tempStairsW, false);

            return true;
        }
    }
}
