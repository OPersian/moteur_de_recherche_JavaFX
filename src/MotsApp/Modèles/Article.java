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
                   URL source
                   )
                   //boolean version) 
    {
        super(titre, auteur, date, source);
        
        //this.contenu = new SimpleStringProperty(contenu);
        //this.version = true;
        this.setContenu(contenu);
    }
    
    @Override
    public String toString()
    {  
        String message;
        //if (this.version) message = "electronique et en papier";
        //else  message = "electronique";
        
        String s = ("\n" + "Contenu: " + this.getContenu() + "\n");
                   // "Version: " + message);
        //return s;         
        return super.toString() + s; 
    }
    
    public void setContenu(String contenu)  { this.contenu.set(contenu); }    
    public String getContenu()              { return contenu.get(); } 
    
    public StringProperty contenuProperty() { return contenu; } //return values in FXML TableView
    
   /* public void setVersion(boolean version){this.version.set(version);}    
    public boolean getVersion(){return version.get();}  */

}
