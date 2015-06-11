package MotsApp.Modèles;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Persianova, Golubnycha
 */
public class BaseDesArticles {
    
    public ObservableList<Article> mabase; //private
    //private ObservableList<Article> mabase2 = FXCollections.observableArrayList(); 
    
    public BaseDesArticles()
    {
        mabase = FXCollections.observableArrayList();
    }
    
    public void ajouterArticle(Article n)
    {
        mabase.add(n);
    }
    
    public ObservableList<Article> getArticleData() {
    //public BaseDeMatières getMatièreData() {
    
        return mabase;
    }    
    
    //to debug in console
    public void articleAfficher(){
        
        Iterator it = mabase.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }
    
    
}
