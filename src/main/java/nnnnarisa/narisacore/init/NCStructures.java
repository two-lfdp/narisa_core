package nnnnarisa.narisacore.init;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import nnnnarisa.narisacore.world.gen.structure.*;

public class NCStructures {
    public static void registerStructures(){
        NCMapGenSmallHouse.addBlockGen("oak", new ImplSmallHouseOak());
        NCMapGenGiantPyramid.addBlockGen("yellow", new ImplGiantPyramidYellow());
        NCMapGenSwampHouse.addBlockGen("normal", new ImplSwampHouseNormal());
        MapGenStructureIO.registerStructure(NCMapGenSmallHouse.Start.class, "NCSmallHouse");
        MapGenStructureIO.registerStructure(NCMapGenGiantPyramid.Start.class, "NCRefinedPyramid");
        MapGenStructureIO.registerStructure(NCMapGenSwampHouse.Start.class, "NCSwampHouse");
        StructureSmallHousePieces.registerSmallHousePieces();
        StructureGiantPyramidPieces.registerGiantPyramidPieces();
        StructureSwampHousePieces.registerSwampHousePieces();
    }

    private static class ImplSmallHouseOak implements INCBlockGenSmallHouse{
        @Override
        public void setSeed(long seed) {
            /* dummy */
        }

        @Override
        public IBlockState getWallBlockState() {
            return Blocks.PLANKS.getDefaultState();
        }

        @Override
        public IBlockState getFloorCeilBlockState() {
            return Blocks.STONEBRICK.getDefaultState();
        }

        @Override
        public IBlockState getLightBlockState() {
            return Blocks.GLOWSTONE.getDefaultState();
        }

        @Override
        public IBlockState getPillarBlockState() {
            return Blocks.LOG.getDefaultState();
        }

        @Override
        public IBlockState getGlassPaneBlockState() {
            return Blocks.GLASS_PANE.getDefaultState();
        }

        @Override
        public BlockDoor getDoorBlock() {
            return Blocks.OAK_DOOR;
        }
    }

    private static class ImplGiantPyramidYellow implements INCBlockGenGiantPyramid{
        @Override
        public void setSeed(long seed) {
            /* dummy */
        }

        @Override
        public IBlockState getMainBlockState(){
            return Blocks.SANDSTONE.getDefaultState();
        }

        @Override
        public IBlockState getSmoothBlockState() {
            return Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH);
        }

        @Override
        public IBlockState getChiseledBlockState() {
            return Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.CHISELED);
        }

        @Override
        public IBlockState getTerracottaBlockState() {
            return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
        }

        @Override
        public IBlockState getAccentTerracottaBlockState() {
            return Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BLUE);
        }
    }

    private static class ImplSwampHouseNormal implements INCBlockGenSwampHouse{
        @Override
        public void setSeed(long seed) {
            /* dummy */
        }

        @Override
        public IBlockState getMainBlockState() {
            return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
        }

        @Override
        public IBlockState getLogYAxisBlockState() {
            return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
        }

        @Override
        public IBlockState getLogXAxisBlockState() {
            return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
        }

        @Override
        public IBlockState getLogZAxisBlockState() {
            return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
        }

        @Override
        public IBlockState getPaneBlockState() {
            return Blocks.GLASS_PANE.getDefaultState();
        }

        @Override
        public IBlockState getFenceBlockState() {
            return Blocks.SPRUCE_FENCE.getDefaultState();
        }

        @Override
        public IBlockState getStairsBlockStateFromDir(EnumFacing direction) {
            if (direction == null || direction == EnumFacing.UP || direction == EnumFacing.DOWN){
                return Blocks.SPRUCE_STAIRS.getDefaultState();
            }
            return Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, direction);
        }
    }
}
