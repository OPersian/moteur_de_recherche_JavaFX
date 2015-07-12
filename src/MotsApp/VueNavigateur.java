package MotsApp;

import MotsApp.Contrôleurs.RootLayoutContrôleur;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Persianova, Golubnycha
 */

public class VueNavigateur {
    
    /*--------------------------navigation sur scenes-------------------------*/ 
    public static final String ROOT_LAYOUT = "/MotsApp/Vues/RootLayout.fxml"; 
    public static final String PAGE_ACCUEIL = "/MotsApp/Vues/PageAccueil.fxml"; 
    public static final String ARTICLE_AJOUTE = "/MotsApp/Vues/ArticleAjoute.fxml"; 
    public static final String ARTICLE_TABLEAU = "/MotsApp/Vues/ArticleTableau.fxml";
    public static final String RESULTATS_RECHERCHE = "/MotsApp/Vues/RechercheResultats.fxml";
    public static final String PHOTO_AJOUTE = "/MotsApp/Vues/PhotoAjoute.fxml"; 
    public static final String PHOTO_TABLEAU = "/MotsApp/Vues/PhotoTableau.fxml";
    public static final String AUTEURS_INFO = "/MotsApp/Vues/Auteurs.fxml";
    /*------------------------END navigation sur scenes-----------------------*/
    
    private static RootLayoutContrôleur mainController;
    
    public static void setMainController(RootLayoutContrôleur mainController){
        VueNavigateur.mainController = mainController;
    }
    
    // cette méthode permet de charger le "sous-vue"
    public static void loadVue(String fxml) {
        try {
            mainController.setView(
                FXMLLoader.load(
                    VueNavigateur.class.getResource(
                        fxml
                    )
                )         
            );
            // voir explication du champ couranteSousVue (MainMotsApp)
            MainMotsApp.couranteSousVue = fxml;
            // à déboguer dans la console
            System.out.println(MainMotsApp.couranteSousVue);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // peupler la liste des articles quand on charge l'application
        if (MainMotsApp.mabaseArticle_stockage == null) {
        MainMotsApp.mabaseArticle_stockage = MainMotsApp.mabaseArticle_onload;
        }
        // peupler la liste des photos quand on charge l'application
        if (MainMotsApp.mabasePhoto_stockage == null) {
        MainMotsApp.mabasePhoto_stockage = MainMotsApp.mabasePhoto_onload;
        }
    }
    
    /*---------regles de relations entre vues-types de matieres---------------*/ 
    public static Boolean nonMatiereVueCheck(){
        return (MainMotsApp.couranteSousVue != VueNavigateur.PAGE_ACCUEIL && 
                MainMotsApp.couranteSousVue != VueNavigateur.RESULTATS_RECHERCHE &&
                MainMotsApp.couranteSousVue != VueNavigateur.AUTEURS_INFO);
    }
    public static Boolean articleVueCheck(){
        return (MainMotsApp.couranteSousVue == VueNavigateur.ARTICLE_AJOUTE || 
                MainMotsApp.couranteSousVue == VueNavigateur.ARTICLE_TABLEAU);
    }
    public static Boolean photoVueCheck(){
        return (MainMotsApp.couranteSousVue == VueNavigateur.PHOTO_AJOUTE || 
                MainMotsApp.couranteSousVue == VueNavigateur.PHOTO_TABLEAU);
    }
    /*------------------------------END regles--------------------------------*/
}
