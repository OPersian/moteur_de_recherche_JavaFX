package trial.Contrôleurs;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import trial.Modèles.Photo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import trial.MainMotsApp;
import trial.VueNavigateur;

/**
 * FXML Controller class
 *
 * @author Persianova, Golubnycha
 */
public class PhotoTableauController implements Initializable {
    @FXML private TableView<Photo> photoTableView;
    //@FXML private TableView<Photo> photoTableView = new TableView<Photo>();
    private ObservableList<Photo> mabasePhoto;
    //private MainMotsApp mainMotsApp; 

    @FXML private TableColumn<Photo, ImageView> photoImage;
    @FXML private TableColumn<Photo, String> photoTitre;
    @FXML private TableColumn<Photo, String> photoAuteur;
    @FXML private TableColumn<Photo, String> photoDescription;
    @FXML private TableColumn<Photo, LocalDate> photoDate;
    @FXML private TableColumn<Photo, URL> photoSource;
    @FXML private Button ajoute_btn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               if (MainMotsApp.mabasePhoto_stockage != null){
            //TableColumn Definition
            TableColumn photoImage = new TableColumn("Photo");
                photoImage.setCellValueFactory(new PropertyValueFactory("photo"));
                   photoImage.setPrefWidth(200);  
                   
            // Setting CellFactory pour TableColumn("Photo")                 
photoImage.setCellFactory(new Callback<TableColumn<Photo,ImageView>,TableCell<Photo,ImageView>>(){        
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
                
		System.out.println(cell.getIndex());               
		return cell;
	}

});
                 
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
     VueNavigateur.loadVista(VueNavigateur.PHOTO_AJOUTE); 
    }
}
    
