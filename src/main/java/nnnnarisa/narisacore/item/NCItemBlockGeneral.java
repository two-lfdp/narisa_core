package nnnnarisa.narisacore.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import nnnnarisa.narisacore.NarisaCore;
import nnnnarisa.narisacore.block.INCBlockMulti;

public class NCItemBlockGeneral extends ItemBlock{
    public NCItemBlockGeneral(Block block, boolean hasSubtypes){
        super(block);

        if(hasSubtypes){
            setHasSubtypes(true);
            setMaxDamage(0);
        }
        setCreativeTab(NarisaCore.TAB_NARISACORE);
    }

    @Override
    public int getMetadata(int damage){
        if(getHasSubtypes()){
            return damage;
        }
        return 0;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        if(block instanceof INCBlockMulti){
            return ((INCBlockMulti)block).getUnlocalizedName(stack.getMetadata());
        }
        return block.getUnlocalizedName();
    }
}
