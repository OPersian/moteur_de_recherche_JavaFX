package trial.Contrôleurs;

import trial.Modèles.BaseDeMatières;
import trial.Modèles.BaseDesArticles;
import trial.Modèles.Article;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import trial.MainMotsApp;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class ArticleAjouteContrôleur implements Initializable {    
    
    @FXML private Button save_btn;
    @FXML private TextField textFieldTitre;
    @FXML private TextArea textAreaContenu;
    @FXML private TextField textFieldDate;
    @FXML private TextField textFieldAuteur;
    @FXML private TextField textFieldSource;
    @FXML private Label successMsgLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    BaseDesArticles mabase = new BaseDesArticles(); //to populate tableview in future   
    BaseDeMatières mabase2 = new BaseDeMatières(); //just to debug in console; may be useful for images
    
    @FXML
    private void sauvegarder(ActionEvent event) throws MalformedURLException {
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-"
                + "MM-yyyy");    
        LocalDate date = LocalDate.now();
        String date_format = textFieldDate.getText();
        try {
            date = LocalDate.parse(date_format, formatter);
        } 
        catch (DateTimeParseException e) {
            System.out.println("Date format est incorrect!");
        }  
        
        String monurl = textFieldSource.getText();   
        //URL source = new URL(monurl); 
        URL source = new URL("http://sample.com.ua");                
        try {
            source = new URL(monurl);
        }
        catch (MalformedURLException e) {
            System.out.println("URL format incorrect!");
            successMsgLabel.setText("On ne peut pas enregistrer vos donnés!");
        }           
        
        Article monArticle = new Article(
                textFieldTitre.getText(),
                textFieldAuteur.getText(),
                textAreaContenu.getText(),
                date,
                source
            );
        
        mabase2.ajouterMatière(monArticle);//to debug in console
        mabase2.baseAfficher();//to debug in console
        
        //accessed MainMotsApp.mabase, not a local mabase (ArticleAjouteController'), 
        //to adoid the following:
        //if ArticleAjouteController reloaded and if local variable used, 
        //previous instances would have been deleted 
        MainMotsApp.mabaseArticle.ajouterArticle(monArticle);
        MainMotsApp.mabaseArticle.articleAfficher();
        MainMotsApp.mabaseArticle_stockage = MainMotsApp.mabaseArticle.getArticleData();
                
        try {successMsgLabel.setText("L'article a été bien sauvegardé!");}
        catch (Exception e) {
            successMsgLabel.setText("On ne peut pas enregistrer vos donnés!");
        }
        
    }
    
}
