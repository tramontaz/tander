package view.test;

import DAO.XMLReader;

public class XMLReaderTest {
    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader();
        System.out.println("Sum of all fields = " + xmlReader.readXmlFile("2.xml"));
    }
}
