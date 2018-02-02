import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySQLDBHelper extends BaseDBHelper{

    private String login;
    private String password;
    private String url;

    MySQLDBHelper(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.url = url;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error register JDBC driver", e);
        }
    }

    @Override
    Connection createConnection() {
        try {
            return DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }
}