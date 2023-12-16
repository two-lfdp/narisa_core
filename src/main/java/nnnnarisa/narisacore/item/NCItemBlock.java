package nnnnarisa.narisacore.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.block.NCBlockMulti;

public class NCItemBlock extends ItemBlock
{
    public NCItemBlock(Block block, boolean hasSubtypes){
        super(block);

        if(hasSubtypes){
            setHasSubtypes(true);
            setMaxDamage(0);
        }
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    @Override
    public int getMetadata(int damage){
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        if(block instanceof NCBlockMulti){
            return ((NCBlockMulti)block).getUnlocalizedName(stack.getMetadata());
        }
        return block.getUnlocalizedName();
    }
}
