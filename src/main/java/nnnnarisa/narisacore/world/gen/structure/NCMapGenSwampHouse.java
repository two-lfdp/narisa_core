package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.*;

public class NCMapGenSwampHouse extends MapGenStructure {
    private static final Map<String, INCBlockGenSwampHouse> BLOCK_GEN = new HashMap<>();
    private final int fixedHeight;
    private final int minDist, maxDist;
    private final String namespace, location;
    private final String blockGenName;
    private final List<Biome> filterBiomes;

    public NCMapGenSwampHouse(int fixedHeight, int minDist, int maxDist,
                              String namespace, String location,
                              String blockGenName){
        super();

        this.fixedHeight = fixedHeight;
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.namespace = namespace;
        this.location = location;
        this.blockGenName = blockGenName;
        this.filterBiomes = new LinkedList<Biome>();
    }

    @Override
    public String getStructureName(){
        return "NCSwampHouse";
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
        int iz = (int)Math.floor((double)chunkZ / this.maxDist);
        ix *= this.maxDist;
        iz *= this.maxDist;
        this.rand.setSeed(ix ^ iz ^ this.world.getSeed() ^ "warlock".hashCode());
        ix += this.rand.nextInt(this.maxDist - this.minDist);
        iz += this.rand.nextInt(this.maxDist - this.minDist);

        if (chunkX == ix && chunkZ == iz){
            Biome posBiome = this.world.getBiomeProvider().getBiome(new BlockPos((ix << 4) + 8, 0, (iz << 4) + 8));

            if (this.filterBiomes.isEmpty()){
                return true;
            }

            if (posBiome == null){
                return false;
            }

            for (Biome listBiome : this.filterBiomes){
                if (posBiome == listBiome){
                    return true;
                }
            }
        }

        return false;
    }
    // */

    /*
    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ){
        return (chunkX % 10 == 0 && chunkZ % 10 == 0);
    }
    // */

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ){
        return new NCMapGenSwampHouse.Start(
                this.rand, chunkX, chunkZ,
                this.fixedHeight, this.namespace, this.location,
                this.blockGenName);
    }

    public void addFilterBiome(Biome biome){
        this.filterBiomes.add(biome);
    }

    public static void addBlockGen(String name, INCBlockGenSwampHouse blockGen){
        BLOCK_GEN.put(name, blockGen);
    }

    public static INCBlockGenSwampHouse getBlockGen(String name){
        return BLOCK_GEN.get(name);
    }

    public static class Start extends StructureStart{
        public Start(){}

        public Start(Random random, int chunkX, int chunkZ,
                     int fixedHeight, String namespace, String location,
                     String blockGenName){
            super(chunkX, chunkZ);

            StructureSwampHousePieces.Core startPiece =
                    new StructureSwampHousePieces.Core(
                            random,
                            chunkX*16, fixedHeight, chunkZ*16,
                            namespace, location,
                            blockGenName);
            this.components.add(startPiece);
            startPiece.buildComponent(startPiece, this.components, random);
            this.updateBoundingBox();
        }
    }
}
