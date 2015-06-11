package trial.ModèlesGestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import trial.Modèles.Photo;

/**
 *
 * @author Persianova, Golubnycha
 */
public class NewClass {
    /*
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        List<Photo> l = new ArrayList<>();
        l.add(new Photo(null, "sdf", "dsf", "sdf", LocalDate.now(), new URL("http://sfdf")));

        FileInputStream r;
        try {
            FileOutputStream fileOut
                    = new FileOutputStream("file.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(l);
            out.close();
            fileOut.close();

            FileInputStream fileIn = new FileInputStream("file.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Photo> l1 = (List<Photo>) in.readObject();
            in.close();
            fileIn.close();
            assert l1.equals(l);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}
