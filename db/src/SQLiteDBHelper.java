import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDBHelper extends BaseDBHelper {
    private String dbFileName;

    public SQLiteDBHelper(String dbFileName) {
        this.dbFileName = dbFileName;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error register JDBC driver", e);
        }
    }

    @Override
    Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}
