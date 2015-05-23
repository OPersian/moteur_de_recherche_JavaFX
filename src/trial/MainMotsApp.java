package trial;

import trial.contrôleurs.RootLayoutContrôleur;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import trial.classesGestionContenu.Article;
import trial.classesGestionContenu.BaseDeMatières;
import trial.classesGestionContenu.BaseDesArticles;

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
    public static ObservableList<Article> mabase_stockage;
    //public static ObservableList<Matière> mabase_stockage;
    //public static BaseDeMatières mabase_stockage;    
    //private ObservableList<Article> mabase = FXCollections.observableArrayList() 
    /*------------------------------------------------------------------------*/    
    //accessed MainMotsApp.mabase, not a local mabase (ArticleAjouteController'), 
    //to adoid the following:
    //if ArticleAjouteController reloaded and if local variable used, 
    //previous instances would have been deleted 
    public static BaseDesArticles mabase = new BaseDesArticles(); //to populate tableview in future   
    
    /*--------------------------END pour TableView----------------------------*/
    
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
    
}
