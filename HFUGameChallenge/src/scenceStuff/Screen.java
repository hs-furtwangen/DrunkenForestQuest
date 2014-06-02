package scenceStuff;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import map.TileMap;
import entitis.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Screen extends JPanel implements Runnable {

	public Thread thread = new Thread(this);

	long frameNanoseconds = 1000 * 1000 * 1000 / 45;

	long startTime = System.nanoTime();

	public static Image[] tileset = new Image[100];

	public static Point mse = new Point(0, 0);

	public static List<Projectile> projectiles;
	
	public static List<Point> customisationProjectiles;
	public static List<Monster> monsterList;
	
	
	public static int myWidth, myHeight;
	public static TileMap map;
	public static Save save;
	public static boolean isFirst = true;
	private int pandaX = 100;
	private int pandaY = 50;
	private int pandaWidth =100;
	private int pandaHeight = 100;
	private int pandaDirection =1;
	
	public static Player player;
	public static int playerDirection;
	public static SkillMenuBar skillMenu;
	public static boolean isShowingSkillMenu = false;
	public static UpgradeProjectileScence upgradeProjectileScence;
	public static Color currentColor = Color.white;
	public static boolean hasStarted = false;
	public static SkillMenuBar startButton;
	
	public static Rectangle expRectangle;
	private int spawnTime=100;
	private int currentSpawn =0;
	public static boolean spawnShoot=false;
	public static Logic logic;
	public static boolean isLost= false;
	public static boolean beatHasselhof = false;
	public static boolean hasselHofAppears = false;
	public static boolean hasselHofsMoves = false;
	
	public static boolean isIntroOver = false;
	
	public static Hasselhoff hasselHoff;
	
	public static AudioInputStream audioInputStream;
	BigClip clip = new BigClip();
	
	public Screen(Frame frame) {

		frame.addMouseListener(new KeyHandel());
		frame.addMouseMotionListener(new KeyHandel());
		frame.addKeyListener(new KeyHandel());
		thread.start();
		
		
	}

	private void define() throws FileNotFoundException {

		map = new TileMap();

		ImageLoader.init();

		for (int i = 0; i < tileset.length; i++) {
			tileset[i] = ImageLoader.tile;
			tileset[i] = createImage(new FilteredImageSource(
					tileset[i].getSource(), new CropImageFilter(0, 30 * i, 30,
							30)));
		}
		
		player = new Player(map.tileMap[11][6].x ,map.tileMap[11][6].y, ImageLoader.hero);
		projectiles = new ArrayList<Projectile>();
		customisationProjectiles = new ArrayList<Point>();
		customisationProjectiles.add(new Point(0,0));
		customisationProjectiles.add(new Point(0,5));
		customisationProjectiles.add(new Point(1,5));
		customisationProjectiles.add(new Point(1,0));

		//customisationProjectiles.add(new doublePoint(0,0,100,25));
		monsterList = new ArrayList<Monster>();
		logic = new Logic();
		skillMenu = new SkillMenuBar(ImageLoader.skillButton, 610, 500);
		startButton = new SkillMenuBar(ImageLoader.startButton, 610, 400);
		upgradeProjectileScence = new UpgradeProjectileScence();
		//Player.pizzaCount = 5;
		hasselHoff = new Hasselhoff();
		try {
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/"+"Musik_1_edit.wav"));
			doMusic();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	private void doMusic()
	{
		     try {
				//clip = AudioSystem.getClip();
				clip.open(audioInputStream);
			    clip.start( );
			    clip.loop(9999);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		     System.out.println("DOES MUSIK");
		  
	}
	
	private void checkLose()
	{
		if(Player.life<=0)
		{
			isLost = true;
		}
	}
	
	private void spawnShoots()
	{
		spawnShoot = false;
		if(Player.pizzaCount==0){
		Screen.projectiles.add(new Projectile());
		}
		else{
		for(int i=1; i<Player.pizzaCount+2; i++)
		{
			Screen.projectiles.add(new Projectile( (int) ((int) (i*15)*Math.pow(-1,i))));
		}
		}
	}
	
	@Override
	public void run() {
		int second = 0;
		while (true) {
			long timeTaken = System.nanoTime() - startTime;
			if (timeTaken < frameNanoseconds) {
				long sleepMillis = ((frameNanoseconds - timeTaken) / (1000 * 1000));
				second++;
				repaint();
				if (!isFirst && !isShowingSkillMenu && !isLost && !beatHasselhof) {
					
					Projectile.moveAndCheckToKillProjectile();
					Monster.moveAndCheckToKillMonster();
					this.playerMove();
					Monster.checkMonsterCollision();
					checkLose();
					//doMusic();

					if(spawnShoot)
						spawnShoots();
					
					if(hasStarted)
					{
						Player.checkLevelUp();
						currentSpawn++;
						if(currentSpawn > spawnTime)
						{
							logic.spawnMonster();
							currentSpawn =0;
						}
					}
					

					if(hasselHofsMoves)
					{
						hasselHoff.moveTheHasselHoff();
						hasselHoff.checkHasselHofCollision();
					}
				}

				if (second % 50 == 0) {
					if (second == 100) {
						second = 0;

					}
				}
				try {
						Thread.sleep(sleepMillis);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}

				startTime += frameNanoseconds;
		}
	}
	

	private void playerMove()
	{
		if(Screen.playerDirection != 0)
		player.move(playerDirection);
		
	}
	
	private void movePanda()
	{
		if(pandaX < 600 && pandaDirection ==1)
		{
			pandaX++;
		}else
		if(pandaX >= 60)
		{
			pandaDirection =2;
			pandaX--;
			pandaHeight++;
		}
		
		if(pandaY<500 && pandaDirection ==1)
		{
			pandaY++;
		}else
		if(pandaY >= 50)
		{
			pandaY--;
			pandaWidth++;
		}

		if(pandaX <=100 && pandaY <=50)
		{
			pandaDirection =1;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			try {
				define();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isFirst = false;
			System.out.println("paint");

		}
		if(!isIntroOver){
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(ImageLoader.introScreen, 100, 50, 500, 500, null);
			g.drawImage(ImageLoader.pandaJew, pandaX, pandaY, pandaWidth, pandaHeight,null);
			movePanda();
			if(pandaWidth>500)
			{
				g.drawImage(ImageLoader.pressButton, myWidth/2 - ImageLoader.pressButton.getWidth()/2, myHeight/2,ImageLoader.pressButton.getWidth()+30, ImageLoader.pressButton.getHeight(), null );
			}
		}
		else {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0, 0, 0));
		g.drawLine(map.tileMap[0][0].x - 1, 0, map.tileMap[0][0].x - 1,
				map.tileMap[map.worldHeight - 1][0].y + map.blockSize + 1); // links
		g.drawLine(map.tileMap[0][map.worldWidth - 1].x + map.blockSize, 0,
				map.tileMap[0][map.worldWidth - 1].x + map.blockSize,
				map.tileMap[map.worldHeight - 1][0].y + map.blockSize + 1);// rechts
		g.drawLine(map.tileMap[0][0].x, map.tileMap[map.worldHeight - 1][0].y
				+ map.blockSize, map.tileMap[0][map.worldWidth - 1].x
				+ map.blockSize, map.tileMap[map.worldHeight - 1][0].y
				+ map.blockSize);// bottom
		// g.clearRect(0, 0, getWidth(), getHeight());
		if(isShowingSkillMenu)
		{
			upgradeProjectileScence.draw(g);
		}
 else {
	 for(int i =0; i<25; i++){
		 for(int j =0; j <4; j++){
	 		g.drawImage(ImageLoader.baum, 20 + 20*j, 0+i*20, ImageLoader.baum.getWidth(), ImageLoader.baum.getHeight(), null);
		 }
	 }
	 		map.draw(g);
			player.draw(g);
			startButton.draw(g);
			g.drawRect(110, 550, 480, 30);
			g.setColor(new Color(255, 0, 255, 120));
			int expCurrentProzent = ((1+Player.currentExp)*100)/Player.expNeeded;
			g.fillRect(111, 551, (int) 4.79*expCurrentProzent, 29);
			g.setColor(Color.blue);
			g.drawString("Erfahrung bis Level Up",myWidth/2-60, 570);
	
			
			g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 14));
			g.setColor(Color.white);
			g.drawString("Level: ", 607, 50);
			g.drawString(Integer.toString(Player.Level)+ "", 670, 50);
			
			g.drawString("Round: ", 607, 100);
			g.drawString(Integer.toString(Screen.logic.round)+ "", 670, 100);
			
			g.drawString("Leben: ", 607, 150);
			g.drawString(Integer.toString(Player.life)+ "", 670, 150);
			if(Player.pizzaCount<9){
			g.drawString("Pizza's : ", 607, 200);
			g.drawString(Integer.toString(Player.pizzaCount)+ "", 670, 200);
			}
			else
			{
				g.drawString("Pizza's : ", 610, 200);
				g.drawString("MAX"+ "", 670, 200);
				
			}
			
			for (Monster m : monsterList) {
				if (m.ingame) {
					m.draw(g);
				}
			}

			for (Projectile p : projectiles) {
				if (p.isIngame) {
					p.draw(g);
				}
			}
		}
		//hasselHofAppears = true;
		if(hasselHofAppears && !isShowingSkillMenu)
		{
			hasselHoff.draw(g);
			if(!hasselHofsMoves)
			{
				g.drawImage(ImageLoader.haselHoffWarning, Screen.myWidth/2 - ImageLoader.haselHoffWarning.getWidth()/2, Screen.myHeight/2, ImageLoader.haselHoffWarning.getWidth(), ImageLoader.haselHoffWarning.getHeight(), null);
			}
		}
		
		skillMenu.draw(g);
		//isLost = true;
		//hasselHofAppears = true;
		
		
		if(isLost)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 50));
			g.drawString("YOU LOST !!!!" , myWidth/2-140, myHeight/2);
			g.drawString("Try Again" , myWidth/2-120, myHeight/2+70);
			
			g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 12));
			g.drawString("Credits goes to:" , myWidth/2-100, myHeight/2+150);
			g.drawString("All HFU-Game Designer present" , myWidth/2-100, myHeight/2+180);
			g.drawString("All HFU-Game Musiker present" , myWidth/2-100, myHeight/2+210);
			g.drawString("Myself (M.O)" , myWidth/2-100, myHeight/2+240);
			
		}
		//beatHasselhof= true;
		if(beatHasselhof)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 40));
			g.drawString("YOU WIN !!!!" , myWidth/2-100, myHeight/2-70);
			g.drawString("Incredible you've beaten" , myWidth/2-250, myHeight/2);
			

			g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 60));
			g.drawString("THE HASSELHOF" , myWidth/2-250, myHeight/2+90);
			
			
			g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 12));
			g.drawString("Credits goes to:" , myWidth/2-100, myHeight/2+150);
			g.drawString("All HFU-Game Designer present" , myWidth/2-100, myHeight/2+180);
			g.drawString("All HFU-Game Musiker present" , myWidth/2-100, myHeight/2+210);
			g.drawString("Myself (M.O)" , myWidth/2-100, myHeight/2+240);
			
		}
	}
	}

}
