import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Core {
    public void insertRecordsInTable(DBHelper dbHelper, int n) {
        dbHelper.setAutoCommit(false);
        dbHelper.execute("DROP TABLE IF EXISTS TEST;");
        dbHelper.execute("CREATE TABLE TEST (FIELD int not null primary key);");
        dbHelper.createPrepareStatement("INSERT INTO `TEST` (`FIELD`) VALUES (?);");
        for (int i = 1; i <= n; i++) dbHelper.prepStatementSetInt(1, i).prepareStatementAddBatch();
        dbHelper.prepareStatementExecuteBatch();
        dbHelper.commit();
    }

    public void unloadingToXML(DBHelper dbHelper, String filename) {
        try (
                BufferedWriter wr = new BufferedWriter(new FileWriter(filename));
                ResultSet resultSet = dbHelper.executeQuery("SELECT * FROM TEST;")
        ) {
            wr.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            wr.write("<entries>\n");
            while (resultSet.next()) {
                wr.write("    <entry>\n");
                wr.write("        <field>");
                wr.write(Integer.toString(resultSet.getInt(1)));
                wr.write("</field>\n");
                wr.write("    </entry>\n");
            }
            wr.write("</entries>");
            wr.flush();
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void convertXML(String inFilename, String outFilename, String patternFilename) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(new StreamSource(patternFilename));
            transformer.transform(new StreamSource(inFilename), new StreamResult(outFilename));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public long getAverageFromXML(String filename) {
        long sum = 0;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filename));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("entry");
            for (int i = 0, cnt = nodeList.getLength(); i < cnt; i++) {
                Element element = (Element) nodeList.item(i);
                sum += Integer.parseInt(element.getAttribute("field"));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }
}
