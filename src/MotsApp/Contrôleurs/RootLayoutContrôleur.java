package MotsApp.Contrôleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.apache.lucene.queryparser.classic.ParseException;
import MotsApp.MainMotsApp;
import MotsApp.Modèles.AlertGestion;
import MotsApp.ModèlesGestion.LuceneMoteur;
import MotsApp.ModèlesGestion.ioXmlGestion;
import MotsApp.VueNavigateur;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class RootLayoutContrôleur implements Initializable {
    
    @FXML private StackPane vuePorteur;
    @FXML private TextField rechercherChamp;
   
    public void setView(Node node) {
        vuePorteur.getChildren().setAll(node);
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
        VueNavigateur.loadVue(VueNavigateur.PAGE_ACCUEIL);
    }

    @FXML
    private void allerArticleAjoute(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.ARTICLE_AJOUTE);
    }

    @FXML
    private void allerPhotoAjoute(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.PHOTO_AJOUTE);
    }

    @FXML
    private void rechercher(ActionEvent event) throws ParseException, IOException {
        //VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU);
        String requête;
        requête = rechercherChamp.getText();
        LuceneMoteur.chercherDansIndex(requête);
        MainMotsApp.requêteTexte = requête;
        VueNavigateur.loadVue(VueNavigateur.RESULTATS_RECHERCHE);
    }   

    /*------------------------travail avec des fichiers------------------------*/   
    MainMotsApp mainMotsApp = new MainMotsApp();  
    
    @FXML
    private void fichierOuvrir() throws Exception {
        ioXmlGestion.fichierOuvrir();
    }   
    
    @FXML
    private void fichierSauvegarderComme() throws Exception {
        ioXmlGestion.fichierSauvegarderComme(mainMotsApp);
    }   
    /*------------------------END travail avec fichiers-----------------------*/  
    
   /**
     * Opens an about dialog.
     */
    @FXML
    private void montrerAbout() {
        AlertGestion.displayInfoAlert(  AlertGestion.infoTitre, 
                                        AlertGestion.infoCorps,
                                        AlertGestion.infoAuteurs);
    }

    /**
     * Closes the application.
     */
    @FXML
    private void sortieDeMotsApp() {
        System.exit(0);
    }

    /**
    * Shows detail information on authors.
    */
    @FXML
    private void montrerAuteurs(ActionEvent event) {
    }
    
    /**
    * Runs indexing of documents (with search goals).
    */
    @FXML
    private void indexer(ActionEvent event) throws IOException {
        LuceneMoteur.créerIndex();
    }

}
