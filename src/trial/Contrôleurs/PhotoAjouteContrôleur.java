package trial.Contrôleurs;

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
import trial.VueNavigateur;
import trial.Modèles.BaseDeMatières;
import trial.Modèles.BaseDePhoto;
import trial.Modèles.Photo;

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
    @FXML private ImageView imgV;
    @FXML private Button voir_btn;
   // private Object imageCellView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    BaseDePhoto mabasePhoto = new BaseDePhoto();
    BaseDeMatières mabase2 = new BaseDeMatières();
    
        @FXML
    public void uploadPhoto(ActionEvent event) throws MalformedURLException {
        
       FileChooser fileChooser= new FileChooser();
         fileChooser.setTitle("Choisissez le fichier!");
         File file = fileChooser.showOpenDialog(new Stage());
         if(file != null){
             System.out.println(file);
             String img = file.toURI().toURL().toString();
             Image image = new Image(img);
             //file.getName();
             //imageView.setFitWidth(150);
             //imageView.setFitHeight(150);
             imgV.setImage(image);
        }
    }

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
        
        Photo monPhoto;
        monPhoto = new Photo(
                imgV,
                textFieldTitre.getText(),
                textFieldAuteur.getText(),
                textAreaDescription.getText(),
                date,
                source);                
                
        mabase2.ajouterMatière(monPhoto);//to debug in console
        mabase2.baseAfficher();//to debug in console
        MainMotsApp.mabasePhoto.ajouterPhoto(monPhoto);
        MainMotsApp.mabasePhoto.photoAfficher();
        MainMotsApp.mabasePhoto_stockage = MainMotsApp.mabasePhoto.getPhotoData();
        
        try {successMsgLabel.setText("L'image a été bien sauvegardé!");}
        catch (Exception e) {
            successMsgLabel.setText("On ne peut pas enregistrer vos donnés!");
        }   
    }

    @FXML
    private void voir(ActionEvent event) {
        VueNavigateur.loadVista(VueNavigateur.PHOTO_TABLEAU); 
    }
}