package nnnnarisa.narisacore.init;

import net.minecraft.world.gen.structure.MapGenStructureIO;
//import nnnnarisa.narisacore.NCDebugDummyMapGen; // DEBUG
import nnnnarisa.narisacore.world.gen.structure.*;

public class NCStructures {
    public static void registerStructures(){
        MapGenStructureIO.registerStructure(NCMapGenSmallHouse.Start.class, "NCSmallHouse"); // DEBUG
        StructureSmallHousePieces.registerSmallHousePieces();
    }
}
