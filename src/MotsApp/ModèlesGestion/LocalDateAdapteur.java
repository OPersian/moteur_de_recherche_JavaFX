package MotsApp.ModèlesGestion;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between the LocalDate and the ISO 8601 
 * @author Persianova, Golubnycha
 */
public class LocalDateAdapteur extends XmlAdapter<String, LocalDate>{
    
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
