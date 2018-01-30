package view;

import DAO.ConnectionUTIL;
import DAO.JaxbWorker;
import model.Fields;

public class FieldViewImpl implements FieldView {
    private String dbURL = "jdbc:mysql://localhost/tander";
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String databaseUsername = "tander";
    private String databasePassword = "%I@mT@nder_";
    private int n = 1000000;

    public void action() {
//        fillConnectionData();
        ConnectionUTIL connectionUTIL = new ConnectionUTIL(dbURL, jdbcDriver, databaseUsername, databasePassword);

        connectionUTIL.initializeTheDatabase(n);
        Fields fields = connectionUTIL.getFieldsFromDB("SELECT * FROM tander.TEST;");

        JaxbWorker jaxbWorker = new JaxbWorker();
        jaxbWorker.convertCollectionToXML(fields, "1.xml");
    }
}
