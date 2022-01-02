package entities.difficulty;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class DifficultyRepo {
    private static HashMap<DifficultyLevel, Difficulty> difficulties = null;
    private static List<DifficultyLevel> order;

    public static String[] getDifficulties() {
        if (difficulties == null)
            initialise();

        String[] out = new String[difficulties.size()];

        for (int i = 0; i < order.size(); i++)
            out[i] = order.get(i).toString();

        return out;
    }

    public static Difficulty getDifficulty(DifficultyLevel difficulty) {
        if (difficulties == null)
            initialise();

        return difficulties.get(difficulty);
    }

    public static Difficulty getDifficulty(String difficulty) {
        if (difficulty == null)
            initialise();

        for (DifficultyLevel dl: difficulties.keySet()) {
            if (dl.toString().equals(difficulty))
                return difficulties.get(dl);
        }

        return null;
    }

    private static void initialise() {
        difficulties = new HashMap<>();
        order = new LinkedList<>();
        difficulties.put(DifficultyLevel.EASY, new DifficultyImpl(0, 1, 1, "EASY"));
        order.add(DifficultyLevel.EASY);
        difficulties.put(DifficultyLevel.MEDIUM, new DifficultyImpl(4, 2, 2, "MEDIUM"));
        order.add(DifficultyLevel.MEDIUM);
        difficulties.put(DifficultyLevel.HARD, new DifficultyImpl(8, 3, 3, "HARD"));
        order.add(DifficultyLevel.HARD);
    }
}
