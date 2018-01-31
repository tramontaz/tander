import DAO.ConnectionUTIL;
import DAO.JaxbWorker;
import DAO.XMLConverter;
import DAO.XMLReader;

public class Main {

    public static void main(String[] args) {
        //create connection
        ConnectionUTIL connectionUTIL = new ConnectionUTIL(args[0], args[1], args[2], args[3]);
        //fill database
        connectionUTIL.initializeTheDatabase(Integer.valueOf(args[4]));

        //Generate an xml file from the database
        JaxbWorker jaxbWorker = new JaxbWorker();
        jaxbWorker.convertCollectionToXML(connectionUTIL.getFieldsFromDB("SELECT * FROM tander.TEST;"), "src/main/resources/1.xml");

        //With xslt, the application converts the contents of 1.xml to new 2.xml
        XMLConverter xmlConverter = new XMLConverter();
        xmlConverter.convertXML("src/main/resources/converter.xls", "src/main/resources/1.xml", "src/main/resources/2.xml");

        XMLReader xmlReader = new XMLReader();
        xmlReader.readXmlFile("src/main/resources/2.xml");


    }
}
