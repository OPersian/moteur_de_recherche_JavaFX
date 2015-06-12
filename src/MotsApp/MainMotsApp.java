package MotsApp;

import java.io.File;
import MotsApp.Contrôleurs.RootLayoutContrôleur;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import MotsApp.Modèles.Article;
import MotsApp.ModèlesGestion.ArticleListeEmballage;
import MotsApp.Modèles.BaseDesPhotos;
import MotsApp.Modèles.BaseDesArticles;
import MotsApp.Modèles.Photo;
import MotsApp.ModèlesGestion.PhotoListeEmballage;

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

    
 /**
 * Loads article data from the specified file. The current article data will
 * be safe.
 * 
 * @param file
     * @return 
     * @throws java.lang.Exception
 */
public static void chargerArticleDataDuFichier(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(ArticleListeEmballage.class);
        Unmarshaller um = context.createUnmarshaller();
        
        // Reading XML from the file and unmarshalling.
        ArticleListeEmballage wrapper = (ArticleListeEmballage) um.unmarshal(file);
        
        // uncomment to delete current articles (that was added within a session)
        // mabaseArticle_stockage.clear(); // attention: not mabaseArticle!
        
        // Adding wrapped article data to the current observableList of articles.
        mabaseArticle_stockage.addAll(wrapper.getArticles());
        
        filePath_lucene = file.getPath(); //for lucene index
        mabaseArticle_stockage_lucene = mabaseArticle_stockage; //for lucene index
        
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not load data from file:\n" + 
                file.getPath() + "\n" + e.toString());
    }
}

/**
 * Saves the article data list to the specified file.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public void sauvegarderArticleDataToFile(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(ArticleListeEmballage.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping the article data.
        ArticleListeEmballage wrapper = new ArticleListeEmballage();
        wrapper.setArticles(mabaseArticle_stockage);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);
        
        filePath_lucene = file.getPath(); //to lucene index
        
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not save data to file:\n" + file.getPath() +
                            "\n" + e.toString());
    } 
}

/**
 * Loads photo data from the specified file. The current photo data will
 * be replaced.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public static void chargerPhotoDataDuFichier(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PhotoListeEmballage.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PhotoListeEmballage wrapper = (PhotoListeEmballage) um.unmarshal(file);

        mabasePhoto_stockage.clear();//not mabase!
        mabasePhoto_stockage.addAll(wrapper.getPhotos());

        // Save the file path to the registry.
        // setMatièreFichierChemin(file);
        filePath_lucene = file.getPath(); //to lucene index

    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not load data from file:\n" + 
                file.getPath() + "\n" + e.toString());
    }
}

/**
 * Saves the photo data list to the specified file.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public void sauvegarderPhotoDataToFile(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PhotoListeEmballage.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our article data.
        PhotoListeEmballage wrapper = new PhotoListeEmballage();
        wrapper.setPhotos(mabasePhoto_stockage);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        // setMatièreFichierChemin(file);
        filePath_lucene = file.getPath(); //to lucene index
        
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not save data to file:\n" + file.getPath() +
                            "\n" + e.toString());
    } 
}
       
}
