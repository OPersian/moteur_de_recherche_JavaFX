package MotsApp.Contrôleurs;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import MotsApp.Modèles.Photo;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import MotsApp.MainMotsApp;
import MotsApp.ModèlesGestion.AlertGestion;
import MotsApp.ModèlesGestion.LuceneMoteur;
import MotsApp.ModèlesGestion.ioXmlGestion;
import MotsApp.VueNavigateur;


/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class PhotoTableauContrôleur implements Initializable {
    @FXML private TableView<Photo> photoTableView;
    @FXML private TableColumn<Photo, String> photoTitre;
    @FXML private TableColumn<Photo, String> photoAuteur;
    @FXML private TableColumn<Photo, String> photoDescription;
    @FXML private TableColumn<Photo, LocalDate> photoDate;
    @FXML private TableColumn<Photo, URL> photoSource;
    @FXML private Button suprime_btn;
    @FXML private Button ajouter_btn;
    @FXML private Button copier_btn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //populate from Photos collection only if any Photo has been saved to
        // collection with PhotoAjoute
        
        if (MainMotsApp.mabasePhoto_stockage != null){
                          
            photoTitre.setCellValueFactory(
                cellData -> cellData.getValue().titreProperty());
            
            photoAuteur.setCellValueFactory(
                cellData -> cellData.getValue().auteurProperty());

            photoDescription.setCellValueFactory(
                cellData -> cellData.getValue().descriptionProperty());
 
            photoDate.setCellValueFactory(
                cellData -> cellData.getValue().dateProperty());
            
            photoSource.setCellValueFactory(
                cellData -> cellData.getValue().sourceProperty()); 
            
            photoTableView.getItems().setAll(MainMotsApp.mabasePhoto_stockage);
        }
    } 


    @FXML
    private void ajoutePhotoToTable(ActionEvent event) {
     VueNavigateur.loadVue(VueNavigateur.PHOTO_AJOUTE); 
    }
    
   
    @FXML
    private void lireDuFichier(ActionEvent event) throws Exception {      
        ioXmlGestion.fichierOuvrir(false); // save current photo list
        AlertGestion.displayInfoAlert("Attendez","Exécution de l'indexation",
                    "SVP, attendez qqn sec tandis que l'indexation est terminée \n Cliquez sur ok");
        LuceneMoteur.créerIndex(); // Lucene indexation
        VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);//reload view 
        //to see changes instantly after adding data from the file to the current "observable list" of photos
    }

    @FXML
    private void suprPhotoTable(ActionEvent event) {
        //obtenir index de la ligne sélectionnée
       int row = photoTableView.getSelectionModel().getSelectedIndex(); 
       photoTableView.getItems().remove(row); //supprimer la ligne sélectionnée
   }

    /*@FXML
    private void copierPhotoTable(ActionEvent event) {
       int row = photoTableView.getSelectionModel().getSelectedIndex(); //obtenir index de la ligne sélectionnée
      
       //affecter aux variables statiques les données de la ligne sélectionnée
       PhotoAjouteContrôleur.titre = photoTitre.getCellData(row);
       PhotoAjouteContrôleur.auteur = photoAuteur.getCellData(row);
       PhotoAjouteContrôleur.description = photoDescription.getCellData(row);
       PhotoAjouteContrôleur.date = photoDate.getCellData(row).toString();
       PhotoAjouteContrôleur.source = photoSource.getCellData(row).toString();
       
          
       VueNavigateur.loadVue(VueNavigateur.PHOTO_AJOUTE);
    }*/

  }
    
