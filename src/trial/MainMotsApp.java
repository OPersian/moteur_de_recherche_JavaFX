package trial;

import java.io.File;
import trial.Contrôleurs.RootLayoutContrôleur;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import trial.Modèles.Article;
import trial.Modèles.ArticleListWrapper;
import trial.Modèles.BaseDePhoto;
import trial.Modèles.BaseDesArticles;
import trial.Modèles.Photo;
import trial.Modèles.PhotoListWrapper;

/**
 *
 * @author Persianova, Golubnycha
 */

public class MainMotsApp extends Application {
    
    /*--------------------------gestion des scenes----------------------------*/    
    //http://stackoverflow.com/questions/19602727/how-to-reference-javafx-fxml-files-in-resource-folder    
    public Stage primaireStage; //private    
    /*------------------------END gestion des scenes--------------------------*/    
    
    
    /*----------------------------pour TableView------------------------------*/
    public static ObservableList<Article> mabaseArticle_stockage;
    public static ObservableList<Photo> mabasePhoto_stockage;
    //public static ObservableList<Matière> mabase_stockage;
    //public static BaseDeMatières mabase_stockage;    
    //private ObservableList<Article> mabase = FXCollections.observableArrayList() 
    /*------------------------------------------------------------------------*/    
    //accessed MainMotsApp.mabase, not a local mabase (ArticleAjouteController'), 
    //to adoid the following:
    //if ArticleAjouteController reloaded and if local variable used, 
    //previous instances would have been deleted 
    public static BaseDesArticles mabaseArticle = new BaseDesArticles(); //to populate tableview in future  
    public static BaseDePhoto mabasePhoto = new BaseDePhoto(); 
    /*--------------------------END pour TableView----------------------------*/
    
    public static String currentSousVue; //to parametrize open/save menu buttons' methods
                                    //whether we work with photos or articles 
    
    @Override
    public void start(Stage stage) throws Exception {
        
        this.primaireStage = stage;
        //http://docs.oracle.com/javafx/2/fxml_get_started/fxml_tutorial_intermediate.htm#CACHBAEJ 
        //stage.setTitle("MotsApp Application");        
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
        VueNavigateur.loadVista(VueNavigateur.PAGE_ACCUEIL);
        
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
 * Returns the article file preference, i.e. the file that was last opened.
 * The preference is read from the OS specific registry. If no such
 * preference can be found, null is returned.
 * 
 * @return
 */
public File getMatièreFichierChemin() {
    Preferences prefs = Preferences.userNodeForPackage(MainMotsApp.class);
    //String filePath = prefs.get("/trial/fichiers/file1.xml", null);
    String filePath = prefs.get("LAST_OUTPUT_DIR", null);

    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
public void setMatièreFichierChemin(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainMotsApp.class);
    if (file != null) {
        //prefs.put("/trial/fichiers/file1.xml", file.getPath());
        prefs.put("LAST_OUTPUT_DIR", file.getPath());

        // Update the stage title.
        primaireStage.setTitle("MotsApp - " + file.getName()); //DOESNT WORK!
        //even tried this.primaireStage
    } else {
        prefs.remove("/trial/fichiers/file1.xml");

        // Update the stage title.
        primaireStage.setTitle("MotsApp");//DOESNT WORK!
        //even tried this.primaireStage
    }
}
    
    /**
     * Returns the main stage. Useful to work with files (IO) -- OPersian
     * @return
     */
    public Stage getPrimaireStage() {
        return primaireStage;
        //even tried this.primaireStage
    }    
    
    
 /**
 * Loads article data from the specified file. The current person data will
 * be replaced.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public void chargerArticleDataDuFichier(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(ArticleListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        ArticleListWrapper wrapper = (ArticleListWrapper) um.unmarshal(file);
        
        //uncomment, if you want to delete articles current (added within a session)
        //mabaseArticle_stockage.clear();//not mabaseArticle!
        
        mabaseArticle_stockage.addAll(wrapper.getArticles());

        // Save the file path to the registry.
        setMatièreFichierChemin(file);

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
                .newInstance(ArticleListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our article data.
        ArticleListWrapper wrapper = new ArticleListWrapper();
        wrapper.setArticles(mabaseArticle_stockage);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setMatièreFichierChemin(file);
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not save data to file:\n" + file.getPath() +
                            "\n" + e.toString());
    } 
}

/**
 * Loads photo data from the specified file. The current person data will
 * be replaced.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public void chargerPhotoDataDuFichier(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PhotoListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PhotoListWrapper wrapper = (PhotoListWrapper) um.unmarshal(file);

        mabasePhoto_stockage.clear();//not mabase!
        mabasePhoto_stockage.addAll(wrapper.getPhotos());

        // Save the file path to the registry.
        setMatièreFichierChemin(file);

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
                .newInstance(PhotoListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our article data.
        PhotoListWrapper wrapper = new PhotoListWrapper();
        wrapper.setPhotos(mabasePhoto_stockage);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setMatièreFichierChemin(file);
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not save data to file:\n" + file.getPath() +
                            "\n" + e.toString());
    } 
}
       
}
