package trial.classesGestionContenu;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Persianova, Golubnycha
 */

//class has been created to debug in console regime
public class BaseDeMatières {
    
    //private TreeSet<Matière> mabase;
    
    public ObservableList<Matière> mabase; //private
    //private ObservableList<Article> mabase2 = FXCollections.observableArrayList(); 
    
    public BaseDeMatières()
    {
        //mabase = new TreeSet<Matière>();
        mabase = FXCollections.observableArrayList();
    }
    
    public void ajouterMatière(Matière n)
    {
        mabase.add(n);
    }
    
    public ObservableList<Matière> getMatièreData() {
    //public BaseDeMatières getMatièreData() {
    
        return mabase;
    }    
    
    //to debug in console
    public void baseAfficher(){
        
        Iterator it = mabase.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }
    
}
