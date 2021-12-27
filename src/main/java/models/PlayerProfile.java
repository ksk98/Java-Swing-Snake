package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import enums.SnakeType;

@DatabaseTable(tableName = "playerProfiles")
public class PlayerProfile {
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private int highScore;

    @DatabaseField(canBeNull = false)
    private SnakeType skin;

    public PlayerProfile() {

    }

    public PlayerProfile(String name) {
        this.name = name;
        highScore = 0;
        skin = SnakeType.DEFAULT;
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }
}
