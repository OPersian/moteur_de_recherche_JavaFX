package MotsApp.ModèlesGestion;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import MotsApp.Modèles.Photo;

/**
 * This is used for saving the
 * list of photos to XML.
 * @author Persianova, Golubnycha
 */
@XmlRootElement(name = "photos")
public class PhotoListeEmballage {
    
    private List<Photo> photos;
    @XmlElement(name = "photo")
    
    public List<Photo> getPhotos() {
        return photos;
    }
    
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
}
