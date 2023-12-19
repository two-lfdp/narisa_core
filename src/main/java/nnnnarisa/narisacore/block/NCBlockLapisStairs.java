package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockQuartz.EnumType;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraftforge.registries.IForgeRegistry;
import nnnnarisa.narisacore.NarisaCore;

public class NCBlockLapisStairs extends BlockStairs{
    public NCBlockLapisStairs(NCBlockDecorativeLapis block){
        super(block.getDefaultState().withProperty(BlockQuartz.VARIANT, EnumType.DEFAULT));

        setUnlocalizedName("lapisStairs");
        setRegistryName(NarisaCore.MODID, "lapis_stairs");
        setCreativeTab(NarisaCore.TAB_NARISACORE);

        setSoundType(SoundType.STONE);

        setHarvestLevel("pickaxe", 1);
    }

    public void registerBlocks(IForgeRegistry<Block> registry){
        registry.register(this);
    }
}
