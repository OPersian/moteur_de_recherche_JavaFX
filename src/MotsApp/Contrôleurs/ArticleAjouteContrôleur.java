package MotsApp.Contrôleurs;

import MotsApp.Modèles.Article;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
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
import MotsApp.ModèlesGestion.LuceneMoteur;
import MotsApp.VueNavigateur;
import java.io.IOException;


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
    
 /*
    // initialiser les variables statiques pour remplir les champs par les données de le tableview
    public static String titreA;
    public static String auteurA;
    public static String contenuA;
    public static String dateA;
    public static String sourceA;*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* 
        //remplir les champs par les données de le tableview
        textFieldTitre.setText(titreA);
        textFieldAuteur.setText(auteurA);
        textAreaContenu.setText(contenuA);
        textFieldDate.setText(dateA);
        textFieldSource.setText(sourceA); */
    }    
    
    @FXML
    private void sauvegarder(ActionEvent event) {
        
        try {
            
            // formatage de types specifiques : localdate, url
            LocalDate date = FormatAdapteur.dateFormat(textFieldDate.getText());
            URL source = FormatAdapteur.urlFormat(textFieldSource.getText());
            
            Article monArticle = new Article(
                    textFieldTitre.getText(),
                    textFieldAuteur.getText(),
                    textAreaContenu.getText(),
                    date,
                    source
            );
            
            // ajouter le nouvel objet article dans la liste "courante"
            MainMotsApp.mabaseArticle_stockage.add(monArticle);
            
            // Lucene indexation
            try {
                LuceneMoteur.créerIndex();
            } catch (IOException ex) {
                successMsgLabel.setText("Erreur d'indexation!");
            }
            
            
            successMsgLabel.setText("L'article a été bien sauvegardé!");            
        }
        
        catch (MalformedURLException ex) {
            successMsgLabel.setText("On ne peut pas enregistrer vos donnés!");
        }
        
    }

    @FXML
    private void voirListeArticles(ActionEvent event) {
        VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU); 
    }
    
}
