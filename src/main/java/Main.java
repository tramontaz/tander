import Core.ConnectionUTIL;
import Core.JaxbWorker;
import Core.XMLConverter;
import Core.XMLReader;


public class Main {

    private String dbURL;
    private String jdbcDriver;
    private String databaseUsername;
    private String databasePassword;
    private int n;

    Main() {
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public void setN(int n) {
        this.n = n;
    }

    protected void run() {
        long startTime = System.nanoTime();

        //create connection
        ConnectionUTIL connectionUTIL = new ConnectionUTIL(dbURL, jdbcDriver, databaseUsername, databasePassword);
        //fill database
        connectionUTIL.initializeTheDatabase(n);

        //Generate an xml file from the database
        JaxbWorker jaxbWorker = new JaxbWorker();
        jaxbWorker.convertCollectionToXML(connectionUTIL.getFieldsFromDB(
                                                                            "SELECT * FROM tander.TEST;"),
                                                                            "src/main/resources/1.xml");

        //With xslt, the application converts the contents of 1.xml to new 2.xml
        XMLConverter xmlConverter = new XMLConverter();
        xmlConverter.convertXML(
                "src/main/resources/converter.xsl",
                "src/main/resources/1.xml",
                "src/main/resources/2.xml");

        //Calculate and display the arithmetic sum of the value of all attributes in Field
        XMLReader xmlReader = new XMLReader();
        System.out.println(
                "The result of the calculations: " +
                        xmlReader.readXmlFileAndReturnSumOfAttribute("src/main/resources/2.xml"));
        long totalTime = System.nanoTime() - startTime;
        System.out.println("\n" +
                "Execution time of the program: " + totalTime/1000000000 + " seconds.");
    }
}
