package MotsApp.ModèlesGestion;

import MotsApp.MainMotsApp;
import MotsApp.Modèles.AlertGestion;
import MotsApp.VueNavigateur;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Olena, Olga
 */
public class ioXmlGestion {
     
    /**
     * Opens a FileChooser to let the user select the data (file) to load.
     */
    // @FXML
    public static void fichierOuvrir() throws Exception {
        FileChooser fileChooser = new FileChooser();
        
        // Set extension filter; only xml can be read
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        // File file = fileChooser.showOpenDialog(null);
        File file;
        
        if (VueNavigateur.nonMatiereVueCheck()) file = fileChooser.showOpenDialog(null);
        else {
            AlertGestion.displayPreventionAlert(AlertGestion.preventionTitre, 
                                      AlertGestion.preventionCorps,
                                      AlertGestion.preventionDetails);
            file = fileChooser.showOpenDialog(null); // necessaire!
        }

        if (file != null) {
            if (VueNavigateur.articleVueCheck()){
                    MainMotsApp.chargerArticleDataDuFichier(file);//mainMotsApp, before it became static
                    VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU);//reload view 
                    //to see changes instantly
            }                
            else if (VueNavigateur.photoVueCheck()){
                    MainMotsApp.chargerPhotoDataDuFichier(file); // mainMotsApp, before it became static
                    VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);//reload view 
                    //to see changes instantly
            }                 
            else MainMotsApp.chargerArticleDataDuFichier(file);
        }
    }

    /**
    * Opens a FileChooser to let the user select a file to save to.
    */
    // @FXML
    public static void fichierSauvegarderComme(MainMotsApp mainMotsApp) throws Exception {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        // File file = fileChooser.showSaveDialog(null);
        File file;
        
        if (VueNavigateur.nonMatiereVueCheck()) file = fileChooser.showSaveDialog(null);
        else {
                AlertGestion.displayPreventionAlert(AlertGestion.preventionTitre, 
                                          AlertGestion.preventionCorps,
                                          AlertGestion.preventionDetails);
                file = fileChooser.showSaveDialog(null); // necessaire!
        }

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            if (VueNavigateur.articleVueCheck()) mainMotsApp.sauvegarderArticleDataToFile(file);
            else if (VueNavigateur.photoVueCheck()) mainMotsApp.sauvegarderPhotoDataToFile(file);
            else mainMotsApp.sauvegarderArticleDataToFile(file);
        } 
    }
}
