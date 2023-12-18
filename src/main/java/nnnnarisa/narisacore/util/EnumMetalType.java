package nnnnarisa.narisacore.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.IStringSerializable;

public enum EnumMetalType implements IStringSerializable{
    COPPER("copper", "Copper", 1, false),
    TIN("tin", "Tin", 1, false),
    ALUMINUM("aluminum", "Aluminum", 1, false),
    LEAD("lead", "Lead", 2, false),
    SILVER("silver", "Silver", 2, false),
    BRONZE("bronze", "Bronze", 2, true),
    ELECTRUM("electrum", "Electrum", 2, true),
    ALUBRASS("alubrass", "Alubrass", 1, true),
    STEEL("steel", "Steel", 2, true);

    private final String lowerName, headCapitalName;
    private final int blockLevel;
    private final boolean isAlloy;

    EnumMetalType(String lowerName, String headCapitalName, int blockLevel, boolean isAlloy) {
        this.lowerName = lowerName;
        this.headCapitalName = headCapitalName;
        this.blockLevel = blockLevel;
        this.isAlloy = isAlloy;
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

    public boolean isAlloy(){
        return isAlloy;
    }

    @Override
    public String getName() {
        return lowerName;
    }

    public static EnumMetalType[] getSimpleMetal(){
        return new EnumMetalType[]{COPPER, TIN, ALUMINUM, LEAD, SILVER};
    }

    public static List<EnumMetalType> getSimpleMetalList(){
        List<EnumMetalType> result = new ArrayList<>();

        result.add(COPPER);
        result.add(TIN);
        result.add(ALUMINUM);
        result.add(LEAD);
        result.add(SILVER);

        return result;
    }
}
