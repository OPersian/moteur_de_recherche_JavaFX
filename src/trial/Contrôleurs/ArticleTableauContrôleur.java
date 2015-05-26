package trial.Contrôleurs;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import trial.Modèles.Article;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import trial.MainMotsApp;
import trial.VueNavigateur;

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
    
    @FXML private TextField titreTextField;
    @FXML private TextField auteurTextField;
    @FXML private TextField contenuTextField;
    @FXML private TextField dateTextField;
    @FXML private TextField sourceTextField;  
       
    private ObservableList<Article> mabase;     
    // Reference to the main application.
    
    @FXML
    private void ajouteArticleToTable(ActionEvent event) throws MalformedURLException {
        
        ObservableList<Article> mabase = matièreTableView.getItems();
        //private ObservableList<Article> mabase = FXCollections.observableArrayList();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-"
                + "MM-yyyy");    
        LocalDate date = LocalDate.now();
        String date_format = dateTextField.getText();
        try {
            date = LocalDate.parse(date_format, formatter);
        } 
        catch (DateTimeParseException e) {
            System.out.println("Date format est incorrect!");
        }  
        
        String monurl = sourceTextField.getText();   
        //URL source = new URL(monurl); 
        URL source = new URL("http://sample.com.ua");                
        try {
            source = new URL(monurl);
        }
        catch (MalformedURLException e) {
            System.out.println("URL format incorrect!");
        }     
        
        mabase.add(new Article(titreTextField.getText(),
            auteurTextField.getText(),
            contenuTextField.getText(),
            date,
            source
            )
        );
        
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

    MainMotsApp mainMotsApp = new MainMotsApp();
    
    @FXML
    private void lireDuFichier(ActionEvent event) throws Exception {
        
        FileChooser fileChooser = new FileChooser();
        
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainMotsApp.getPrimaireStage());

        if (file != null) {
            mainMotsApp.chargerArticleDataDuFichier(file);
        }
        VueNavigateur.loadVista(VueNavigateur.ARTICLE_TABLEAU);//reload view 
            //to see changes instantly
        
    }
    
}
