package view;

import DAO.ConnectionUTIL;
import DAO.JaxbWorker;
import model.Fields;

public class FieldViewImpl implements FieldView {
    private String dbURL = "jdbc:mysql://localhost/tander";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String databaseUsername = "tander";
    private String databasePassword = "%I@mT@nder_";
    private int n = 100;

    public void action() {
//        fillConnectionData();
        ConnectionUTIL connectionUTIL = new ConnectionUTIL(dbURL, jdbcDriver, databaseUsername, databasePassword);

        connectionUTIL.initializeTheDatabase(n);

        JaxbWorker jaxbWorker = new JaxbWorker();
        jaxbWorker.convertCollectionToXML(connectionUTIL.getFieldsFromDB("SELECT * FROM tander.TEST;"), "1.xml");
    }
}
