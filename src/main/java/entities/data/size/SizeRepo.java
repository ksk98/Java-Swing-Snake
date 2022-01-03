package entities.data.size;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class SizeRepo {
    private static HashMap<BoardSize, Size> sizes = null;
    private static List<BoardSize> order;

    public static String[] getSizeNames() {
        if (sizes == null)
            initialise();

        String[] out = new String[sizes.size()];

        for (int i = 0; i < order.size(); i++)
            out[i] = order.get(i).toString();

        return out;
    }

    public static Size getSize(BoardSize boardSize) {
        if (sizes == null)
            initialise();

        return sizes.get(boardSize);
    }

    public static Size getSize(String size) {
        if (sizes == null)
            initialise();

        for (BoardSize bs: sizes.keySet()) {
            if (bs.toString().equals(size))
                return sizes.get(bs);
        }

        return null;
    }

    private static void initialise() {
        sizes = new HashMap<>();
        order = new LinkedList<>();
        sizes.put(BoardSize.SMALL, new SizeImpl(16, 8, "SMALL"));
        order.add(BoardSize.SMALL);
        sizes.put(BoardSize.MEDIUM, new SizeImpl(24, 12, "MEDIUM"));
        order.add(BoardSize.MEDIUM);
        sizes.put(BoardSize.LARGE, new SizeImpl(28, 14, "LARGE"));
        order.add(BoardSize.LARGE);
    }
}
