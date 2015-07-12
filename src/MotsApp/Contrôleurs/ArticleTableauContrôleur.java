package MotsApp.Contrôleurs;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import MotsApp.Modèles.Article;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import MotsApp.MainMotsApp;
import MotsApp.ModèlesGestion.AlertGestion;
import MotsApp.ModèlesGestion.FormatAdapteur;
import MotsApp.ModèlesGestion.LuceneMoteur;
import MotsApp.ModèlesGestion.ioXmlGestion;
import MotsApp.VueNavigateur;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class ArticleTableauContrôleur implements Initializable {
    
    @FXML private TableView<Article> matièreTableView;
    
    @FXML private TableColumn<Article, String> matièreTitre;
    @FXML private TableColumn<Article, String> matièreAuteur;
    @FXML private TableColumn<Article, String> matièreContenu;
    @FXML private TableColumn<Article, LocalDate> matièreDate;
    @FXML private TableColumn<Article, URL> matièreSource;    
    
    private TextField titreTextField;
    private TextField auteurTextField;
    private TextField contenuTextField;
    private TextField dateTextField;
    private TextField sourceTextField;  
       
    private ObservableList<Article> mabase;     
    @FXML private Button ajouteA_btn;
    @FXML private Button suprimeA_btn;
    @FXML
    private Button copierA_btn;
    
    private void ajouteArticleToTable(ActionEvent event) throws MalformedURLException {
        
        // formatage de types specifiques (localdate, url) :
        LocalDate date = FormatAdapteur.dateFormat(dateTextField.getText());        
        URL source = FormatAdapteur.urlFormat(sourceTextField.getText());

        // à obtenir les données entrés par l'utilisateur :
        mabase.add(new Article(titreTextField.getText(),
            auteurTextField.getText(),
            contenuTextField.getText(),
            date,
            source
            )
        );
        // à mettre les données entrés par l'utilisateur 
        // dans la nouvelle instance de la classe Article :
        titreTextField.setText(null);
        auteurTextField.setText(null);
        contenuTextField.setText(null);
        dateTextField.setText(null);
        sourceTextField.setText(null);
  
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        //populate from Articles collection only if any Article has been saved to
        // collection with ArticleAjoute
        
        if (MainMotsApp.mabaseArticle_stockage != null){
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

            matièreTableView.getItems().setAll(MainMotsApp.mabaseArticle_stockage);
        }
              
    }    

    MainMotsApp mainMotsApp = new MainMotsApp();//replace showOpenDialog' arg with null and delete this field! (to check if possible)
    
    @FXML
    private void lireDuFichier(ActionEvent event) throws Exception {

        ioXmlGestion.fichierOuvrir(false); // save current article list
        AlertGestion.displayInfoAlert("Attendez","Exécution de l'indexation",
                    "SVP, attendez qqn sec tandis que l'indexation est terminée \n Cliquez sur ok");
        LuceneMoteur.créerIndex(); // Lucene indexation
        VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU);//reload view 
        //to see changes instantly after adding data from the file to the current "observable list" of articles
    }

    @FXML
    private void suprArticleTable(ActionEvent event) {
        //obtenir index de la ligne sélectionnée
         int row = matièreTableView.getSelectionModel().getSelectedIndex();
         matièreTableView.getItems().remove(row); //supprimer la ligne sélectionnée
    }

    @FXML
    private void ajouterToTable(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.ARTICLE_AJOUTE);
    }

  /*  @FXML
    private void copierArticleTable(ActionEvent event) {
       int row = matièreTableView.getSelectionModel().getSelectedIndex(); //obtenir index de la ligne sélectionnée
          
       //affecter aux variables statiques les données de la ligne sélectionnée
       ArticleAjouteContrôleur.titreA = matièreTitre.getCellData(row);
       ArticleAjouteContrôleur.auteurA = matièreAuteur.getCellData(row);
       ArticleAjouteContrôleur.contenuA = matièreContenu.getCellData(row);
       ArticleAjouteContrôleur.dateA = matièreDate.getCellData(row).toString();
       ArticleAjouteContrôleur.sourceA = matièreSource.getCellData(row).toString();
          
       VueNavigateur.loadVue(VueNavigateur.ARTICLE_AJOUTE);
    }*/
    
}
