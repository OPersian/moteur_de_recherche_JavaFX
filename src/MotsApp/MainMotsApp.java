package MotsApp;

import MotsApp.Contrôleurs.RootLayoutContrôleur;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import MotsApp.Modèles.Article;
import MotsApp.Modèles.Photo;

/**
 *
 * @author Persianova, Golubnycha
 */

public class MainMotsApp extends Application {
    
    /*--------------------------gestion des scenes----------------------------*/    
    public Stage primaireStage;
    // pour paramétrer les méthodes des boutons open/save du menu 
    // (si on travail avec photos ou avec article, 
    // comme dans les noms des boutons le type de matière n'est pas indiqué délibérément):
    public static String couranteSousVue;
    /*------------------------END gestion des scenes--------------------------*/
    
    
    /*----------------------------pour TableView------------------------------*/
    // pour travailler tojours aves une seule liste des articles pendant la "session"
    public static ObservableList<Article> mabaseArticle_stockage; 
    // pour travailler tojours aves une seule liste des photos pendant la "session"
    public static ObservableList<Photo> mabasePhoto_stockage;
    // pour peupler la liste des articles quand on charge l'application:
    public static ObservableList<Article> mabaseArticle_onload; 
    // pour peupler la liste des photos quand on charge l'application:
    public static ObservableList<Photo> mabasePhoto_onload; 
    /*--------------------------END pour TableView----------------------------*/


    /*--------------------------pour Lucene recherche-------------------------*/
    // voir Main; pour d'opérer maxHits (voir: LuceneMoteur --> chercherDansIndex):
    public static int maxHits; 
    // pour chercher avec lucene (en particulier, stocker des resultats); 
    // aussi, pour eviter return-value de chargerArticleDataDuFichier():
    public static ObservableList<Article> mabaseArticle_stockage_lucene;
    public static ObservableList<Photo> mabasePhoto_stockage_lucene;
    // pour montrer le texte de la requête (dans la scène avec tableview des resultats):
    public static String requêteTexte;
    /*-------------------------END pour Lucene recherche----------------------*/
           
    @Override
    public void start(Stage stage) throws Exception {
        
        this.primaireStage = stage;
        this.primaireStage.setTitle("MotsApp Application");  
        this.primaireStage.setHeight(650);
        this.primaireStage.setWidth(690);
        this.primaireStage.setResizable(true);
        
        stage.setScene(
            createScene(
                loadMainPane()
            )
        );
        
        stage.show();
    }   
    
    private Pane loadMainPane() throws IOException  {
        FXMLLoader loader = new FXMLLoader();    
        
        // pour charger la scène primaire
        Pane mainPane = (Pane) loader.load(
            getClass().getResourceAsStream(
                VueNavigateur.ROOT_LAYOUT
            )
        );        
        RootLayoutContrôleur mainController = loader.getController();
        VueNavigateur.setMainController(mainController);
        VueNavigateur.loadVue(VueNavigateur.PAGE_ACCUEIL);        
        return mainPane;
    }
    
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );
        
        return scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }       
}
