package entitis;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.security.SecureRandom;

import scenceStuff.ImageLoader;
import scenceStuff.Screen;

public class Hasselhoff extends Rectangle {
	Rectangle hasselHoffFace;
	Rectangle hasselHoffMouth;
	int hasselHofDirection =0;
	int hasselHofMovePassage =25;
	int hasselHofMoveCounter =0;
	int hasselMoveCount =0;

	SecureRandom generator = new SecureRandom();
	
	int hasselHoffLebenFace = 2000000;
	int hasselHoffLebelSmile = 1000000;
	
	boolean faceLives = true;
	boolean smilieLives = true;
	
	public Hasselhoff()
	{
		hasselHoffFace = new Rectangle( Screen.myWidth/2- ImageLoader.haselHoffFace.getWidth()/2 , 0, ImageLoader.haselHoffFace.getWidth(), ImageLoader.haselHoffFace.getHeight());
		hasselHoffMouth = new Rectangle( Screen.myWidth/2- ImageLoader.haselHoffMouth.getWidth()/2 -6, ImageLoader.haselHoffFace.getHeight() - ImageLoader.haselHoffMouth.getHeight()/2-4, ImageLoader.haselHoffMouth.getWidth(), ImageLoader.haselHoffMouth.getHeight());
		
	}
	
	public void moveTheHasselHoff()
	{
		if(hasselMoveCount >=1){
			hasselHoffFace.y++;
			hasselHoffMouth.y++;
			hasselMoveCount =0;
		}
		else
		{
			hasselMoveCount++;
		}
	}
	
	private void moveHasselHof()
	{
		if(hasselHofMoveCounter <hasselHofMovePassage && hasselHofDirection ==0)
		{
			hasselHoffMouth.y++;
			hasselHofMoveCounter ++;
		}
		else if( hasselHofMoveCounter >= 0)
		{
			hasselHofDirection = 1;
			hasselHoffMouth.y--;
			hasselHofMoveCounter--;
		}
		else if(0 >= hasselHofMoveCounter)
		{
			hasselHofDirection =0;
		}
		
	}
	
	public void checkHasselHofCollision()
	{
		for (Projectile p : Screen.projectiles)
		{
			if(smilieLives)
			{
				if(hasselHoffMouth.intersects(new Polygon(p.xpoints, p.ypoints, p.npoints).getBounds2D()))
				{
					System.out.println("HaselHofcollision");
					int random = generator.nextInt(101);
					if(random<Projectile.critChance)
					{
						hasselHoffLebelSmile -= Projectile.dmg*Projectile.critDmg;
						
					}else
					{
						hasselHoffLebelSmile -= Projectile.dmg;
					}
					p.isIngame = false;
					if (hasselHoffLebelSmile <= 0) {
						smilieLives = false;
					}
				}
			}
			else if(faceLives)
			{
				if(hasselHoffFace.intersects(new Polygon(p.xpoints, p.ypoints, p.npoints).getBounds2D()))
				{
					System.out.println("HaselHofcollision");
					int random = generator.nextInt(101);
					if(random<Projectile.critChance)
					{
						hasselHoffLebenFace -= Projectile.dmg*Projectile.critDmg;
						
					}else
					{
						hasselHoffLebenFace -= Projectile.dmg;
					}
					p.isIngame = false;
					if (hasselHoffLebenFace <= 0) {
						Screen.beatHasselhof = true;
					}
				}
			}
		}
		
	}
//			for (Projectile p : Screen.projectiles) {
//				if (m.intersects(new Polygon(p.xpoints, p.ypoints, p.npoints).getBounds2D())){ //m.contains(p.ypoints[0], p.xpoints[0])) {
//					int random = m.generator.nextInt(101);
//					if(random<Projectile.critChance)
//					{
//						m.life -= Projectile.dmg*Projectile.critDmg;
//						
//					}else
//					{
//						m.life -= Projectile.dmg;
//					}
//					System.out.println(m.life);
//					
//					System.out.println("Collision detekted");
//					p.isIngame = false;
//					if (m.life <= 0) {
//						die(m);
//					}
//				}
//			}
//		}
	
	
	public void draw(Graphics g) {
		g.drawImage(ImageLoader.haselHoffFace, hasselHoffFace.x, hasselHoffFace.y, hasselHoffFace.width, hasselHoffFace.height, null);
		if(smilieLives){
			g.drawImage(ImageLoader.haselHoffMouth, hasselHoffMouth.x, hasselHoffMouth.y, hasselHoffMouth.width, hasselHoffMouth.height, null);
			moveHasselHof();
		}
	}
}
