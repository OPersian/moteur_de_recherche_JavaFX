package MotsApp.ModèlesGestion;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 
 * @author Persianova, Golubnycha
 */
public class FormatAdapteur extends XmlAdapter<String, LocalDate>{
    
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
    
    public static LocalDate dateFormat(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-"
                + "MM-yyyy"); //HH-mm-ss
        LocalDate date = LocalDate.now();
        try {
            date = LocalDate.parse(stringDate, formatter);
            return date;
        } 
        catch (DateTimeParseException e) {
            System.out.println("Date format est incorrect!");
            return date;
        }  
    }
    
    public static URL urlFormat(String stringUrl) throws MalformedURLException {
    
        URL source = new URL("http://sample.com.ua");                
        try {
            source = new URL(stringUrl);
            return source;
        }
        catch (MalformedURLException e) {
            System.out.println("URL format incorrect!");
            return source;
        }     
    }
}
