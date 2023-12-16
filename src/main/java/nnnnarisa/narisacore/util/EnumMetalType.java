package nnnnarisa.narisacore.util;

import net.minecraft.util.IStringSerializable;

public enum EnumMetalType implements IStringSerializable{
    COPPER("copper", "Copper", 1),
    TIN("tin", "Tin", 1),
    ALUMINUM("aluminum", "Aluminum", 1),
    LEAD("lead", "Lead", 2),
    SILVER("silver", "Silver", 2),
    BRONZE("bronze", "Bronze", 2),
    ELECTRUM("electrum", "Electrum", 2),
    ALUBRASS("alubrass", "Alubrass", 1),
    STEEL("steel", "Steel", 2);

    private final String lowerName, headCapitalName;
    private final int blockLevel;

    EnumMetalType(String lowerName, String headCapitalName, int blockLevel) {
        this.lowerName = lowerName;
        this.headCapitalName = headCapitalName;
        this.blockLevel = blockLevel;
    }

    public String getLowerName() {
        return lowerName;
    }

    public String getHeadCapitalName() {
        return headCapitalName;
    }

    public int getBlockLevel() {
        return blockLevel;
    }

    @Override
    public String getName() {
        return lowerName;
    }
}
