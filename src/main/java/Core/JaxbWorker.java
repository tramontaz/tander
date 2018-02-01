package Core;

import model.Field;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbWorker {

    public JaxbWorker() {
    }

    public Field fromXmlToObject(String filePath) {
        try {
            //create JAXBContext object - point of entry JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Field.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Field) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void convertObjectToXML(Field field, String filePath) {
        //save object to file
        try {
            JAXBContext context = JAXBContext.newInstance(Field.class);
            Marshaller marshaller = context.createMarshaller();
            //flag for readable output in xml:
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //marshalling object to file:
            marshaller.marshal(field, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void convertCollectionToXML(List<Field> fields, String filePath){
        //save object to file
        try {
            JAXBContext context = JAXBContext.newInstance(Field.class);
            Marshaller marshaller = context.createMarshaller();
            //flag for readable output in xml:
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //marshalling object to file:
            marshaller.marshal(fields, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
