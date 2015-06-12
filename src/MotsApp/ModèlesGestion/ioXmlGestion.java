package MotsApp.ModèlesGestion;

import MotsApp.MainMotsApp;
import static MotsApp.MainMotsApp.filePath_lucene;
import static MotsApp.MainMotsApp.mabaseArticle_stockage;
import static MotsApp.MainMotsApp.mabaseArticle_stockage_lucene;
import static MotsApp.MainMotsApp.mabasePhoto_stockage;
import MotsApp.Modèles.AlertGestion;
import MotsApp.VueNavigateur;
import java.io.File;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Olena, Olga
 */
public class ioXmlGestion {
    /**
 * Loads article data from the specified file. The current article data will
 * be safe.
 * 
 * @param file
     * @return 
     * @throws java.lang.Exception
 */
public static void chargerArticleDataDuFichier(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(ArticleListeEmballage.class);
        Unmarshaller um = context.createUnmarshaller();
        
        // Reading XML from the file and unmarshalling.
        ArticleListeEmballage wrapper = (ArticleListeEmballage) um.unmarshal(file);
        
        // uncomment to delete current articles (that was added within a session)
        // mabaseArticle_stockage.clear(); // attention: not mabaseArticle!
        
        // Adding wrapped article data to the current observableList of articles.
        mabaseArticle_stockage.addAll(wrapper.getArticles());
        
        filePath_lucene = file.getPath(); //for lucene index
        mabaseArticle_stockage_lucene = mabaseArticle_stockage; //for lucene index
        
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not load data from file:\n" + 
                file.getPath() + "\n" + e.toString());
    }
}

/**
 * Saves the article data list to the specified file.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public static void sauvegarderArticleDataToFile(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(ArticleListeEmballage.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping the article data.
        ArticleListeEmballage wrapper = new ArticleListeEmballage();
        wrapper.setArticles(mabaseArticle_stockage);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);
        
        filePath_lucene = file.getPath(); //to lucene index
        
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not save data to file:\n" + file.getPath() +
                            "\n" + e.toString());
    } 
}

/**
 * Loads photo data from the specified file. The current photo data will
 * be replaced.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public static void chargerPhotoDataDuFichier(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PhotoListeEmballage.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PhotoListeEmballage wrapper = (PhotoListeEmballage) um.unmarshal(file);

        mabasePhoto_stockage.clear();//not mabase!
        mabasePhoto_stockage.addAll(wrapper.getPhotos());

        // Save the file path to the registry.
        // setMatièreFichierChemin(file);
        filePath_lucene = file.getPath(); //to lucene index

    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not load data from file:\n" + 
                file.getPath() + "\n" + e.toString());
    }
}

/**
 * Saves the photo data list to the specified file.
 * 
 * @param file
     * @throws java.lang.Exception
 */
public static void sauvegarderPhotoDataToFile(File file) throws Exception {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PhotoListeEmballage.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our article data.
        PhotoListeEmballage wrapper = new PhotoListeEmballage();
        wrapper.setPhotos(mabasePhoto_stockage);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        // setMatièreFichierChemin(file);
        filePath_lucene = file.getPath(); //to lucene index
        
    } catch (Exception e) { // catches ANY exception
        System.out.println("Could not save data to file:\n" + file.getPath() +
                            "\n" + e.toString());
    } 
}
    
     
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
                    chargerArticleDataDuFichier(file);//mainMotsApp, before it became static
                    VueNavigateur.loadVue(VueNavigateur.ARTICLE_TABLEAU);//reload view 
                    //to see changes instantly
            }                
            else if (VueNavigateur.photoVueCheck()){
                    chargerPhotoDataDuFichier(file); // mainMotsApp, before it became static
                    VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);//reload view 
                    //to see changes instantly
            }                 
            else chargerArticleDataDuFichier(file);
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
            if (VueNavigateur.articleVueCheck()) sauvegarderArticleDataToFile(file);
            else if (VueNavigateur.photoVueCheck()) sauvegarderPhotoDataToFile(file);
            else sauvegarderArticleDataToFile(file);
        } 
    }
}
