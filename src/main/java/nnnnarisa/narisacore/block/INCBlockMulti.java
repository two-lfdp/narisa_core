package nnnnarisa.narisacore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public interface INCBlockMulti {
    String getUnlocalizedName(int meta);

    abstract class Impl extends Block implements INCBlockMulti{
        public Impl(Material material){
            super(material);
        }

        public Impl(Material material, MapColor mapColor){
            super(material, mapColor);
        }

        public String getUnlocalizedName(int meta){
            return super.getUnlocalizedName() + "." + getMetaName(meta);
        }

        abstract protected String getMetaName(int meta);
    }

}
