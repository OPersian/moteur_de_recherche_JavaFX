package MotsApp.Modèles;

import MotsApp.ModèlesGestion.FormatAdapteur;
import java.net.URL;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Persianova, Golubnycha
 */
    
public abstract class Matière implements Comparable  {

private final SimpleStringProperty titre = new SimpleStringProperty(null);
private final SimpleStringProperty auteur = new SimpleStringProperty(null);
private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>(null);
private final ObjectProperty<URL> source = new SimpleObjectProperty<URL>(null);  

//default constructor - for FXML (FXCollections in TableView), no-args needed!
public Matière()
    {  
        this(null, null, null, null);          
    }

public Matière(String titre, String auteur, 
               LocalDate date, URL source)
    {
        this.setTitre(titre);
        this.setAuteur(auteur);        
        this.setDate(date);
        this.setSource(source);
    }

//to debug in console
@Override
public String toString()
{         
    String s = ("Titre: " + titre + "\n" + "Auteur: " + auteur
            + "\n" + "Date: " + date + "\n" + "Lien: " + source);
    return s;
}        
// trier les objets; seulement à déboguer dans la console    
@Override 
public int compareTo(Object o)
{
    Matière n = (Matière)o;
    //return this.titre.compareTo(n.titre);
    return this.titre.get().compareTo(n.titre.get());
}
    
public void setTitre(String titre){this.titre.set(titre);}    
public String getTitre(){return titre.get();}   

public void setDate(LocalDate date){this.date.set(date);} 
@XmlJavaTypeAdapter(FormatAdapteur.class) //to write localdata field to XML file
public LocalDate getDate(){return date.get();} 

public void setAuteur(String auteur){this.auteur.set(auteur);}    
public String getAuteur(){return auteur.get();} 

public void setSource(URL source){this.source.set(source);}    
public URL getSource(){return source.get();} 

//return values in FXML TableView
public StringProperty titreProperty() { return titre; }
public StringProperty auteurProperty() { return auteur; }
public ObjectProperty<LocalDate> dateProperty() { return date; }
public ObjectProperty<URL> sourceProperty() { return source; }

}
    
    
    


