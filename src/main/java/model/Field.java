package model;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "entry")
@XmlSeeAlso({model.Fields.class})
public class Field {

    private int fieldValue;

    public Field() {
    }

    public Field(int fieldValue) {
        this.fieldValue = fieldValue;
    }
    @XmlElement(name = "field")
    public int getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(int fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldValue=" + fieldValue +
                '}';
    }
}
