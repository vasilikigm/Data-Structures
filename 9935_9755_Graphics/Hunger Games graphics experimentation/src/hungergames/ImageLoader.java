package hungergames;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	//Function that loads images
	//It returns the BufferedImage object of our loaded image
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//If the we are not able to load the image ,we don't want to run our program so:
			System.exit(1);
		}
		return null;
		
	}

}
