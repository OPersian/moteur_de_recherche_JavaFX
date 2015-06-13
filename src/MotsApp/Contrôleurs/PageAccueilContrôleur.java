package MotsApp.Contrôleurs;

import static MotsApp.MainMotsApp.mabaseArticle;
import static MotsApp.MainMotsApp.mabaseArticle_stockage;
import static MotsApp.MainMotsApp.mabasePhoto;
import static MotsApp.MainMotsApp.mabasePhoto_stockage;
import MotsApp.Modèles.Article;
import MotsApp.Modèles.Photo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

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
        
        // to load sample article from the very beginning
        Article a = new Article();
        a.setTitre("titre");
        a.setAuteur("auteur");
        a.setContenu("contenu");
        mabaseArticle.ajouterArticle(a);
        mabaseArticle_stockage = mabaseArticle.getArticleData();
        
        // to load sample photo item from the very beginning
        Photo p = new Photo();
        p.setTitre("titre");
        p.setAuteur("auteur");
        p.setContenu("description");
        mabasePhoto.ajouterPhoto(p);
        mabasePhoto_stockage = mabasePhoto.getPhotoData();
        
        functionaliteLabel.setText("MotsApp! vous permet de bien sauvegarder tous vos "
                + "\ndonnees de maniere vraiment multifonctionnele! "
                + "\n"
                + "\nLes fichiers en format XML tiennent vos articles et vos "
                + "\n photos de n'importe quelle volume!"
                + "\n"                
                + "\nDe plus, vous pouvez naviguer sur vos donnees et meme "
                + "\nchercher!");
        
        utiliteLabel.setText("L'application est utile dans votre vie privee "
                + "\net celle de professionelle! Miex vaut tojours tracer "
                + "\nles resultats de vos travaux en formats des notes ou "
                + "\nscreenshots. "
                + "\n"
                + "\nVos souvenirs intimes peuvent etre aussi memorizes grace "
                + "\na MotsApp! "
                + "\n"
                + "\nVous vous interressez au sujet particulier? Les collections des "
                + "\narticles et des photos sont a votre disposition!");
        
        efficaciteLabel.setText("MotsApp! garde bien vos articles et photos"
                + "\nen  format XML que vous notamment permettra "
                + "\nd'echanger vos donnes avec les autres applications "
                + "\nsans aucun probleme! "
                + "\n"
                + "\nPrenez controle de votre productivite, comme MotsApp! "
                + "\nvous permet de sauvegarder, modifier et naviguer "
                + "\nsur vos donnees de facon le plus efficace!"
                + "\n"
                + "\nDe plus, nous vous garantions de vous bien soutenir"
                + "\npendant tout le periode d'emploi de MotsApp!");

    }    
    
}
