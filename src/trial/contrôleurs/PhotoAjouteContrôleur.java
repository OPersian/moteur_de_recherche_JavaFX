package trial.contrôleurs;

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import trial.MainMotsApp;
import trial.classesGestionContenu.Article;
import trial.classesGestionContenu.BaseDeMatières;
import trial.classesGestionContenu.Photo;

/**
 * FXML Controller class
 *
 * @author Olena
 */
public class PhotoAjouteContrôleur implements Initializable {    
      
    @FXML private Button save_btn;
    @FXML private TextField textFieldTitre;
    @FXML private TextField textFieldDate;
    @FXML private TextField textFieldAuteur;
    @FXML private TextField textFieldSource;
    @FXML private Label successMsgLabel;
    @FXML private Button upload_btn;
    @FXML private TextArea textAreaDescription;
    @FXML private ImageView imageView;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    BaseDeMatières mabase = new BaseDeMatières();
    //private ObservableList<Article> mabase2 = FXCollections.observableArrayList();    
    

    @FXML
    private void sauvegarder(ActionEvent event) throws MalformedURLException {
    //private ObservableList<Article> sauvegarder(ActionEvent event) throws MalformedURLException {

        
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
        
        Photo monPhoto = new Photo(
                textFieldTitre.getText(),
                textFieldAuteur.getText(),
                date,
                source, 
                textAreaDescription.getText(),
                imageView);
        //System.out.println(monArticle.get_titre());
        //System.out.println(monArticle);
        
        mabase.ajouterMatière(monPhoto);
        mabase.baseAfficher();
        
        try {successMsgLabel.setText("L'image a été bien sauvegardé!");}
        catch (Exception e) {
            successMsgLabel.setText("On ne peut pas enregistrer vos donnés!");
        }          
        
        //mabase2.add(monArticle);
        //return mabase2;
    }

    @FXML
    private void uploadPhoto(ActionEvent event) throws MalformedURLException {
        
       FileChooser fileChooser= new FileChooser();
         fileChooser.setTitle("Choisissez le fichier!");
         File file = fileChooser.showOpenDialog(new Stage());
         if(file != null){
             System.out.println(file);
             String img = file.toURI().toURL().toString();
             Image image = new Image(img);
             //imageView.setFitWidth(150);
             //imageView.setFitHeight(150);
             imageView.setImage(image);
        }
    }
}