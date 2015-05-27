package trial.ModèlesGestion;

import static com.oracle.nio.BufferSecrets.instance;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import static jdk.nashorn.internal.objects.Global.instance;
import org.apache.lucene.analysis.core.StopAnalyzer;
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

/**
 *
 * @author Persianova, Golubnycha
 */
public class LuceneMoteur {
    
    public MainMotsApp mainMotsApp = new MainMotsApp();
    public ObservableList<Article> mabaseArticle_stockage_new; //to lucene index
    public static String FILE_PATH;
    public static void créerIndex() throws IOException{
       
        //Analyzer analyzer = new StandardAnalyzer(); //doesn't work
        //StandardAnalyzer analyzer = new StandardAnalyzer();
        
        try {
            StopAnalyzer analyzer = new StopAnalyzer();

            Directory dir = FSDirectory.open(Paths.get("."));
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(OpenMode.CREATE);
            IndexWriter writer = new IndexWriter(dir, iwc);
            
            FileChooser fileChooser = new FileChooser();

            // Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show save file dialog
            File file = fileChooser.showOpenDialog(null);

            // Save the file path to the registry.
            MainMotsApp.setMatièreFichierChemin(file);
            FILE_PATH = MainMotsApp.filePath_lucene;
            MainMotsApp.chargerArticleDataDuFichier(file);//we have a global variable --- mabaseArticle_stockage_lucene
            
            Document document = new Document();
                    //File[] files = file.listFiles();
                    for (Article i : MainMotsApp.mabaseArticle_stockage) {
                            //Document document = new Document();
                            //i = new Article();

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


                            /*String path = i.getCanonicalPath();
                            document.add(new Field(FIELD_PATH, path, Field.Store.YES));

                            Reader reader = new FileReader(file);
                            document.add(new Field(FIELD_CONTENTS, reader));*/

                            writer.addDocument(document);
                    }
                    writer.commit(); //on finalize
                    writer.close(); //on ferme
                    
                    System.out.println("Indexing works \n" + document);

        }
        catch (Exception e) {
            System.out.println("Can not produce indexing with lucene \n" + e.toString());
        }
        
    }
    
    public static void chercherDansIndex(String requête) throws ParseException, IOException{
        // ouvrir l’index
        /*Directory fsDir = FSDirectory.open(Paths.get("."));
        DirectoryReader reader = DirectoryReader.open(fsDir);*/
        
        try {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(FILE_PATH)));
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(2);

            // exécuter une requête
            StopAnalyzer analyzer = new StopAnalyzer();
            //int maxHits = 1; //OPersian
            QueryParser parser = new QueryParser("titre", analyzer);
            Query q = parser.parse(requête);
            searcher.search(q, collector);

            /*TopDocs topDocs = searcher.search(q, maxHits);        
            // obtain the ScoreDoc (= documentID, relevanceScore) array from topDocs
            ScoreDoc[] hits = topDocs.scoreDocs;*/
            ScoreDoc[] hits = collector.topDocs().scoreDocs;

            // retrieve each matching document from the ScoreDoc array; display results
            for (int i = 0; i < hits.length; i++) {
                /*Document doc = instance.getDocument(hits[i].doc); //instance of the Field class
                String titre = doc.get("titre");*/
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                System.out.println((i + 1) + ". " + d.get("path") + " score=" + hits[i].score);
                //make it performed in TableView!!!
            }
        }
        catch (Exception e) {
            System.out.println("Error searching " + requête + " :\n " + e.getMessage());
        }
        
    }
    
}
