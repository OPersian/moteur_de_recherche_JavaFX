package MotsApp.Contrôleurs;

import MotsApp.Modèles.Article;
import MotsApp.Modèles.Photo;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import MotsApp.MainMotsApp;
import MotsApp.ModèlesGestion.ioXmlGestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class PageAccueilContrôleur implements Initializable {   
 
    
    @FXML private TitledPane x1;
    @FXML private Label functionaliteLabel;
    @FXML private Label utiliteLabel;
    @FXML private Label efficaciteLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // pour peupler la liste des articles quand on charge l'application :
        ObservableList<Article> articlesList = FXCollections.observableArrayList();
        
        // obtenir le fichier avec sien chemin relatif
        File fileArticles = new File("articles_sample.xml");
        
        // charger les données dans la variable locale du fichier "par défaut" 
        // pour peupler la liste des articles quand on charge l'application: 
        try {
            ioXmlGestion.chargerArticleDataDuFichier(fileArticles, true, articlesList);
            MainMotsApp.mabaseArticle_onload = articlesList;
        } catch (Exception ex) {
            Logger.getLogger(PageAccueilContrôleur.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        // pour peupler la liste des photos quand on charge l'application :
        ObservableList<Photo> photosList = FXCollections.observableArrayList();
        
        // obtenir le fichier avec sien chemin relatif
        File filePhotos = new File("photos_sample.xml");
        
        // charger les données dans la variable locale du fichier "par défaut" 
        // pour peupler la liste des photos quand on charge l'application: 
            try {
                ioXmlGestion.chargerPhotoDataDuFichier(filePhotos, true, photosList);
                MainMotsApp.mabasePhoto_onload = photosList;
            } catch (Exception ex) {
                Logger.getLogger(PageAccueilContrôleur.class.getName()).log(Level.SEVERE, null, ex);
            }

        functionaliteLabel.setText("MotsApp! vous permet de bien sauvegarder tous vos "
                + "données de manière vraiment multifonctionnelle! "
                + "\n"
                + "\nLes fichiers en format XML tiennent vos articles et vos "
                + "photos de n'importe quel volume!"
                + "\n"                
                + "\nDe plus, vous pouvez naviguer sur vos données et même "
                + "chercher!");
        
        utiliteLabel.setText("L'application est utile dans votre vie privée et celle de professionelle! Miex vaut tojours tracer "
                + "les résultats de vos travaux en formats des notes ou screenshots. "
                + "\n"
                + "\nVos souvenirs intimes peuvent être aussi memorizes grâce à MotsApp! "
                + "\n"
                + "\nVous vous intéressez au sujet particulier? Les collections des articles "
                + "et des photos sont à votre disposition!");
        
        efficaciteLabel.setText("MotsApp! garde bien vos articles et photos en format XML que vous notamment permettra d'echanger "
                + "vos données avec les autres applications sans aucun probleme! "
                + "\n"
                + "\nPrenez le contrôle de votre productivité, comme MotsApp! "
                + "vous permet de sauvegarder, modifier et naviguer sur vos données de façon le plus efficace!"
                + "\n"
                + "\nDe plus, nous vous garantissons de vous bien soutenir pendant tout le période d'emploi de MotsApp!");

    }    
    
}
