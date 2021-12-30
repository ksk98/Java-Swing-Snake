package entities.size;

import enums.BoardSize;

import java.util.HashMap;
import java.util.Set;

public abstract class SizeRepo {
    private static HashMap<String, Size> sizes = null;

    public static Set<String> getSizeNames() {
        if (sizes == null)
            initialise();

        return sizes.keySet();
    }

    public static Size getSize(String name) {
        if (sizes == null)
            initialise();

        return sizes.get(name);
    }

    public static Size getSize(BoardSize boardSize) {
        switch (boardSize) {
            case LARGE:
                return getSize("LARGE");
            case MEDIUM:
                return getSize("MEDIUM");
            case SMALL:
            default:
                return getSize("SMALL");
        }
    }

    private static void initialise() {
        sizes = new HashMap<>();
        sizes.put("SMALL", new SizeImpl(16, 8, "SMALL"));
        sizes.put("MEDIUM", new SizeImpl(24, 12, "MEDIUM"));
        sizes.put("LARGE", new SizeImpl(32, 16, "LARGE"));
    }
}
