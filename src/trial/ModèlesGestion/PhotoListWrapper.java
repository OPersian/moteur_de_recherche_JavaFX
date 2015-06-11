package trial.ModèlesGestion;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import trial.Modèles.Photo;

/**
 *
 * @author Persianova, Golubnycha
 */
@XmlRootElement(name = "photos")
public class PhotoListWrapper {
    
    private List<Photo> photos;
    @XmlElement(name = "photo")
    
    public List<Photo> getPhotos() {
        return photos;
    }
    
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
}
