package world;

import java.util.HashMap;

public enum TileType {

    TREE1(32, false, "Tree1"),
    TREE2(21, false, "Tree1"),
    STUMP(9, false, "Stump"),
    NAMEPLATE(82, false, "Nameplate"),
    CHEST(79, false, "Chest");

    public static final int TILE_SIZE = 16;
    private int id;
    private boolean collidable;
    private String name;


    private TileType(int id, boolean collidable, String name) {
        this.id = id;
        this.collidable = collidable;
        this.name = name;
    }

    private static HashMap<Integer, TileType> tileMap;

    static {
        tileMap = new HashMap<Integer, TileType>();
        for (TileType tileType : TileType.values()) {
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeByID(int id) {
        return tileMap.get(id);
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }
}
