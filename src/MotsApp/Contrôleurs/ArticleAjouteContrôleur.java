package MotsApp.Contrôleurs;

import MotsApp.Modèles.BaseDeMatières;
import MotsApp.Modèles.BaseDesArticles;
import MotsApp.Modèles.Article;
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
import MotsApp.MainMotsApp;
import MotsApp.ModèlesGestion.FormatAdapteur;
import MotsApp.VueNavigateur;

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
        
        LocalDate date = FormatAdapteur.dateFormat(textFieldDate.getText());
        URL source = FormatAdapteur.urlFormat(textFieldSource.getText());

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
        MainMotsApp.mabaseArticle.articleAfficher(); // to debug in console
        MainMotsApp.mabaseArticle_stockage = MainMotsApp.mabaseArticle.getArticleData();
                
        try {successMsgLabel.setText("L'article a été bien sauvegardé!");}
        
        catch (Exception e) {
            successMsgLabel.setText("On ne peut pas enregistrer vos donnés!");
        }
        
    }

    @FXML
    private void voirListeArticles(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU); 
    }
    
}
