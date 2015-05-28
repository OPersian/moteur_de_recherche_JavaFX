package trial.Contrôleurs;

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
import trial.MainMotsApp;
import trial.Modèles.Article;
import trial.ModèlesGestion.LuceneMoteur;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class ArticleRechercheResultatsController implements Initializable {
    
    @FXML private TableView<Article> matièreTableView;
    
    @FXML private TableColumn<Article, String> matièreTitre;
    @FXML private TableColumn<Article, String> matièreAuteur;
    @FXML private TableColumn<Article, String> matièreContenu;
    @FXML private TableColumn<Article, LocalDate> matièreDate;
    @FXML private TableColumn<Article, URL> matièreSource;    
    
    @FXML private TextField titreTextField;
    @FXML private TextField auteurTextField;
    @FXML private TextField contenuTextField;
    @FXML private TextField dateTextField;
    @FXML private TextField sourceTextField;  
    

    @FXML private Label resultatsRechercheLabel;
    
    //private ObservableList<Article> mabase; //needs to be global in order to show new result with every new request

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        if (LuceneMoteur.baseArticleResultats != null){
            MainMotsApp.mabaseArticle_stockage_lucene = LuceneMoteur.baseArticleResultats.getArticleData();
            
            matièreTitre.setCellValueFactory(
                cellData -> cellData.getValue().titreProperty());

            matièreAuteur.setCellValueFactory(
                cellData -> cellData.getValue().auteurProperty());

            matièreContenu.setCellValueFactory(
                cellData -> cellData.getValue().contenuProperty());

            matièreDate.setCellValueFactory(
                cellData -> cellData.getValue().dateProperty());

            matièreSource.setCellValueFactory(
                cellData -> cellData.getValue().sourceProperty());

            matièreTableView.getItems().setAll(MainMotsApp.mabaseArticle_stockage_lucene);
    } 
        MainMotsApp.mabaseArticle_stockage_lucene.clear(); //clear results
        matièreTableView = null; //clear tableview 
        String labelText = "Resultats de la Recherche";
        labelText += ": " + MainMotsApp.requêteTexte;
        resultatsRechercheLabel.setText(labelText);
        MainMotsApp.requêteTexte = ""; //clear text of request from global variable
        

}
    
}
