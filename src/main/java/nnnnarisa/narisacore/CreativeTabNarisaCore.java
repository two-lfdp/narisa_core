package nnnnarisa.narisacore;

import nnnnarisa.narisacore.init.NCItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabNarisaCore extends CreativeTabs{
    public CreativeTabNarisaCore(){
        super("narisacore");
    }

    @Override
    public ItemStack getTabIconItem(){
        return new ItemStack(NCItems.INGOT, 1, 0);
    }
}
