package nnnnarisa.narisacore.util;

public enum EnumMetalType {
    COPPER("copper", "Copper"),
    TIN("tin", "Tin"),
    ALUMINUM("aluminum", "Aluminum"),
    LEAD("lead", "Lead"),
    SILVER("silver", "Silver"),
    BRONZE("bronze", "Bronze"),
    ELECTRUM("electrum", "Electrum"),
    ALUBRASS("alubrass", "Alubrass"),
    STEEL("steel", "Steel");

    private final String lowerName, headCapitalName;

    EnumMetalType(String lowerName, String headCapitalName) {
        this.lowerName = lowerName;
        this.headCapitalName = headCapitalName;
    }

    public String getLowerName() {
        return lowerName;
    }

    public String getHeadCapitalName() {
        return headCapitalName;
    }
}
