package MotsApp.Modèles;

import java.net.URL;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Persianova, Golubnycha
 */

public class Article extends Matière {

    //private StringProperty contenu;
    private final SimpleStringProperty contenu = new SimpleStringProperty(null);
    
    //default constructor; enables to work with FXML Table View population
    //for FXML (FXCollections in TableView) no-args needed!
    public Article() 
    {        
        this(null, null, null, null, null);
    }   
            
    public Article(String titre, 
                   String auteur,
                   String contenu,
                   LocalDate date, 
                   URL source)
    {
        super(titre, auteur, date, source);
        //this.contenu = new SimpleStringProperty(contenu);
        this.setContenu(contenu);
    }
    
    
    @Override // to debug in console
    public String toString()
    {  
        String s = ("\n" + "Contenu: " + this.getContenu() + "\n"); 
        return super.toString() + s;  
    }
    
    public void setContenu(String contenu)  { this.contenu.set(contenu); }    
    public String getContenu()              { return contenu.get(); } 
    
    public StringProperty contenuProperty() { return contenu; } //return values in FXML TableView

}
