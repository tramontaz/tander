import java.sql.*;

public abstract class BaseDBHelper implements DBHelper {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    abstract Connection createConnection();

    @Override
    public void connect() {
        if(connection != null) throw new RuntimeException("DB already connected");
        try {
            connection = createConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        if(connection == null) throw new RuntimeException("DB not connected");
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public boolean execute(String sql) {
        if(statement == null) throw new RuntimeException("DB not connected");
        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public void createPrepareStatement(String sql) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public DBHelper prepStatementSetInt(int parameterIndex, int x) {
        if (preparedStatement == null) throw new RuntimeException("Prepared statement not created");
        try {
            preparedStatement.setInt(parameterIndex, x);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
        return this;
    }

    @Override
    public void prepareStatementAddBatch() {
        if (preparedStatement == null) throw new RuntimeException("Prepared statement not created");
        try {
            preparedStatement.addBatch();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public int[] prepareStatementExecuteBatch() {
        if (preparedStatement == null) throw new RuntimeException("Prepared statement not created");
        try {
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public void commit() {
        if (connection == null) throw new RuntimeException("DB not connected");
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public ResultSet executeQuery(String sql) {
        if(statement == null) throw new RuntimeException("DB not connected");
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
                preparedStatement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
