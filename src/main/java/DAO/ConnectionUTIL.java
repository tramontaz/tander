package DAO;

import model.Field;
import model.Fields;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionUTIL {
    /**
     * driver, url, login and password.
     */
    private String dbUrl;
    private String jdbcDriver;
    private String username;
    private String password;

    public ConnectionUTIL(String dbUrl, String jdbcDriver, String username, String password) {
        this.dbUrl = dbUrl;
        this.jdbcDriver = jdbcDriver;
        this.username = username;
        this.password = password;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Fields preparedStatement(String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Fields fields = new Fields();
        try {
            //Register JDBC Driver:
            Class.forName(jdbcDriver);

            //Open a connection:
            connection = DriverManager.getConnection(dbUrl, username, password);

            //Execute a query:
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                fields.addField(new Field(resultSet.getInt("FIELD")));
            }

        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        } finally {
            //close resources:
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return fields;
    }
}
