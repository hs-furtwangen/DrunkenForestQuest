package entitis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import scenceStuff.Screen;

public class Projectile extends Polygon {
	public boolean isIngame;
	Polygon t = new Polygon();
	
	public static int dmg = 10;
	public static double Genauigkeit = 100;
	public static int critChance = 0;
	public static int critDmg = 0;
	
	public Projectile()
	{
		System.out.println("Project initialise");
		//this.x = Screen.player.x + Screen.player.width/2;
		//this.y = Screen.player.y;
		isIngame = true;
		
		for(Point d: Screen.customisationProjectiles)
		{		
			this.addPoint(Screen.player.x +d.x+ Screen.player.width/2,Screen.player.y + d.y);
			
		}
	}
	
	public Projectile(int p)
	{
		System.out.println("Project initialise");
		//this.x = Screen.player.x + Screen.player.width/2;
		//this.y = Screen.player.y;
		isIngame = true;
		
		for(Point d: Screen.customisationProjectiles)
		{		
			this.addPoint(Screen.player.x +d.x+ Screen.player.width/2 + p-8,Screen.player.y + d.y);
			
		}
	}
	
	public static void moveAndCheckToKillProjectile() 
	{
		try{
		List<Projectile> killerList = new ArrayList<Projectile>();
		for(Projectile p: Screen.projectiles)
		{
			p.moveAnimation();
			
			if(!p.isIngame)
			{
				killerList.add(p);
			}
		}
		
		for(Projectile p: killerList)
		{
			Screen.projectiles.remove(p);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean moveAnimation()
	{
		for(int i =0; i<this.ypoints.length; i++)
		{
		this.ypoints[i] -= 1;
		
		if(!Screen.map.isCordinMap(new Point(this.xpoints[i], this.ypoints[i])))
		{
			//Screen.projectiles.remove(this);
			//isIngame = false;
			//return true;
		}
		}
		return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(Screen.currentColor);
		
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
		
		//g2.drawPolygon(this);
		//g2.fillPolygon(this);
		//g.fillPolygon(this);
		g.drawPolygon(this);
		//Polygon p = new Polygon(this.xpoints, this.ypoints, this.npoints);
		//Rectangle r = (Rectangle) p.getBounds2D();
		//g.fillRect(r.x, r.y, r.width, r.height);
		
	}
}
