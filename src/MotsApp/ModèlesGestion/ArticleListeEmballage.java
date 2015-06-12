package MotsApp.ModèlesGestion;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import MotsApp.Modèles.Article;

/**
 * This is used for saving the
 * list of articles to XML.
 * 
 * @author Persianova, Golubnycha
 */

@XmlRootElement(name = "articles") // responsible for xml tag which wraps the list of articles
public class ArticleListeEmballage {

    private List<Article> articles;
    @XmlElement(name = "article") // responsible for xml tag which wraps every article
    //ObservableList<Article> doesn't work! 
    
    public List<Article> getArticles() {
        return articles;
    }
    
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
    // a - article; if true - Article; if false, we handle the type Photo
    /*public List<?> getMatières(Boolean a) { // List<? extends Matière>
        if (a) return articles;
        else return photos;
    }*/
    
    /*
    public List<? extends Matière> getMatières(ObservableList<? extends Matière> matières) { // List<? extends Matière>
        if (matières instanceof ObservableList<Article>) return articles;
        else return photos;
    }   
    
    // a - article; if true - Article; if false, we handle the type Photo
    public void setMatières(List<?> matières, Boolean a) { // List<? extends Matière>
        if (a) this.articles = (List<Article>) matières;
        else this.photos = (List<Photo>) matières;
    }
    */
}
