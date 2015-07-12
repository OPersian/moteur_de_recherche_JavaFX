package MotsApp.Modèles;

import java.net.URL;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author Persianova, Golubnycha
 */
public class Photo extends Matière {

    private final SimpleStringProperty description = new SimpleStringProperty(null);

    //default constructor; enables to work with FXML Table View population
    //for FXML (FXCollections in TableView) no-args needed!
    public Photo() 
    {        
        this( null, null, null, null, null);
    }  
    

    public Photo(String titre,
                String auteur,
                String description,
                LocalDate date,
                URL source) 
            
    {
        super(titre, auteur, date, source);
        this.setContenu(description);
    }
    
    @Override // to debug in console
    public String toString()
    {  
        String s = ("\n" + "Description: " + this.getContenu() + "\n"); 
        return super.toString() + s;  
    }

    public void setContenu(String description)  { this.description.set(description); }    
    public String getContenu()                  { return description.get(); }
    public StringProperty descriptionProperty() { return description; }

}
