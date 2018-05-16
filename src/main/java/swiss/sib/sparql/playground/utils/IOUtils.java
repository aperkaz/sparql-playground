package swiss.sib.sparql.playground.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import swiss.sib.sparql.playground.exception.SparqlTutorialException;

/**
 * IO utitly class
 * @author Daniel Teixeira http://github.com/ddtxra
 *
 */
public class IOUtils {

	public static String readFile(String path, String orValue) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			return new String(encoded, Charset.forName("UTF-8"));
		} catch (IOException e) {
			if(orValue != null){
				return orValue;
			}else {
				e.printStackTrace();
				throw new SparqlTutorialException(e);
			}
		}
	}

	public static byte[] readImage(String extension, File f) {

		try {

			// Retrieve image from the classpath.
			InputStream is = new FileInputStream(f);

			// Prepare buffered image.
			BufferedImage img = ImageIO.read(is);

			// Create a byte array output stream.
			ByteArrayOutputStream bao = new ByteArrayOutputStream();

			// Write to output stream
			ImageIO.write(img, extension, bao);

			return bao.toByteArray();
		} catch (IOException e) {
			throw new SparqlTutorialException(e);
		}
	}
	

	public static void streamFile(File file, OutputStream outStream) {

		try {

			FileInputStream inputStream = new FileInputStream(file);

	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
		} catch (IOException e) {
			throw new SparqlTutorialException(e);
		}
	}
	
	

}