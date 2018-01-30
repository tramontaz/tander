package view.test;

import DAO.JaxbWorker;
import model.Fields;



public class JaxbWorkerTest {
    public static void main(String[] args) {
        JaxbWorker jaxbWorker = new JaxbWorker();

        Fields fields = new Fields();


//        System.out.println(fields.toString());

//        jaxbWorker.convertObjectToXML(new Field(1), "1.xml");
        jaxbWorker.convertCollectionToXML(fields, "1.xml");
    }
}
