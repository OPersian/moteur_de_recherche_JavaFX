package trial;

import trial.contrôleurs.RootLayoutContrôleur;
import java.io.IOException;
import javafx.fxml.FXMLLoader;


/**
 *
 * @author Persianova, Golubnycha
 */

public class VueNavigateur {
    
    /*--------------------------navigation sur scenes----------------------------*/ 
    public static final String ROOT_LAYOUT = "/trial/fichiersFxml/RootLayout.fxml"; 
    public static final String PAGE_ACCUEIL = "/trial/fichiersFxml/PageAccueil.fxml"; 
    public static final String ARTICLE_AJOUTE = "/trial/fichiersFxml/ArticleAjoute.fxml";    
    public static final String PHOTO_AJOUTE = "/trial/fichiersFxml/PhotoAjoute.fxml"; 
    public static final String MATIÉRE_TABLEAU = "/trial/fichiersFxml/MatièreTableau.fxml"; 
    /*------------------------END navigation sur scenes------------------------*/
    
    private static RootLayoutContrôleur mainController;
    
    public static void setMainController(RootLayoutContrôleur mainController){
        VueNavigateur.mainController = mainController;
    }
    
    public static void loadVista(String fxml) {
        try {
            mainController.setView(
                FXMLLoader.load(
                    VueNavigateur.class.getResource(
                        fxml
                    )
                )         
            );
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    public static void loadTableauVue() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainMotsApp.class.getResource(MATIÉRE_TABLEAU));
        AnchorPane tableauVue = (AnchorPane) loader.load();
        
        // Set tableauVue overview into the center of root layout.
        //FXMLLoader.load.setCenter(tableauVue);
        
        
    }
    */
}
