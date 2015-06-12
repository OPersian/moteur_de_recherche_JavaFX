package MotsApp.Contrôleurs;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import MotsApp.MainMotsApp;
import MotsApp.Modèles.Article;
import MotsApp.ModèlesGestion.LuceneMoteur;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class RechercheResultatsContrôleur implements Initializable {
    /*
    private TableView<Article> matièreTableView;
    private TableColumn<Article, String> matièreTitre;
    private TableColumn<Article, String> matièreAuteur;
    private TableColumn<Article, String> matièreContenu;
    private TableColumn<Article, LocalDate> matièreDate;
    private TableColumn<Article, URL> matièreSource;   
    */
    
    @FXML private Label resultatsRechercheLabel;
    
    @FXML private TableView<Article> articleTableView;
    @FXML private TableColumn<Article, String> articleTitre;
    @FXML private TableColumn<Article, String> articleAuteur;
    @FXML private TableColumn<Article, String> articleContenu; 
    @FXML private TableColumn<Article, LocalDate> articleDate;
    @FXML  private TableColumn<Article, URL> articleSource;
    //private ObservableList<Article> mabase; //needs to be global in order to show new result with every new request
    @FXML private TableView<?> photoTableView;
    @FXML private TableColumn<?, ?> photoTitre;
    @FXML private TableColumn<?, ?> photoAuteur;
    @FXML private TableColumn<?, ?> photoDescription;
    @FXML private TableColumn<?, ?> photoDate;
    @FXML private TableColumn<?, ?> photoSource;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
        String labelText = "Resultats de la Recherche";
        labelText += ": " + MainMotsApp.requêteTexte;
        resultatsRechercheLabel.setText(labelText);
        // MainMotsApp.requêteTexte = ""; //clear text of request from global variable
        
        if (LuceneMoteur.baseArticleResultats != null){
            MainMotsApp.mabaseArticle_stockage_lucene = LuceneMoteur.baseArticleResultats.getArticleData();
            
            articleTitre.setCellValueFactory(
                cellData -> cellData.getValue().titreProperty());

            articleAuteur.setCellValueFactory(
                cellData -> cellData.getValue().auteurProperty());

            articleContenu.setCellValueFactory(
                cellData -> cellData.getValue().contenuProperty());

            articleDate.setCellValueFactory(
                cellData -> cellData.getValue().dateProperty());

            articleSource.setCellValueFactory(
                cellData -> cellData.getValue().sourceProperty());

            articleTableView.getItems().setAll(MainMotsApp.mabaseArticle_stockage_lucene);
    } 
        
        MainMotsApp.mabaseArticle_stockage_lucene.clear(); //clear results
        articleTableView = null; //clear tableview 

}
    
}
