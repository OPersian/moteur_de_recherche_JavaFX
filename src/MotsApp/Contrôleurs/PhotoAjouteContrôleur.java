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
import javax.imageio.ImageIO;
import MotsApp.MainMotsApp;
import MotsApp.VueNavigateur;
import MotsApp.Modèles.BaseDeMatières;
import MotsApp.Modèles.BaseDesPhotos;
import MotsApp.Modèles.Photo;
import MotsApp.ModèlesGestion.FormatAdapteur;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.swing.filechooser.FileSystemView;


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
    @FXML
    private Label labelSource;
    
    //Element node;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    BaseDesPhotos mabasePhoto = new BaseDesPhotos();
    BaseDeMatières mabase2 = new BaseDeMatières();
    
    //String img;
    String imgU;
    //String imgC;
        
        @FXML
    public void uploadPhoto(ActionEvent event) throws MalformedURLException, IOException {
        
       FileChooser fileChooser= new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
               "Image Files", "*.png", "*.jpg", "*.gif");
       fileChooser.getExtensionFilters().add(extFilter);
       fileChooser.setTitle("Choisissez le fichier Image!");
       File file = fileChooser.showOpenDialog(new Stage());
       if(file != null){
            //System.out.println(file); //print URI C:\...
            imgU = file.toURI().toURL().toString();//URI to URL
            //imgC = imgU.substring(6, imgU.length()); // C:/...
            //System.out.println(imgU);// print URL file:/C:/...
            
            // image from file into BufferedImage
           // File sourceimage = new File(imgC);
            //System.out.println(sourceimage); // print URI file:\C:\...
           // BufferedImage bufferedImage = ImageIO.read(sourceimage);
            //String rename = textFieldTitre.getText(); // renommer le fichier tel qu'il titre
            // image from BufferedImage into file
            //File outputfile = new File("MotsAppImages/"+rename);   
            
            //File outputfile = new File(System.getProperty("user.home")+"/MotsAppImages/"); 
          //  File outputfile = new File("MotsAppImages/"); 
           // outputfile.mkdir(); //creer le dossier "MotsAppImages"
           // outputfile = new File("MotsAppImages/"+sourceimage.getName());
            
            //File outputfile = new File(sourceimage.getName());
           // ImageIO.write(bufferedImage, "png", outputfile); 
           // img = outputfile.toURI().toURL().toString(); 
            //System.out.println(outputfile);
            //outputfile.getTotalSpace();
           // File sourceimageHome = new File(System.getProperty("user.home"));
            //System.out.println(sourceimageHome);
           // FileSystemView.getFileSystemView().getHomeDirectory();
            
            
            // display image in ImageView            
            Image image = new Image(imgU);
            imgView.setImage(image);
            double h = image.getHeight();
            double w = image.getWidth();
            System.out.printf("Resolution: %.0f x %.0f px \n",image.getHeight(),image.getWidth());
            // complete textFieldSource
            textFieldSource.setText(imgU);
            resolution.setText("Resolution: "+h+" x "+w+" px");
            //labelSource.setText("Local source de votre image: "+imgC);
    }
}
 
    @FXML
    private void sauvegarder(ActionEvent event) throws MalformedURLException {
        
        LocalDate date = FormatAdapteur.dateFormat(textFieldDate.getText());
        URL source = FormatAdapteur.urlFormat(textFieldSource.getText());
                      
        Photo monPhoto;
        monPhoto = new Photo(
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
       VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);  
    }

 }