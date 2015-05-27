package trial.Contrôleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.apache.lucene.queryparser.classic.ParseException;
import trial.MainMotsApp;
import trial.ModèlesGestion.LuceneMoteur;
import trial.VueNavigateur;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class RootLayoutContrôleur implements Initializable {
    
    @FXML private StackPane vistaHolder;
    @FXML private TextField rechercherChamp;
    
    public void setView(Node node) {
        vistaHolder.getChildren().setAll(node);
    } 

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
    private void rechercher(ActionEvent event) throws ParseException, IOException {
        //VueNavigateur.loadVista(VueNavigateur.ARTICLE_TABLEAU);
        String requête;
        requête = rechercherChamp.getText();
        LuceneMoteur.chercherDansIndex(requête);
    }   

    /*-------------------------travail avec fichiers--------------------------*/   
    MainMotsApp mainMotsApp = new MainMotsApp();      
    
    /**
     * Opens a FileChooser to let the user select a MotsApp! to load.
     */
    @FXML
    private void fichierOuvrir() throws Exception {
        FileChooser fileChooser = new FileChooser();
        
        // Set extension filter; only xml can be read
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainMotsApp.getPrimaireStage());
        //File file = fileChooser.showOpenDialog(null);//try it!

        if (file != null) {
            if (MainMotsApp.currentSousVue == VueNavigateur.ARTICLE_AJOUTE || 
                MainMotsApp.currentSousVue == VueNavigateur.ARTICLE_TABLEAU){
                    MainMotsApp.chargerArticleDataDuFichier(file);//mainMotsApp, before it became static
                    VueNavigateur.loadVista(VueNavigateur.ARTICLE_TABLEAU);//reload view 
                    //to see changes instantly
            }
                
            else if (MainMotsApp.currentSousVue == VueNavigateur.PHOTO_AJOUTE || 
                MainMotsApp.currentSousVue == VueNavigateur.PHOTO_TABLEAU){
                    mainMotsApp.chargerPhotoDataDuFichier(file);
                    VueNavigateur.loadVista(VueNavigateur.PHOTO_TABLEAU);//reload view 
                    //to see changes instantly
            }
                 
            else {MainMotsApp.chargerArticleDataDuFichier(file);}//mainMotsApp, before it became static
        }

    }
    
    /**
    * Opens a FileChooser to let the user select a file to save to.
    */
    @FXML
    private void fichierSauvegarderComme() throws Exception {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainMotsApp.getPrimaireStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            
            if (MainMotsApp.currentSousVue == VueNavigateur.ARTICLE_AJOUTE || 
                MainMotsApp.currentSousVue == VueNavigateur.ARTICLE_TABLEAU)
                    mainMotsApp.sauvegarderArticleDataToFile(file);
                
            else if (MainMotsApp.currentSousVue == VueNavigateur.PHOTO_AJOUTE || 
                MainMotsApp.currentSousVue == VueNavigateur.PHOTO_TABLEAU)
                    mainMotsApp.sauvegarderPhotoDataToFile(file);
                
            else mainMotsApp.sauvegarderArticleDataToFile(file);
            
            //mainMotsApp.sauvegarderArticleDataToFile(file);
        }     
    }
    /*------------------------END travail avec fichiers-----------------------*/  
    
   /**
     * Opens an about dialog.
     */
    @FXML
    private void montrerAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("MotsApp!");
        alert.setHeaderText("About");
        alert.setContentText("Author: Olena Persianova, Olga Golybnucha");
        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void sortieDeMotsApp() {
        System.exit(0);
    }

    @FXML
    private void montrerAuteurs(ActionEvent event) {
    }

    @FXML
    private void indexer(ActionEvent event) throws IOException {
        LuceneMoteur.créerIndex();
    }

}
