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
import MotsApp.Modèles.BaseDesPhotos;
import MotsApp.Modèles.BaseDesArticles;
import MotsApp.Modèles.Photo;

/**
 *
 * @author Persianova, Golubnycha
 */

public class MainMotsApp extends Application {
    
    /*--------------------------gestion des scenes----------------------------*/    
    public Stage primaireStage; //private  
    public static String couranteSousVue; //to parametrize open/save menu buttons' methods
                                          //(whether we work with photos or articles)
    /*------------------------END gestion des scenes--------------------------*/
    
    
    /*----------------------------pour TableView------------------------------*/
    public static ObservableList<Article> mabaseArticle_stockage;
    public static ObservableList<Photo> mabasePhoto_stockage;
    public static BaseDesArticles mabaseArticle = new BaseDesArticles(); //to populate tableview in future  
    public static BaseDesPhotos mabasePhoto = new BaseDesPhotos(); 
    /*--------------------------END pour TableView----------------------------*/


    /*--------------------------pour Lucene recherche-------------------------*/
    public static int maxHits; //see Main; enables to operate with maxHits (LuceneMoteur; chercherDansIndex)
    public static String filePath_lucene; //enables lucene indexing of files
    public static ObservableList<Article> mabaseArticle_stockage_lucene;//to enable lucene search (stockage des resultats)
                                                                        //(and not to make a method chargerArticleDataDuFichier to return value)
    public static String requêteTexte; //enables depicting of search request text in the scene with tableview of results
    /*-------------------------END pour Lucene recherche----------------------*/
           
    @Override
    public void start(Stage stage) throws Exception {
        
        this.primaireStage = stage;
        this.primaireStage.setTitle("MotsApp Application");  
        this.primaireStage.setResizable(false);
        
        stage.setScene(
            createScene(
                loadMainPane()
            )
        );
        
        stage.show();
    }   
    
    private Pane loadMainPane() throws IOException  {
        FXMLLoader loader = new FXMLLoader();
        
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
