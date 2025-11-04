package hungergames;

import java.awt.image.BufferedImage;

public class Assets {

	private final static int width=360;
	private final static int height=360;
	public static BufferedImage player1,player2,sea,sand,stone,grass,animal,rope,apple,grape,pineapple,sword,pistol,bow;
	
	//Function that crops a sprite sheet and stores every cropped image at the proper BufferedImage object.
	//This way we only have to crop every image once and use it as many times as we want.
	public static void init() {
		BufferedImage sheet=ImageLoader.loadImage("/textures/newsheet.png");
		
		
		player1=sheet.getSubimage(0, 0, width, height);
		player2=sheet.getSubimage(width, 0, width, height);
		bow=sheet.getSubimage(2*width, 0, width, height);
		sword=sheet.getSubimage(3*width,0, width, height);
		pistol=sheet.getSubimage(0, height, width, height);
		apple=sheet.getSubimage(width, height, width, height);
		grape=sheet.getSubimage(2*width, height, width, height);
		pineapple=sheet.getSubimage(3*width,height, width, height);
		animal=sheet.getSubimage(0, 2*height, width, height);
		rope=sheet.getSubimage(width, 2*height, width, height);
		grass=sheet.getSubimage(2*width, 2*height, width, height);
		sand=sheet.getSubimage(3*width,2*height, width, height);
		sea=sheet.getSubimage(0, 3*height, width, height);
		stone=sheet.getSubimage(width, 3*height, width, height);
		


	}
	
	
}
