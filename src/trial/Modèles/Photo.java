package trial.Modèles;

import java.net.URL;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
/**
 *
 * @author Persianova, Golubnycha
 */
public class Photo extends Matière {
    
    private final SimpleStringProperty description = new SimpleStringProperty(null);
    private ImageView imgV;
    
   
    public Photo(ImageView imgV,
            String titre, 
            String auteur, 
            String description,
            LocalDate date, 
            URL source) 
    {
        super(titre, auteur, date, source);
        
            
        this.setDescription(description);
        this.imgV = imgV;
                

    }
    
  @Override
    public String toString()
    {  
        String message;
        //if (this.version) message = "electronique et en papier";
        //else  message = "electronique";
        
        String s = ("\n" + "Description: " + this.getDescription() + "\n");
                   // "Version: " + message);
        //return s;         
        return super.toString() + s; 
    }


    public String getDescription(){return description.get();} 
    public void setDescription(String description){this.description.set(description);}  
    public StringProperty descriptionProperty() { return description; }
  
    public ImageView getImage(){return imgV;}
    public void setImage(ImageView image){this.setImgV(imgV);}
    public void setImgV(ImageView imgV) {this.imgV = imgV;}

}
