package MotsApp;

import MotsApp.Contrôleurs.RootLayoutContrôleur;
import java.io.IOException;
import javafx.fxml.FXMLLoader;


/**
 *
 * @author Persianova, Golubnycha
 */

public class VueNavigateur {
    
    /*--------------------------navigation sur scenes----------------------------*/ 
    public static final String ROOT_LAYOUT = "/MotsApp/Vues/RootLayout.fxml"; 
    public static final String PAGE_ACCUEIL = "/MotsApp/Vues/PageAccueil.fxml"; 
    public static final String ARTICLE_AJOUTE = "/MotsApp/Vues/ArticleAjoute.fxml"; 
    public static final String ARTICLE_TABLEAU = "/MotsApp/Vues/ArticleTableau.fxml";
    public static final String RESULTATS_RECHERCHE = "/MotsApp/Vues/RechercheResultats.fxml";
    public static final String PHOTO_AJOUTE = "/MotsApp/Vues/PhotoAjoute.fxml"; 
    public static final String PHOTO_TABLEAU = "/MotsApp/Vues/PhotoTableau.fxml";
    /*------------------------END navigation sur scenes------------------------*/
    
    private static RootLayoutContrôleur mainController;
    
    
    public static void setMainController(RootLayoutContrôleur mainController){
        VueNavigateur.mainController = mainController;
    }
    
    public static void loadVue(String fxml) {
        try {
            mainController.setView(
                FXMLLoader.load(
                    VueNavigateur.class.getResource(
                        fxml
                    )
                )         
            );
            MainMotsApp.couranteSousVue = fxml;   
            System.out.println(MainMotsApp.couranteSousVue);//to debug in console
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
