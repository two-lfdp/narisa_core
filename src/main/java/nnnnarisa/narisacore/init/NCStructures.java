package nnnnarisa.narisacore.init;

import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import nnnnarisa.narisacore.world.gen.structure.*;

public class NCStructures {
    public static void registerStructures(){
        NCMapGenSmallHouse.addBlockGen("oak", new ImplSmallHouseOak());
        NCMapGenGiantPyramid.addBlockGen("yellow", new ImplGiantPyramidYellow());
        MapGenStructureIO.registerStructure(NCMapGenSmallHouse.Start.class, "NCSmallHouse");
        MapGenStructureIO.registerStructure(NCMapGenGiantPyramid.Start.class, "NCRefinedPyramid");
        StructureSmallHousePieces.registerSmallHousePieces();
        StructureGiantPyramidPieces.registerGiantPyramidPieces();
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
}
