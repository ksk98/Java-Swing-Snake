package entities.difficulty;

import enums.DifficultyLevel;

import java.util.HashMap;
import java.util.Set;

public abstract class DifficultyRepo {
    private static HashMap<String, Difficulty> difficulties = null;

    public static Set<String> getDifficultyNames() {
        if (difficulties == null)
            initialise();

        return difficulties.keySet();
    }

    public static Difficulty getDifficulty(String name) {
        if (difficulties == null)
            initialise();

        return difficulties.get(name);
    }

    public static Difficulty getDifficulty(DifficultyLevel difficulty) {
        switch (difficulty) {
            case HARD:
                return getDifficulty("HARD");
            case MEDIUM:
                return getDifficulty("MEDIUM");
            case EASY:
            default:
                return getDifficulty("EASY");
        }
    }

    private static void initialise() {
        difficulties = new HashMap<>();
        difficulties.put("EASY", new DifficultyImpl(0, 1, 1, "EASY"));
        difficulties.put("MEDIUM", new DifficultyImpl(4, 2, 2, "MEDIUM"));
        difficulties.put("HARD", new DifficultyImpl(8, 3, 3, "HARD"));
    }
}
