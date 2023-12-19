package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCBlockDecorativeLapis extends BlockQuartz implements INCBlockMulti{
    public NCBlockDecorativeLapis(){
        super();

        setUnlocalizedName("lapisDeco");
        setRegistryName(NarisaCore.MODID, "lapis_deco");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);

        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos){
        return MapColor.LAPIS;
    }

    @Override
    public String getUnlocalizedName(int meta){
        return super.getUnlocalizedName() + "." + EnumType.byMetadata(meta);
    }

    public void registerBlocks(IForgeRegistry<Block> registry){
        registry.register(this);
    }
}
