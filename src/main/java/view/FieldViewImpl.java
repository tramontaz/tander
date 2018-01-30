package view;

import DAO.ConnectionUTIL;
import DAO.JaxbWorker;
import model.Field;
import model.Fields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FieldViewImpl implements FieldView {
    private String dbURL = "jdbc:mysql://localhost/tander";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String databaseUsername = null;
    private String databasePassword = null;

    public void fillConnectionData() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("please, enter the database URL (example: jdbc:mysql://localhost/tander)");
            dbURL = in.readLine();

            System.out.println(".....aaaaaand JDBC Driver (example: com.mysql.jdbc.Driver)");
            jdbcDriver = in.readLine();

            System.out.println("Now you need to enter database username (example: tander)");
            databaseUsername = in.readLine();

            System.out.println("And the last one, enter user password: ");
            databasePassword = in.readLine();

            System.out.println("Your connection data is:\n" +
                                "Database URL: " + dbURL + "\n" +
                                "JDBC Driver: " + jdbcDriver + "\n" +
                                "Database username: " + databaseUsername + "\n" +
                                "Database password: " + databasePassword);
            in.close();

        } catch (IOException e) {
            System.err.println("We have some problems:\n" + e);
        }
    }

    public void action() {
        fillConnectionData();
        ConnectionUTIL connectionUTIL = new ConnectionUTIL(dbURL, jdbcDriver, databaseUsername, databasePassword);

        Fields fields = connectionUTIL.preparedStatement("SELECT * FROM tander.TEST;");

        JaxbWorker jaxbWorker = new JaxbWorker();
        jaxbWorker.convertCollectionToXML(fields, "1.xml");
    }
}
