package trial.ModèlesGestion;

import java.io.File;
import static java.io.FileDescriptor.in;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import static java.lang.System.in;
import java.nio.file.Paths;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import static javax.management.Query.in;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import trial.MainMotsApp;
import static trial.MainMotsApp.filePath_lucene;
import static trial.MainMotsApp.mabaseArticle_stockage_lucene;
import trial.Modèles.Article;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import trial.Modèles.BaseDesArticles;

/**
 *
 * @author Persianova, Golubnycha
 */
public class LuceneMoteur {
    
    public static String FILE_PATH;
    public static Document document; //pour paurcorir resultats de recherche
    public static String titreShow; //to debug in console; see in the code below
    
    //to add search results to a list
     public static BaseDesArticles baseArticleResultats = new BaseDesArticles();
    
    public static void créerIndex() throws IOException{
        try {
            StopAnalyzer analyzer = new StopAnalyzer();
            Directory dir = FSDirectory.open(Paths.get("."));
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(OpenMode.CREATE);
            IndexWriter writer = new IndexWriter(dir, iwc);
            
            //Document document = new Document();
            for (Article i : MainMotsApp.mabaseArticle_stockage) {
                //Document document = new Document();
                document = new Document();
                FieldType myFieldType = new FieldType();             
                myFieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
                myFieldType.setStored(true); // on stocke le texte
                myFieldType.setTokenized(true);
                myFieldType.freeze();
                Field myField1 = new Field("titre",i.getTitre(),myFieldType); 
                document.add(myField1);
                // etc. pour tous les champs souhaités                        
                Field myField2 = new Field("auteur",i.getAuteur(),myFieldType); 
                document.add(myField2);                        
                Field myField3 = new Field("auteur",i.getAuteur(),myFieldType); 
                document.add(myField3); 
                Field myField4 = new Field("contenu",i.getContenu(),myFieldType); 
                document.add(myField4);                         
                Field myField5 = new Field("date",i.getDate().toString(),myFieldType); 
                document.add(myField5); 
                Field myField6 = new Field("source",i.getSource().toString(),myFieldType); 
                document.add(myField6);                         
                writer.addDocument(document);
                            
                System.out.println(document + "\n");//to debug in console
                            
                writer.commit(); //on finalize
                //writer.close(); //avoided: org.apache.lucene.store.AlreadyClosedException: this IndexWriter is closed
            }     
            
            writer.close(); //on ferme
                    
            System.out.println("Indexing has been successfully produced. \n");
        }
        
        catch (Exception e) {
            System.out.println("Can not produce indexing with lucene \n" + e.toString());
        }
        
    } 
  
    public static void chercherDansIndex(String requête) 
                                        throws ParseException, IOException{
        try {
            // ouvrir l’index
            Directory fsDir = FSDirectory.open(Paths.get("."));

            DirectoryReader reader = DirectoryReader.open(fsDir);
            IndexSearcher searcher = new IndexSearcher(reader);
            
            // exécuter une requête
            Analyzer analyzer = new StopAnalyzer();
            
            // build a Query object
            QueryParser parser = new QueryParser("titre",analyzer);
            Query q = parser.parse(requête);
            
            // search for the query
            int maxHits = 10; //number of results to show (Lucene documentation)
            TopDocs docs = searcher.search(q, maxHits); // changed from 1 to 10
            
            //parcourir les résultats
            ScoreDoc[] hits = docs.scoreDocs;//!!!
            
            // retrieve each matching document from the ScoreDoc array
            /*for (int i = 1; i <= hits.length; i++){
                //Document d = instance.getDocument(hits[i].doc); //instance of the Field class
                Document d = null;
                int docId = hits[i].doc;
                d = searcher.doc(docId);
                float score = hits[i].score;
                String titre = document.get("titre");
                System.out.println("The next document found: " + d); //to debug with console
                titreShow = titre;//to see only the last one in "console debug"
                System.out.println(titre + " --- ");
                //System.out.println((i + 1) + ". " + d.get("path") + " score=" + score);
                System.out.println((i + 1) + ". titre: " + titre + " score=" + score); 
                //make it performed in TableView???
            }*/
            
            // retrieve each matching document from the ScoreDoc array
            //http://stackoverflow.com/questions/14966208/hits-object-deprecated-in-lucene-net-3-03-how-do-i-replace-it
            for (ScoreDoc i : hits) {
                // retrieve the document from the 'ScoreDoc' object
               Document doc = searcher.doc(i.doc);
               String titre = doc.get("titre");
               
               Article article = new Article();
               article.setTitre(titre);
               //set other fields!!!
               
               baseArticleResultats.ajouterArticle(article); //to perform in TableView
                       
               /*System.out.println(titre);
               titreShow = titre;
               document = doc;*/
            }
            
            //to debug in console
            /*
            System.out.println("We have searched in the next documents: " + document);
            System.out.println("The user has entered the next request:               " + requête);
            System.out.println("Research is successfully processed; article found:   " + titreShow);//OK if only one found
            */
  
        }
        catch (Exception e) {
            System.out.println("Error searching " + requête + ":\n " + e.getMessage());
        }
        
    }
    
}
