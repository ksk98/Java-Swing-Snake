package managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import models.HighScoreRecord;

import java.sql.SQLException;
import java.util.List;

public class HighScoreManager {
    private static final String path = "jdbc:sqlite:data.db";
    private final ConnectionSource connectionSource;
    private final Dao<HighScoreRecord, Integer> scoreDao;

    private static HighScoreManager instance;

    static {
        try {
            instance = new HighScoreManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HighScoreManager getInstance() {
        return instance;
    }

    public void appendScore(String name, int score) throws SQLException {
        if (name == null)
            return;

        HighScoreRecord hsr = new HighScoreRecord(name, score);
        scoreDao.create(hsr);
    }

    public List<HighScoreRecord> getTopScores(int count) throws SQLException {
        QueryBuilder<HighScoreRecord, Integer> topDaoBuilder = scoreDao.queryBuilder()
                .orderBy("highScore", false)
                .limit((long)count);

        return topDaoBuilder.query();
    }

    private HighScoreManager() throws SQLException {
        connectionSource = new JdbcConnectionSource(path);
        TableUtils.createTableIfNotExists(connectionSource, HighScoreRecord.class);
        scoreDao = DaoManager.createDao(connectionSource, HighScoreRecord.class);
    }
}
