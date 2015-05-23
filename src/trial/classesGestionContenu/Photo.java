package trial.classesGestionContenu;

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
        private StringProperty description;
    private ImageView imageView;
    
    public Photo(String titre, 
            String auteur, 
            LocalDate date, 
            URL source,
            String description,
            ImageView imageView) 
    {
        super(titre, auteur, date, source);
        
        this.description = new SimpleStringProperty(description);
        this.imageView = imageView;
    }



    public String getDescription(){return description.get();} 
    public void setDescription(String description){this.description.set(description);}  
    
    public ImageView getImageView() {return imageView;}
    public void setIImageView(ImageView imageView) {this.imageView = imageView;}
}
