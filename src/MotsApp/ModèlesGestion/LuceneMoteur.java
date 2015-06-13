package MotsApp.ModèlesGestion;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import MotsApp.MainMotsApp;
import MotsApp.Modèles.Article;
import MotsApp.Modèles.BaseDesArticles;
import MotsApp.Modèles.BaseDesPhotos;
import MotsApp.Modèles.Matière;
import MotsApp.Modèles.Photo;
import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Persianova, Golubnycha
 */
public class LuceneMoteur {
    
    public static Document document; //pour paurcorir resultats de recherche
    //public static String titreShow; //to debug in console; see in the code below
    
    //to add search results to a list
    public static BaseDesArticles baseArticleResultats = new BaseDesArticles();
    public static BaseDesPhotos basePhotoResultats = new BaseDesPhotos();

    
    public static void créerArticleIndex() throws IOException{
        try {
            StopAnalyzer analyzer = new StopAnalyzer();
            //StopAnalyzer removes common English words that are not usually useful for indexing.
            
            Directory dir = FSDirectory.open(Paths.get("."));
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer); // java.lang.IllegalStateException: do not share IndexWriterConfig instances across IndexWriters
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
                Field myFieldTitre = new Field("titre",i.getTitre(),myFieldType); 
                document.add(myFieldTitre);
                // etc. pour tous les champs souhaités                        
                Field myFieldAuteur = new Field("auteur",i.getAuteur(),myFieldType); 
                document.add(myFieldAuteur);                        
                /*Field myField3 = new Field("auteur",i.getAuteur(),myFieldType); 
                document.add(myField3); */
                Field myFieldContenu = new Field("contenu",i.getContenu(),myFieldType); 
                document.add(myFieldContenu);                         
                Field myFieldDate = new Field("date",i.getDate().toString(),myFieldType); 
                document.add(myFieldDate); 
                Field myFieldSource = new Field("source",i.getSource().toString(),myFieldType); 
                document.add(myFieldSource);                         
                writer.addDocument(document);
                            
                System.out.println(document + "\n");//to debug in console
                            
                writer.commit(); //on finalize
                //writer.close(); //avoided: org.apache.lucene.store.AlreadyClosedException: this IndexWriter is closed
            } 
            
             //Document document = new Document();
            for (Photo i : MainMotsApp.mabasePhoto_stockage) {
                try {
                    //Document document = new Document();
                    document = new Document();
                }
                catch (Exception e){
                    System.out.println("can't create new document (for photos)\n" + e.toString());
                }
                
                FieldType myFieldType = new FieldType();             
                myFieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
                myFieldType.setStored(true); // on stocke le texte
                myFieldType.setTokenized(true);
                myFieldType.freeze();
                try {
                    Field myFieldTitre = new Field("titre",i.getTitre(),myFieldType); 
                    document.add(myFieldTitre);
                    // etc. pour tous les champs souhaités                        
                    Field myFieldAuteur = new Field("auteur",i.getAuteur(),myFieldType); 
                    document.add(myFieldAuteur);                        
                    /*Field myField3 = new Field("auteur",i.getAuteur(),myFieldType); 
                    document.add(myField3); */
                    Field myFieldContenu = new Field("contenu",i.getContenu(),myFieldType); 
                    document.add(myFieldContenu);                         
                    Field myFieldDate = new Field("date",i.getDate().toString(),myFieldType); 
                    document.add(myFieldDate); 
                    Field myFieldSource = new Field("source",i.getSource().toString(),myFieldType); 
                    document.add(myFieldSource);     
                }
                catch (Exception e){
                    System.out.println("can't add photo's fields to document\n" + e.toString());
                }
                try {
                    writer.addDocument(document);
                }
                catch (Exception e){
                    System.out.println("can't add document with photo to writer\n" + e.toString());
                }               
                            
                System.out.println(document + "\n");//to debug in console
                
                try {            
                    writer.commit(); //on finalize
                    //writer.close(); //avoided: org.apache.lucene.store.AlreadyClosedException: this IndexWriter is closed
                }
                catch (Exception e){
                    System.out.println("can't commit the writer with photos\n" + e.toString());
                }                     
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
            
            // construire l'objet Query
            QueryParser parser = new QueryParser("titre", analyzer);
            Query q = parser.parse(requête);
            
            // rechercher pour query
            int maxHits = 10; //number of results to show (Lucene documentation)
            TopDocs docs = searcher.search(q, maxHits); // changed from 1 to 10
            
            //parcourir les résultats
            ScoreDoc[] hits = docs.scoreDocs;
            
            try{
            // retrieve each matching document from the ScoreDoc array
            //http://stackoverflow.com/questions/14966208/hits-object-deprecated-in-lucene-net-3-03-how-do-i-replace-it
            for (ScoreDoc i : hits) {
               // retrieve the document from the 'ScoreDoc' object
                Document doc = searcher.doc(i.doc);
                String titre = doc.get("titre");
                String auteur = doc.get("auteur");
                String contenu = doc.get("contenu"); // for article
                String description = doc.get("contenu"); // for photo
                String date = doc.get("date");              
                URL source = new URL(doc.get("source"));
                Article article = new Article();
                Photo photo = new Photo();
                try {
                    article.setTitre(titre);
                    article.setAuteur(auteur);
                    article.setContenu(contenu);
                    article.setDate(LocalDate.parse(date));
                    article.setSource(source);
                    baseArticleResultats.ajouterArticle(article); //to perform in TableView
                }
                catch (Exception e) {
                    System.out.println("Lucene can not get search reasults (if any) in articles \n" + e.toString());
                    continue; // if can't set hit's attributes, then it's a Photo type, not Article
                }
                // if item is not of class type Article, it is of Photo
                if (photo.getTitre() == null) {
                    try {
                        photo.setTitre(titre);
                        photo.setAuteur(auteur);
                        photo.setContenu(description);
                        photo.setDate(LocalDate.parse(date));
                        photo.setSource(source);
                        basePhotoResultats.ajouterPhoto(photo); //to perform in TableView 
                    }
                     catch (Exception e) {
                        System.out.println("Lucene can not get search reasults (if any) in photos \n" + e.toString());
                        continue;
                    }
                }
                
                /*for (int j = 1; j <= hits.length; j++){
                //Document d = instance.getDocument(hits[i].doc); //instance of the Field class
                Document d = null;
                int docId = hits[j].doc;
                d = searcher.doc(docId);
                float score = hits[j].score;
                System.out.println("the score is: " + score);
                }*/
            }
            } //try
            catch(Exception e) {
                System.out.println("Error fetching search results \n" + e.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println("Error searching " + requête + ":\n " + e.getMessage());
        }
        
    }
    
}
