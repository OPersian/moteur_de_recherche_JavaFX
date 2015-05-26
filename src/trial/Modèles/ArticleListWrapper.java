package trial.Modèles;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Helper class to wrap a list of articles. This is used for saving the
 * list of articles to XML.
 * 
 * @author Persianova, Golubnycha
 */

@XmlRootElement(name = "articles")
public class ArticleListWrapper {
    
    //ObservableList<Article> doesnt work!
    private List<Article> articles;
    @XmlElement(name = "article")
    
    public List<Article> getArticles() {
        return articles;
    }
    
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
    
    
}
