package MotsApp.ModèlesGestion;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/**
 *
 * @author Olena, Olga
 */
public class AlertGestion {
    
    /*l’utilisateur obtienne le message prévenante que l’application travaillera avec articles par defaut*/
    public static String preventionTitre = "MotsApp! vous previent!";
    public static String preventionCorps = "MotsApp! travaillera avec vos articles."
                        + "\nSi vous desirez de travailler avec photos, venez"
                        + "\na la page d'ajoute de photo ou "
                        + "\na la page de photoalbum, svp!";
    public static String preventionDetails = "Message prévenante. Juste appuiez sur 'OK'!";
    
    
    /*--------------information sur les auteurs de l'application--------------*/
    public static String infoTitre = "Infornations sur MotsApp!";
    public static String infoCorps = "About: MotsApp! is a little application "
                + "\nwhich enables the user to add "
                + "\narticles and photos, to navigate "
                + "\nand modify them, and to search "
                + "\nthrough.";
    public static String infoAuteurs ="Authors: Olena Persianova, Olga Golybnucha";
    
    
    public static void displayPreventionAlert(String titre, String corps, String details) {
        Stage window = new Stage();
        window.setTitle(titre);
        window.setMinWidth(350);
        
        Label labelCorps = new Label();
        labelCorps.setText(corps);
        Label labelDetails = new Label();
        labelDetails.setText(details);

        Button agreeButton = new Button("OK");
        agreeButton.setOnAction(e -> window.close());  
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(labelCorps, labelDetails, agreeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public static void displayInfoAlert(String titre, String corps, String details){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(corps);
        alert.setContentText(details);
        alert.showAndWait();
    }

    
}
