package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NCBlockMulti extends Block{
    public NCBlockMulti(Material material){
        super(material);
    }

    public String getUnlocalizedName(int meta){
        return super.getUnlocalizedName() + "." + getMetaName(meta);
    }

    protected String getMetaName(int meta){
        return "default";
    }
}
