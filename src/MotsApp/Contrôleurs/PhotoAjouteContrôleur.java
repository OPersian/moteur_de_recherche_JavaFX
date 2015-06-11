package MotsApp.Contrôleurs;

//import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;
//import java.lang.ClassLoader;
//import java.net.URLStreamHandler.toExternalForm(URL)
//import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import javafx.scene.image.Image;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import static com.sun.org.apache.xalan.internal.utils.SecuritySupport.getResourceAsStream;
import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;
import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.util.Iterator;
import java.util.ResourceBundle;
//import javafx.application.Platform;
//import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.ImageInputStream;
//import static org.apache.lucene.util.ToStringUtils.byteArray;
import MotsApp.MainMotsApp;
import MotsApp.VueNavigateur;
import MotsApp.Modèles.BaseDeMatières;
import MotsApp.Modèles.BaseDesPhotos;
import MotsApp.Modèles.Photo;
import MotsApp.ModèlesGestion.FormatAdapteur;
//import it.sauronsoftware.base64.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static java.io.File.separator;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import static javafx.scene.input.KeyCode.F;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.BaseNCodec;
import org.w3c.dom.*;

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
   // private byte[] imgV;
    @FXML private Button voir_btn;
   // private Object imageCellView;
    @FXML private Color x2;
    @FXML private Font x1;
    @FXML private Label resolution;
    
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
    
    String img;
    String imgU;
    String imgC;
        
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
            String imgC = imgU.substring(6, imgU.length()); // C:/...
            //System.out.println(imgU);// print URL file:/C:/...
            
            // image from file into BufferedImage
            File sourceimage = new File(imgC);
            //System.out.println(sourceimage); // print URI file:\C:\...
            BufferedImage bufferedImage = ImageIO.read(sourceimage);
            //String rename = textFieldTitre.getText(); // renommer le fichier tel qu'il titre
            // image from BufferedImage into file
            //File outputfile = new File("MotsAppImages/"+rename); 
            
            // File outputfile = new File("MotsAppImages/"+sourceimage.getName());
            File outputfile = new File(sourceimage.getName());
            ImageIO.write(bufferedImage, "png", outputfile); 
            img = outputfile.toURI().toURL().toString(); 
            //outputfile.getTotalSpace();
            
            
            // display image in ImageView            
            Image image = new Image(img);
            imgView.setImage(image);
            double h = image.getHeight();
            double w = image.getWidth();
            System.out.printf("Resolution: %.0f x %.0f px \n",image.getHeight(),image.getWidth());
            // complete textFieldSource
            textFieldSource.setText(imgC);
            resolution.setText("Resolution: "+h+" x "+w+" px");
       
    }
}
 
    @FXML
    private void sauvegarder(ActionEvent event) throws MalformedURLException {
        
        LocalDate date = FormatAdapteur.dateFormat(textFieldDate.getText());
        URL source = FormatAdapteur.urlFormat(img);
                      
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