package MotsApp.Modèles;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Persianova, Golubnycha
 */
public class BaseDesPhotos {
    public ObservableList<Photo> mabasePhoto;    
    
    public BaseDesPhotos()
    {
        mabasePhoto = FXCollections.observableArrayList();
    }
    
    public void ajouterPhoto(Photo n)
    {
        mabasePhoto.add(n);
    }
    
    public ObservableList<Photo> getPhotoData() {
    
        return mabasePhoto;
    }    
    
    //to debug in console
    public void photoAfficher(){
        
        Iterator it = mabasePhoto.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }


    
}
