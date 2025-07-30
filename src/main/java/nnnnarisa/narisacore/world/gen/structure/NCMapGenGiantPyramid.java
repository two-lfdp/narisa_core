package nnnnarisa.narisacore.world.gen.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.*;

public class NCMapGenGiantPyramid extends MapGenStructure {
    private static final Map<String, INCBlockGenGiantPyramid> BLOCK_GEN = new HashMap<>();
    private final int minHeight, maxHeight;
    private final int minDist, maxDist;
    private final boolean isFloating;
    private final String blockGenName;
    private final List<Biome> filterBiomes;

    public NCMapGenGiantPyramid(int minHeight, int maxHeight, int minDist, int maxDist,
                                boolean isFloating, String blockGenName){
        super();

        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.isFloating = isFloating;
        this.blockGenName = blockGenName;
        this.filterBiomes = new LinkedList<Biome>();
    }

    @Override
    public String getStructureName(){
        return "NCGiantPyramid";
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
        this.rand.setSeed(ix ^ iz ^ this.world.getSeed() ^ "pirami".hashCode());
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
        return new NCMapGenGiantPyramid.Start(
                this.rand, chunkX, chunkZ,
                this.minHeight, this.maxHeight,
                this.isFloating, this.blockGenName);
    }

    public void addFilterBiome(Biome biome){
        this.filterBiomes.add(biome);
    }

    public static void addBlockGen(String name, INCBlockGenGiantPyramid blockGen){
        BLOCK_GEN.put(name, blockGen);
    }

    public static INCBlockGenGiantPyramid getBlockGen(String name){
        return BLOCK_GEN.get(name);
    }

    public static class Start extends StructureStart{
        public Start(){}

        public Start(Random random, int chunkX, int chunkZ,
                     int minHeight, int maxHeight,
                     boolean isFloating, String blockGenName){
            super(chunkX, chunkZ);

            StructureGiantPyramidPieces.Pyramid startPiece =
                    new StructureGiantPyramidPieces.Pyramid(random,
                            chunkX * 16, MathHelper.getInt(random, minHeight, maxHeight), chunkZ * 16,
                            isFloating, blockGenName);
            this.components.add(startPiece);
            this.updateBoundingBox();
        }
    }
}
