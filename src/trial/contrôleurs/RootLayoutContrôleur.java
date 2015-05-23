package trial.contrôleurs;

import trial.contrôleurs.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import trial.MainMotsApp;
import trial.VueNavigateur;
import trial.VueNavigateur;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class RootLayoutContrôleur implements Initializable {
    
    
    @FXML private StackPane vistaHolder;
    @FXML private ScrollBar scrollBar;
    
    public void setView(Node node) {
        vistaHolder.getChildren().setAll(node);
    }       
  
    @FXML private MenuItem menuItemPageAccueil;
    @FXML private MenuItem menuItemArticleAjoute;
    @FXML private MenuItem menuItemPhotoAjoute;
    @FXML private MenuItem menuItemOuvrir;
    @FXML private MenuItem menuItemSauvegarder;
    @FXML private MenuItem menuItemSupprimer;
    @FXML private Menu menuItemModifier;
    @FXML private Menu menuItemIndexer;
    @FXML private Menu menuItemAider;
    @FXML private TextField textFieldRechercher;
    @FXML private Button rechercherBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void allerPageAccueil(ActionEvent event) {
        VueNavigateur.loadVista(VueNavigateur.PAGE_ACCUEIL);
    }

    @FXML
    private void allerArticleAjoute(ActionEvent event) {
        VueNavigateur.loadVista(VueNavigateur.ARTICLE_AJOUTE);
    }

    @FXML
    private void allerPhotoAjoute(ActionEvent event) {
        VueNavigateur.loadVista(VueNavigateur.PHOTO_AJOUTE);
    }

    @FXML
    private void rechercher(ActionEvent event) {
        VueNavigateur.loadVista(VueNavigateur.MATIÉRE_TABLEAU);        
    }   



}
