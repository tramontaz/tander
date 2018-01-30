package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "entries")
public class Fields extends ArrayList<Field> {

    private List<Field> fields = new ArrayList<Field>();

    public void addField (Field field) {
        this.fields.add(field);
    }
    @XmlElement(name = "entry")
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Fields{" +
                "fields=" + fields +
                '}';
    }
}
