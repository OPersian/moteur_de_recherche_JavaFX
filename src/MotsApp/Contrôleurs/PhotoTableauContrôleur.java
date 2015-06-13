package MotsApp.Contrôleurs;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import MotsApp.Modèles.Photo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import MotsApp.MainMotsApp;
import MotsApp.ModèlesGestion.ioXmlGestion;
import MotsApp.VueNavigateur;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class PhotoTableauContrôleur implements Initializable {
    @FXML private TableView<Photo> photoTableView;
    //@FXML private TableView<Photo> photoTableView = new TableView<Photo>();
    private ObservableList<Photo> mabasePhoto;
    //private MainMotsApp mainMotsApp; 
    
    private final StringProperty description = new SimpleStringProperty(this, "description","");

    //@FXML private TableColumn<Photo, ImageView> photoImage;
    @FXML private TableColumn<Photo, String> photoTitre;
    @FXML private TableColumn<Photo, String> photoAuteur;
    @FXML private TableColumn<Photo, String> photoDescription;
    @FXML private TableColumn<Photo, LocalDate> photoDate;
    @FXML private TableColumn<Photo, URL> photoSource;
    @FXML private Button ajoute_btn;
    //private TableColumn<?, ?> photoImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               if (MainMotsApp.mabasePhoto_stockage != null){
            //TableColumn Definition
           /* TableColumn photoImage = new TableColumn("Photo");
                photoImage.setCellValueFactory(new PropertyValueFactory("photo"));
                   photoImage.setPrefWidth(200);  */
                   
            // Setting CellFactory pour TableColumn("Photo")                 
/*photoImage.setCellFactory(new Callback<TableColumn<Photo,ImageView>,TableCell<Photo,ImageView>>(){        
	@Override
	public TableCell<Photo, ImageView> call(TableColumn<Photo, ImageView> param) {                
		TableCell<Photo, ImageView> cell = new TableCell<Photo, ImageView>(){
			ImageView imageview = new ImageView();
                        
                    @Override
                    public void updateItem(ImageView item, boolean empty) {                        
				if(item!=null){                            
					HBox box= new HBox();
					box.setSpacing(10);
                        		//imageview.setFitHeight(50);
					//imageview.setFitWidth(50);
					imageview.setImage(item.getImage());

					box.getChildren().addAll(imageview); 
					//Setting Graphic composant pour Cell
					setGraphic(box);
				}
			}
                };
                
                   /* //Cell cliquable
                    cell.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2 && (! cell.isEmpty()) ) {
                            ImageView cellData = cell.getItem();
                            System.out.println(cellData);
                        }
                    });*/
                
		/*System.out.println(cell.getIndex());               
		return cell;
	}

});*/
                 
            photoTitre.setCellValueFactory(
                cellData -> cellData.getValue().titreProperty());
            
            photoAuteur.setCellValueFactory(
                cellData -> cellData.getValue().auteurProperty());

            photoDescription.setCellValueFactory(
                cellData -> cellData.getValue().descriptionProperty());
            photoDescription.setEditable(true);
            /* photoDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));*/

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
    
    MainMotsApp mainMotsApp = new MainMotsApp();
    
    @FXML
    private void lireDuFichier(ActionEvent event) throws Exception {      
        ioXmlGestion.fichierOuvrir(false); // save current photo list
        VueNavigateur.loadVue(VueNavigateur.PHOTO_TABLEAU);//reload view 
                                                           //to see changes instantly
    }
    //сделать булевые переменные: да/нет - ч/б, цветная
    //сделать вывод разрешения фото
    //ошибка индексации люсцен
}
    
