package bgs.formalspecificationide.tutorial3.world;


import java.util.*;

public class World {
    private static World instance;
//    private final List<Tank> allTanks = new ArrayList<>();

    private String currentNodeType;

    public static World getInstance() {
        // Result variable here may seem pointless, but it's needed for DCL (Double-checked locking).
        var result = instance;
        if (instance != null) {
            return result;
        }
        synchronized (World.class) {
            if (instance == null) {
                instance = new World();
            }
            return instance;
        }
    }

    public void setCurrentNodeType(String nodeType){
        currentNodeType = nodeType;
    }

    public String getCurrentNodeType() {
        return currentNodeType;
    }

    //    public void addTank(Tank tank) {
//        synchronized (allTanks) {
//            allTanks.add(tank);
//        }
//    }
//
//    public void deleteTank(Tank tank) {
//        synchronized (allTanks) {
//            allTanks.remove(tank);
//        }
//    }
//
//    public List<Tank> getTanks() {
//        synchronized (allTanks) {
//            return allTanks;
//        }
//    }


}
