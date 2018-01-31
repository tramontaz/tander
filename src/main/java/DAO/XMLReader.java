package DAO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLReader {
    private DocumentBuilderFactory dbf = null;
    private DocumentBuilder docBuilder = null;
    private Document doc = null;
    private int sum = 0;

    public XMLReader() {
    }

    public int readXmlFileAndReturnSumOfAttribute(String pathname){
        //building object model of XML file:
        try {
            dbf = DocumentBuilderFactory.newInstance();
            docBuilder = dbf.newDocumentBuilder();
            doc = docBuilder.parse(new File(pathname));

            //normalisation
            doc.getDocumentElement().normalize();

            //get all nodes with name "entry":
            NodeList nodeList = doc.getElementsByTagName("entry");

            //add all attribute values ​​of the node:
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                sum = sum + Integer.valueOf(element.getAttribute("field"));
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
