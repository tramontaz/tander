import java.sql.ResultSet;

public interface DBHelper {

    void connect();
    void disconnect();
    void setAutoCommit(boolean autoCommit);

    void createPrepareStatement(String sql);
    DBHelper prepStatementSetInt(int parameterIndex, int x);
    void prepareStatementAddBatch();
    int[] prepareStatementExecuteBatch();
    void commit();

    boolean execute(String sql);
    ResultSet executeQuery(String sql);
}