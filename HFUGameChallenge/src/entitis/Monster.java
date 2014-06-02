package entitis;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import scenceStuff.Screen;


public class Monster extends Rectangle  {
	public boolean ingame = false;
	SecureRandom generator = new SecureRandom();
	Image im;
	int life;
	public boolean isPizza;
	
	public Monster(Image im, int life){
			int randx= generator.nextInt(400);
			randx=randx+125;
			
			x = randx;
			y = 0;
			width = im.getWidth(null)+5;
			height = im.getHeight(null)+5;
			ingame = true;
			this.im = im;
			this.life = life;
			System.out.println("Monster init");
			isPizza = false;
		
	}
	
	public static void checkMonsterCollision()
	{
		for (Monster m : Screen.monsterList) {
			for (Projectile p : Screen.projectiles) {
				if (m.intersects(new Polygon(p.xpoints, p.ypoints, p.npoints).getBounds2D())){ //m.contains(p.ypoints[0], p.xpoints[0])) {
					int random = m.generator.nextInt(101);
					if(random<Projectile.critChance)
					{
						m.life -= Projectile.dmg*Projectile.critDmg;
						
					}else
					{
						m.life -= Projectile.dmg;
					}
					System.out.println(m.life);
					
					System.out.println("Collision detekted");
					p.isIngame = false;
					if (m.life <= 0) {
						die(m);
					}
				}
			}
		}
	}
	
	private static void die(Monster m)
	{
		if(!m.isPizza){
			Player.currentExp +=(15+Screen.logic.round);
			Logic.killCount++;
			m.ingame = false;
			Screen.logic.spawnPizza();
		}
		else{
			m.ingame = false;
			if(Player.pizzaCount<9){
			Player.pizzaCount++;
			}
		}
		
	}
	
	public static void moveAndCheckToKillMonster()
	{
		List<Monster> killerList = new ArrayList<Monster>();
		for(Monster p: Screen.monsterList)
		{
			p.moveAnimation();
			
			if(!p.ingame)
			{
				killerList.add(p);
			}
		}
		
		for(Monster p: killerList)
		{
			Screen.monsterList.remove(p);
		}
	}
	
	public boolean moveAnimation()
	{
		y++;
		if(!Screen.map.isCordinMap(new Point(x, y + height)))
		{
			//Screen.projectiles.remove(this);
			Logic.killCount++;
			Player.life--;
			this.ingame = false;
			return true;
		}
		
		return false;
	}
	
	public void draw(Graphics g) {
		g.drawImage(im, x, y, width, height, null);
	}
}
