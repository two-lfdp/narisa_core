package nnnnarisa.narisacore.util;

import net.minecraft.util.IStringSerializable;

public enum EnumGemType implements IStringSerializable{
    RUBY("ruby", "Ruby"),
    TOPAZ("topaz", "Topaz"),
    AMBER("amber", "Amber"),
    PERIDOT("peridot", "Peridot"),
    MALACHITE("malachite", "Malachite"),
    SAPPHIRE("sapphire", "Sapphire"),
    TUNZERNITE("tunzernite", "Tunzernite", "Tanzanite");

    private final String lowerName, headCapitalName, oreDictName;

    EnumGemType(String lowerName, String headCapitalName) {
        this(lowerName, headCapitalName, headCapitalName);
    }

    EnumGemType(String lowerName, String headCapitalName, String oreDictName) {
        this.lowerName = lowerName;
        this.headCapitalName = headCapitalName;
        this.oreDictName = oreDictName;
    }

    public String getLowerName() {
        return lowerName;
    }

    public String getHeadCapitalName() {
        return headCapitalName;
    }

    public String getOreDictName() {
        return oreDictName;
    }

    @Override
    public String getName() {
        return lowerName;
    }
}
