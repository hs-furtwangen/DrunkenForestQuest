package scenceStuff;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage tile;
	public static BufferedImage player;
	public static BufferedImage monsterOne;
	public static BufferedImage resetButton;
	public static BufferedImage saveButton;
	public static BufferedImage hero;
	public static BufferedImage pizza;
	public static BufferedImage haselHoffFace;
	public static BufferedImage haselHoffMouth;
	public static BufferedImage haselHoffWarning;
	public static BufferedImage warning;
	public static BufferedImage layOutSkills;
	public static BufferedImage skillButton;
	public static BufferedImage recTangleProjectile;
	public static BufferedImage startButton;
	public static BufferedImage introScreen;
	public static BufferedImage pandaJew;
	public static BufferedImage pressButton;
	public static BufferedImage baum;
	
	
	public static void init() {
		try {
			tile = readImage("example_7.png");
			player = readImage("example_7.png");
			monsterOne = readImage("monster.png");
			resetButton = readImage("Reset.png");
			saveButton = readImage("Save.png");
			hero = readImage("magier.png");
			pizza = readImage("pizza.png");
			haselHoffFace = readImage("hasselHofOhneMund.png");
			haselHoffMouth = readImage("MundOfHasselhof.png");
			haselHoffWarning = readImage("HaselHoffWarning.png");
			warning = readImage("warning.png");
			layOutSkills = readImage("SkillAround.png");
			skillButton = readImage("skillButton.png");
			recTangleProjectile = readImage("recTangleSkills.png");
			startButton = readImage("start.png");
			introScreen = readImage("IntroScreen.png");
			pandaJew = readImage("panda_jewish.png");
			pressButton = readImage("PRESSBUTTON.png");
			baum = readImage("tanne_03.png");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage readImage(String res) throws IOException {
		return ImageIO.read(Frame.class.getResource("/" + res));
	}

}
