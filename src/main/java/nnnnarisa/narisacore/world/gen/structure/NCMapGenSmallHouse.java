package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Random;

public class NCMapGenSmallHouse extends MapGenStructure {
    private final int fixedHeight;
    private final int minDist, maxDist;
    private final String namespace, location;

    public NCMapGenSmallHouse(int fixedHeight, int minDist, int maxDist, String namespace, String location){
        super();

        this.fixedHeight = fixedHeight;
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.namespace = namespace;
        this.location = location;
    }

    @Override
    public String getStructureName(){
        return "NCSmallHouse";
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        int chunkX = pos.getX() >> 4;
        int chunkZ = pos.getZ() >> 4;

        for(int r = 0; r <= 500; ++r){
            for(int ix = -r; ix <= r; ++ix){
                for(int iz = -r; iz <= r; ++iz){
                    if((ix == -r || ix == r) || (iz == -r || iz == r)){
                        int jx = chunkX + ix;
                        int jz = chunkZ + iz;
                        this.rand.setSeed((long)(jx ^ jz) ^ worldIn.getSeed());
                        this.rand.nextInt();

                        if (this.canSpawnStructureAtCoords(jx, jz) && (!findUnexplored || !worldIn.isChunkGeneratedAt(jx, jz))){
                            return new BlockPos((jx << 4) + 8, 64, (jz << 4) + 8);
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ){
        int ix = (int)Math.floor((double)chunkX / this.maxDist);
        int iz = (int)Math.floor((double)chunkX / this.maxDist);
        ix *= this.maxDist;
        iz *= this.maxDist;
        this.rand.setSeed(ix ^ iz ^ this.world.getSeed());
        ix += this.rand.nextInt(this.maxDist - this.minDist);
        iz += this.rand.nextInt(this.maxDist - this.minDist);

        return (chunkX == ix && chunkZ == iz);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ){
        return new NCMapGenSmallHouse.Start(this.rand, chunkX, chunkZ, this.fixedHeight, this.namespace, this.location);
    }

    public static class Start extends StructureStart{
        public Start(){}

        public Start(Random random, int chunkX, int chunkZ,
                     int fixedHeight, String namespace, String location){
            super(chunkX, chunkZ);

            StructureSmallHousePieces.House startPiece =
                    new StructureSmallHousePieces.House(
                            random,
                            chunkX*16, fixedHeight, chunkZ*16,
                            namespace, location);
            this.components.add(startPiece);
            this.updateBoundingBox();
        }
    }
}
