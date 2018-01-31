package DAO;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLConverter {
    private TransformerFactory tf;
    Transformer transformer;

    public XMLConverter() {
    }

    public void convertXML(String converter, String source, String result) {
        try {
            tf = TransformerFactory.newInstance();
            transformer = tf.newTransformer(new StreamSource(converter));
            transformer.transform(new StreamSource(source), new StreamResult(result));
            System.out.println("The conversion successful complete.");
        } catch (TransformerConfigurationException e) {
            System.err.println("The conversion failed!!!");
            e.printStackTrace();
        } catch (TransformerException e) {
            System.err.println("The conversion failed!!!");
            e.printStackTrace();
        }
    }
}
