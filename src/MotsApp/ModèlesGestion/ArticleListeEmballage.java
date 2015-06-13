package MotsApp.ModèlesGestion;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import MotsApp.Modèles.Article;

/**
 * This is used for saving the
 * list of articles to XML.
 * @author Persianova, Golubnycha
 */

@XmlRootElement(name = "articles") // responsible for xml tag which wraps the list of articles
public class ArticleListeEmballage {

    private List<Article> articles;
    @XmlElement(name = "article") // responsible for xml tag which wraps every article
    
    public List<Article> getArticles() {
        return articles;
    }
    
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
