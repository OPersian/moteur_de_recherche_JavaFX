/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial.Modèles;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


/**
 *
 * @author Ольга
 */
public class ImageToByte {

   /* public ImageToByte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    	/*public void ConvertImageToByte () throws IOException{
		String dirName="C:\\";
		ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
		BufferedImage img=ImageIO.read(new File(dirName,"rose.jpg"));
		ImageIO.write(img, "jpg", baos);
		baos.flush();
 
		String base64String = Base64.encode(baos.toByteArray());
		baos.close();
 	}*/
        
public void ConvertImageToByte (ImageView image) throws FileNotFoundException, IOException {
    	/*
    	 * 1. How to convert an image file to  byte array?
    	 */
 
        File file = new File("C:\\rose.jpg");
 
        FileInputStream fis = new FileInputStream(file);
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
 
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum); 
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            Logger.getLogger(Photo.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        byte[] bytes = bos.toByteArray();
}
        
    
   /* public void ConvertBytoImage () throws IOException, Base64DecodingException{ 
        String base64String = null;
 	byte[] bytearray = Base64.decode(base64String);
        String dirName = null;
	BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
	ImageIO.write(imag, "jpg", new File(dirName,"snap.jpg"));
       
    }*/
    
 public void ConvertBytoImage () throws IOException{
     /*
         * 2. How to convert byte array back to an image file?
         */
        byte[] bytes = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
 
        //ImageIO is a class containing static methods for locating ImageReaders
        //and ImageWriters, and performing simple encoding and decoding. 
 
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; 
        ImageInputStream iis = ImageIO.createImageInputStream(source); 
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
 
        BufferedImage image = reader.read(0, param);
        //got an image file
 
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        //bufferedImage is the RenderedImage to be written
 
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
 
        File imageFile = new File("C:\\newrose2.jpg");
        ImageIO.write(bufferedImage, "jpg", imageFile);
 
        System.out.println(imageFile.getPath());
    }
 }
    

