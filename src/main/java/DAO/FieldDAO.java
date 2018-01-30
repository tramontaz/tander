package DAO;

import model.Field;
import org.w3c.dom.Document;

public interface FieldDAO {
    public void insert(Field field);
    public Field parse(Document document);
}
