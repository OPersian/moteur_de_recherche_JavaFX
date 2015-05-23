package trial.contrôleurs;

import java.net.MalformedURLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import trial.classesGestionContenu.Article;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import trial.MainMotsApp;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */

public class TrialTableViewController implements Initializable{
 
    
    @FXML private TableView<Article> matièreTableauVue;
    @FXML private TableColumn<Article, String> matièreTitre;
    @FXML private TableColumn<Article, String> matièreAuteur;
    @FXML private TableColumn<Article, LocalDate> matièreDate;
    @FXML private TableColumn<Article, URL> matièreSource; 
    
    private ObservableList<Article> mabase;     
    // Reference to the main application.
    private MainMotsApp mainMotsApp;    
    /**
    * The constructor.
    * The constructor is called before the initialize() method.
    */
    public TrialTableViewController() {}

    
   
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*
        matièreTitre.setCellValueFactory(new PropertyValueFactory<Article, String>("titre"));
        matièreAuteur.setCellValueFactory(new PropertyValueFactory<Article, String>("auteur"));
        try {
            //matièreDate.setCellValueFactory(new ObservableValue<Article, LocalDate>("date"));
            //matièreSource.setCellValueFactory(new ObservableValue<Article, URL>("source"));
            //matièreTableauVue.getItems().setAll(getArticleData());
            matièreTableauVue.getItems().setAll(getTableauData());
        } catch (MalformedURLException ex) {
            Logger.getLogger(TrialTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        
        /*
        matièreTitre.setCellValueFactory(
            cellData -> cellData.getValue().titreProperty());
        
        matièreAuteur.setCellValueFactory(
            cellData -> cellData.getValue().auteurProperty());
        
        matièreDate.setCellValueFactory(
            cellData -> cellData.getValue().dateProperty());
        
        matièreSource.setCellValueFactory(
            cellData -> cellData.getValue().sourceProperty());
                
        try {
            matièreTableauVue.getItems().setAll(getTableauData());
        } catch (MalformedURLException ex) {
            Logger.getLogger(TrialTableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        // Set the columns width auto size
    /*matièreTableauVue.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    matièreTableauVue.getColumns().get(0).prefWidthProperty().bind(matièreTableauVue.widthProperty().multiply(0.33));    // 33% for id column size
    matièreTableauVue.getColumns().get(1).prefWidthProperty().bind(matièreTableauVue.widthProperty().multiply(0.33));   // 33% for dt column size
    matièreTableauVue.getColumns().get(2).prefWidthProperty().bind(matièreTableauVue.widthProperty().multiply(0.33));    // 33% for cv column size
    matièreTableauVue.getItems().setAll(this.mabase);*/
        
    }  
    
    
    
    //Is called by the main application to give a reference back to itself.
    public void setMainMotsApp(MainMotsApp mainMotsApp) {
       this.mainMotsApp = mainMotsApp;

       // Add observable list data to the table
      /* matièreTableauVue.setItems(mainMotsApp.getArticleData()); */
    }
    
}
