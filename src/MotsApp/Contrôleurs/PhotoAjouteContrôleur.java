package MotsApp.Contrôleurs;

import java.io.File;
import java.io.IOException;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import MotsApp.MainMotsApp;
import MotsApp.VueNavigateur;
import MotsApp.Modèles.Photo;
import MotsApp.ModèlesGestion.FormatAdapteur;
import MotsApp.ModèlesGestion.LuceneMoteur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * FXML Controller class
 *
 * @author Olena, Olga
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
    @FXML private ImageView imgView;
    @FXML private Button voir_btn;
    @FXML private Color x2;
    @FXML private Font x1;
    @FXML private Label resolution;
    @FXML private Label labelSource;
    
  /*  
    //initialiser les variables statiques pour remplir les champs par les données de le tableview
    public static String titre;
    public static String auteur;
    public static String description;
    public static String date;
    public static String source;*/
    
    //Element node;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    /*    
        //remplir les champs par les données de le tableview
        textFieldTitre.setText(titre);
        textFieldAuteur.setText(auteur);
        textAreaDescription.setText(description);
        textFieldDate.setText(date);
        textFieldSource.setText(source);*/

    }    

    String imgU;

    /**
    * Opens a FileChooser to let the user select the data (file) to load.
    */
    
    @FXML
    public void uploadPhoto(ActionEvent event) throws MalformedURLException, IOException {
       
       // Set extension filter; only .png, .jpg and .gif files can be read
       FileChooser fileChooser= new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
               "Image Files", "*.png", "*.jpg", "*.gif");
       fileChooser.getExtensionFilters().add(extFilter);
       fileChooser.setTitle("Choisissez le fichier Image!");
       File file = fileChooser.showOpenDialog(new Stage());
       
       if(file != null){
            imgU = file.toURI().toURL().toString();//URI to URL
            
            // display image in ImageView            
            Image image = new Image(imgU);
            imgView.setImage(image);
            double h = image.getHeight();
            double w = image.getWidth();
            System.out.printf("Resolution: %.0f x %.0f px \n",image.getHeight(),image.getWidth());
            
            // complete textFieldSource and resolution
            textFieldSource.setText(imgU);
            resolution.setText("Resolution: "+h+" x "+w+" px");
           
    }
}
 
    @FXML
    private void sauvegarder(ActionEvent event) {
        
        try {
            
            // formatage de types specifiques : localdate, url
            LocalDate date = FormatAdapteur.dateFormat(textFieldDate.getText());
            URL source = FormatAdapteur.urlFormat(textFieldSource.getText());
           
            
            Photo monPhoto;
            monPhoto = new Photo(
                    textFieldTitre.getText(),
                    textFieldAuteur.getText(),
                    textAreaDescription.getText(),
                    date,
                    source);
            
            // ajouter le nouvel objet photo dans la liste "courante"
            MainMotsApp.mabasePhoto_stockage.add(monPhoto);
            
            // Lucene indexation
            try {
                LuceneMoteur.créerIndex();
            } catch (IOException ex) {
                successMsgLabel.setText("Erreur d'indexation!");
            }
            
            successMsgLabel.setText("L'image a été bien sauvegardé!");

        }
        catch (MalformedURLException ex) {
            successMsgLabel.setText("On ne peut pas enregistrer vos donnés!");
        }   
    }

    @FXML
    private void voir(ActionEvent event) {
       VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);  
    }

 }