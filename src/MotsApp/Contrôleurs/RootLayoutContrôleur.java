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
import MotsApp.ModèlesGestion.AlertGestion;
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
        String requête;
        requête = rechercherChamp.getText();
        LuceneMoteur.chercherDansIndex(requête);
        // à mettre le texte du requête dans le champ
        MainMotsApp.requêteTexte = requête;
        VueNavigateur.loadVue(VueNavigateur.RESULTATS_RECHERCHE);
    }   

    /*------------------------travail avec des fichiers------------------------*/   
    MainMotsApp mainMotsApp = new MainMotsApp();  
    
    @FXML
    private void fichierOuvrir() throws Exception {
        ioXmlGestion.fichierOuvrir(true); // clear current articles list
        if (VueNavigateur.photoVueCheck()) VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);
        else VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU);
    }   
    
    @FXML
    private void fichierSauvegarderComme() throws Exception {
        ioXmlGestion.fichierSauvegarderComme(mainMotsApp);
    }   
    /*------------------------END travail avec fichiers-----------------------*/  

    @FXML
    private void montrerAbout() {
        AlertGestion.displayInfoAlert(  AlertGestion.infoTitre, 
                                        AlertGestion.infoCorps,
                                        AlertGestion.infoAuteurs);
    }

    // sortir de l'application
    @FXML
    private void sortieDeMotsApp() {
        System.exit(0);
    }

    @FXML
    private void montrerAuteurs(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.AUTEURS_INFO);
    }
    
    /**
    * à lancer l'indexage des documents avec lucene.
    */
    // 
    @FXML
    private void indexer(ActionEvent event) throws IOException {
        LuceneMoteur.créerIndex();
    }

    @FXML
    private void allerListeArticles(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU);
    }

    @FXML
    private void allerListePhotos(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);
    }

}
