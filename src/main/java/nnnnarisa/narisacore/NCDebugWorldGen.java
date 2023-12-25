package nnnnarisa.narisacore;

import java.util.Random;

import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nnnnarisa.narisacore.world.gen.feature.*;

public class NCDebugWorldGen {
    private NCWorldGenSpikes testGenerator1 = null;
    private NCWorldGenSpikes testGenerator2 = null;
    private NCWorldGenSpikes testGenerator3 = null;
    private NCWorldGenMonolith testGenerator4 = null;

    public void init(){
        // NCWorldGenSpikes
        IBlockState stone = Blocks.STONE.getDefaultState();
        testGenerator1 = new NCWorldGenSpikes(
                Blocks.GLOWSTONE.getDefaultState(),
                NCWorldGenSpikes.EnumSize.SMALL);
        testGenerator2 = new NCWorldGenSpikes(
                stone.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE_SMOOTH),
                NCWorldGenSpikes.EnumSize.MIDDLE);
        testGenerator3 = new NCWorldGenSpikes(
                stone,
                NCWorldGenSpikes.EnumSize.LARGE);
        // NCWorldGenMonolith
        testGenerator4 = new NCWorldGenMonolith(Blocks.GLOWSTONE.getDefaultState(),
                2, 6);
    }

    @SubscribeEvent
    public void onPostBiomeDecorate(DecorateBiomeEvent.Post event){
        generateSpike(event);
        //generateMonolith(event);
    }

    /*
    @SubscribeEvent
    public void onMapGenInit(InitMapGenEvent event){
        if(event.getType() == InitMapGenEvent.EventType.SCATTERED_FEATURE){
            event.setNewGen(new NCDebugDummyMapGen(68, 3, 20, "minecraft", "chests/simple_dungeon"));
        }
    }
    // */

    private void generateSpike(DecorateBiomeEvent.Post event){
        Random rand = event.getRand();
        ChunkPos chunkPos = event.getChunkPos();
        BlockPos pos = new BlockPos((chunkPos.x << 4) + rand.nextInt(16) + 8,
                0, (chunkPos.z << 4) + rand.nextInt(16) + 8);
        int randVar = rand.nextInt(3);
        switch(randVar){
            case 1:
                testGenerator1.generate(event.getWorld(), rand, pos);
                break;
            case 2:
                testGenerator2.generate(event.getWorld(), rand, pos);
                break;
            default:
                testGenerator3.generate(event.getWorld(), rand, pos);
        }
    }

    private void generateMonolith(DecorateBiomeEvent.Post event){
        Random rand = event.getRand();
        ChunkPos chunkPos = event.getChunkPos();
        BlockPos pos = new BlockPos((chunkPos.x << 4) + rand.nextInt(16) + 8,
                rand.nextInt(256 - 16 * 2) + 16,
                (chunkPos.z << 4) + rand.nextInt(16) + 8);
        for(int i = 0 ; i < 4 ; i++){
            int randVar = rand.nextInt(8);
            if(randVar == 0){
                testGenerator4.generate(event.getWorld(), rand, pos);
            }
        }
    }
}
