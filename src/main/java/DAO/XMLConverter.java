package DAO;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLConverter {
    public static void main(String[] args) {

        try {

            TransformerFactory tf = TransformerFactory.newInstance();

            //установка используемого XSL-преобразования

            Transformer transformer = tf.newTransformer(new StreamSource("converter.xsl"));

            //установка исходного XML-документа и конечного XML-файла

            transformer.transform(

                    new StreamSource("1.xml"),

                    new StreamResult("2.xml"));

            System.out.print("complete");

        } catch (TransformerException e) {

            e.printStackTrace();

        }

    }
}
