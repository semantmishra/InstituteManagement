package utilis;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class Camera {
	public String capture(){
		 Webcam webcam = Webcam.getDefault();

	        webcam.setViewSize(new Dimension(640,480));

	        webcam.open();

	        // get image
	        BufferedImage image = webcam.getImage();

	        // save image to PNG file
	        String fileName = System.nanoTime()+".jpg";
	        try {
				ImageIO.write(image, "JPG", new File(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        webcam.close();
		
		return fileName;
	}

}
