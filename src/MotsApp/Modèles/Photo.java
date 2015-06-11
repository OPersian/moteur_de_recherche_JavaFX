package MotsApp.Modèles;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author Persianova, Golubnycha
 */
public class Photo extends Matière implements Serializable {

    private final SimpleStringProperty description = new SimpleStringProperty(null);
    //private ImageView imgV;
   // private String description;
   // private byte[] imgV;
    
    //default constructor; enables to work with FXML Table View population
    //for FXML (FXCollections in TableView) no-args needed!
    public Photo() 
    {        
        this( null, null, null, null, null);
    }  
    

    public Photo(
            //ImageView imgV,
           // byte[] imgV,
            String titre,
            String auteur,
            String description,
            LocalDate date,
            URL source) {
        super(titre, auteur, date, source);

        this.setDescription(description);//comment to serial-deserial
        //this.description = description;
        //this.imgV = imgV;//comment to serial-deserial
    }

    @Override
    public String toString() {
        String message;
        //if (this.version) message = "electronique et en papier";
        //else  message = "electronique";

        //String s = ("\n" + "Description: " + this.getDescription() + "\n");
        // "Version: " + message);
        //return s;       
        return "";
       // return super.toString() + s;
    }

public void setDescription(String description)  { this.description.set(description); }    
public String getDescription()                  { return description.get(); }
public StringProperty descriptionProperty()     { return description; }

   /*public ImageView getImage() {
        return imgV;
    }
/*
    public void setImage(ImageView image) {
        this.setImgV(imgV);
    }*/

  /*  public void setImgV(ImageView imgV) {
        this.imgV = imgV;
    }*/

  /*  @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    /**
     * @return the description
     */
   /* public String getDescription() {
        return description;
    }*/

    /**
     * @param description the description to set
     */
   /* public void setDescription(String description) {
        this.description = description;
    }*/

    /**
     * @return the imgV
     */
   /* public byte[] getImgV() {
        return imgV;
    }

    /**
     * @param imgV the imgV to set
     */
   /* public void setImgV(byte[] imgV) {
        this.imgV = imgV;
    }*/
    
   

}
