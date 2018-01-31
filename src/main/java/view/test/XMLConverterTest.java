package view.test;

import DAO.ConnectionUTIL;
import DAO.JaxbWorker;
import DAO.XMLConverter;
import DAO.XMLReader;

public class XMLConverterTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String dbURL = "jdbc:mysql://localhost/tander";
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String databaseUsername = "tander";
        String databasePassword = "%I@mT@nder_";
        int n = 1000000;

        ConnectionUTIL connectionUTIL = new ConnectionUTIL(dbURL, jdbcDriver, databaseUsername, databasePassword);

        connectionUTIL.initializeTheDatabase(n);

        JaxbWorker jaxbWorker = new JaxbWorker();
        jaxbWorker.convertCollectionToXML(connectionUTIL.getFieldsFromDB(
                "SELECT * FROM tander.TEST;"),
                "src/main/resources/1.xml");

        XMLConverter xmlConverter = new XMLConverter();
        xmlConverter.convertXML(
                "src/main/resources/converter.xsl",
                "src/main/resources/1.xml",
                "src/main/resources/2.xml");

        XMLReader xmlReader = new XMLReader();
        System.out.println(
                "The arithmetic sum of the values of all attributes in the Field: \t" +
                xmlReader.readXmlFileAndReturnSumOfAttribute("src/main/resources/2.xml"));
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("time: " + timeSpent);
    }
}
