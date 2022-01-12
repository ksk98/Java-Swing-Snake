package managers.Tiles;


import java.net.URISyntaxException;
import java.util.HashMap;
public class TileSetRepo {
    private static final TileSetRepo instance = new TileSetRepo();

    private final HashMap<String, TileSet> sets;

    private TileSetRepo() {
        sets = new HashMap<>();
        try {
            sets.put("DESERT", new DesertTileSet());
            sets.put("GRASS", new GrassTileSet());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static TileSetRepo getInstance() {
        return instance;
    }

    public TileSet getTileSet(String tileSet) {
        return sets.get(tileSet);
    }

    public String[] getSets() {
        return new String[] {"DESERT", "GRASS"};
    }
}
