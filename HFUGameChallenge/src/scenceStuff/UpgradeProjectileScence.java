package scenceStuff;

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

import entitis.Player;
import entitis.Projectile;
import entitis.doublePoint;


public class UpgradeProjectileScence extends Polygon{

		Rectangle[] colorRectangles = new Rectangle[9];
		Color[] colorArray = new Color[9];
		ResetButton reset;
		SaveButton save;
		Polygon tempPoly;
		List<Point> tempPoints;
		int spendSkillPoints=0;
		
		public UpgradeProjectileScence() {
			for(Point d: Screen.customisationProjectiles)
			{		
				this.addPoint(Screen.myWidth/2+ (d.x*3),Screen.myHeight/2+ (d.y*3));
				//this.addPoint(Screen.myWidth/2+ d.endPoint.x,Screen.myHeight/2+ d.endPoint.y);
			}
			
			for(int i = 0; i< colorRectangles.length; i++)
			{	
				colorRectangles[i] = new Rectangle(100 + i*50,500,40,40);
			}
			
			colorArray[0] = Color.black;
			colorArray[1] = Color.gray;
			colorArray[2] = Color.GREEN;
			colorArray[3] = Color.MAGENTA;
			colorArray[4] = Color.blue;
			colorArray[5] = Color.ORANGE;
			colorArray[6] = Color.RED;
			colorArray[7] = Color.DARK_GRAY;
			colorArray[8] = Color.YELLOW;
			
			tempPoly = new Polygon(this.xpoints, this.ypoints, this.npoints);
			tempPoints = new ArrayList<Point>();
			
			for(Point p: Screen.customisationProjectiles)
			{
				tempPoints.add(new Point(p.x, p.y));
			}
					
			save = new SaveButton(ImageLoader.saveButton, 610, 440);
			reset = new ResetButton(ImageLoader.resetButton, 610, 400);
			
		}

		public void doClickEvent(Point p)
		{
			if(Screen.isShowingSkillMenu)
			{
				
				if(save.contains(p))
				{
					Screen.upgradeProjectileScence.addPoints();
				}
				if(reset.contains(p))
				{
					doReset();
				}
				
				if(!Screen.upgradeProjectileScence.checkColorRectangle(p) && !Screen.skillMenu.contains(p)&&
						!reset.contains(p) && !save.contains(p))
				{
					addTempPoint();
				}
				
			}
		}
		
		public void setUpConfig()
		{
			tempPoly = new Polygon(this.xpoints, this.ypoints, this.npoints);
			tempPoints = new ArrayList<Point>();
			
			for(Point p: Screen.customisationProjectiles)
			{
				tempPoints.add(new Point(p.x, p.y));
			}
			
		}
		
		private void doReset()
		{
			tempPoly = new Polygon(this.xpoints, this.ypoints, this.npoints);
			tempPoints = new ArrayList<Point>();
			
			for(Point p: Screen.customisationProjectiles)
			{
				tempPoints.add(new Point(p.x, p.y));
			}
			Player.SkillPoints+=spendSkillPoints;
			spendSkillPoints =0;
		}
		
		public boolean checkColorRectangle(Point p)
		{
			for(int i = 0; i< colorRectangles.length; i++)
			{
				if(colorRectangles[i].contains(p))
				{
					Screen.currentColor = colorArray[i];	
					return true;
				}
			}
			return false;
		}
		
		private void addTempPoint()
		{
			if(Player.SkillPoints >0){
			Point p =new Point(Screen.mse.x-Screen.myWidth/2,Screen.mse.y - Screen.myHeight/2);
			Point tempPoint = new Point (Screen.myWidth/2 + p.x/3, Screen.myHeight/2 + p.y/3);
			tempPoly.addPoint(tempPoint.x, tempPoint.y);
			
			Point d = new Point( p.x/16,p.y/16);
			tempPoints.add(d);
			Player.SkillPoints--;
			spendSkillPoints++;
			}
		}
		
		public void addPoints()
		{
			checkIfPointsExist();
			spendSkillPoints =0;
			//Point p =new Point(Screen.mse.x-Screen.myWidth/2,Screen.mse.y - Screen.myHeight/2);
			//Point tempPoint = new Point (Screen.myWidth/2 + p.x/3, Screen.myHeight/2 + p.y/3);
			//this.addPoint(tempPoint.x, tempPoint.y);
			
			//Point oldPoint = new Point(Screen.customisationProjectiles.get(Screen.customisationProjectiles.size()-1).x ,Screen.customisationProjectiles.get(Screen.customisationProjectiles.size()-1).y);
			//Point d = new Point( p.x/16,p.y/16);
			//Screen.customisationProjectiles.add(d);
			
			//Screen.customisationProjectiles = tempPoints.subList(0, tempPoints.size());
			
		}
		
	private void checkIfPointsExist() {
		Point p;
		for (int i = 0; i < tempPoly.npoints; i++) {
			if (!this.contains(p = new Point(tempPoly.xpoints[i],
					tempPoly.ypoints[i]))) {
				this.addPoint(p.x, p.y);
			}
		}
		
		for(Point point: tempPoints)
		{
			if(!Screen.customisationProjectiles.contains(point))
			{
				Screen.customisationProjectiles.add(new Point(point.x,point.y));
			}
		}
	}
		
		public void draw(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			drawLayout(g);
	        
			//for(doublePoint d: Screen.customisationProjectiles)
			//{
				//g2.drawLine((d.startPoint.x*5)+Screen.myWidth/2, (d.startPoint.y*5)+Screen.myHeight/2, (d.endPoint.x*5)+Screen.myWidth/2, (d.endPoint.y*5) + Screen.myHeight/2);
			//}
			Polygon tempPolygon = new Polygon(tempPoly.xpoints, tempPoly.ypoints, tempPoly.npoints);
			
			Point p =new Point(Screen.mse.x-Screen.myWidth/2,Screen.mse.y - Screen.myHeight/2);
			Point tempP = new Point (Screen.myWidth/2 + p.x/3, Screen.myHeight/2 + p.y/3);
			tempPolygon.addPoint(tempP.x, tempP.y);
			
			for(int i =0; i<colorRectangles.length; i++)
			{
				g.setColor(colorArray[i]);
				g.fillRect(colorRectangles[i].x, colorRectangles[i].y, colorRectangles[i].width, colorRectangles[i].height);
			}
			
			save.draw(g);
			reset.draw(g);
			
			g.setColor(Color.black);
			g.drawRect(90, 490, 460, 60);
			
			g.drawImage(ImageLoader.layOutSkills, 510, 5,ImageLoader.layOutSkills.getWidth(), ImageLoader.layOutSkills.getHeight(), null);
			g.drawImage(ImageLoader.recTangleProjectile, 215, 190,275,250, null);
			
			
			g.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 14));
			g.drawString("Schaden: ", 530, 250);
			checkDmg(tempPolygon);
			g.drawString(Integer.toString(Projectile.dmg), 630, 250);
			g.drawString("Crit Chance", 530, 200);
			g.drawString(Integer.toString(Projectile.critChance)+ "%", 630, 200);
			g.drawString("Crit Schaden", 530, 150);
			g.drawString("+" +Integer.toString(Projectile.critDmg)+ "%", 630, 150);
			g.drawString("Level ", 530, 100);
			g.drawString(Integer.toString(Player.Level)+ "", 630, 100);
			g.drawString("SkillPoints", 530, 50);
			g.drawString(Integer.toString(Player.SkillPoints), 630, 50);
			

			g.drawString("Farben design only (later maybe more)",Screen.myWidth/2-90, 570);
			
			
			g.setColor(Screen.currentColor);
			g.drawPolygon(tempPolygon);
			
		}
		
		public void checkDmg(Polygon p)
		{
			int tempRealDmg = 0;
			double tempDmg=0;
			double tempCritChance = 0;
			for(int i = 0; i< p.npoints-2;i++)
			{
				double d = angleBetween(new Point(p.xpoints[i], p.ypoints[i]), new Point(p.xpoints[i+1], p.ypoints[i+1]), new Point(p.xpoints[i+2], p.ypoints[i+2]));
				tempDmg +=d;
			}
			
			Rectangle d = p.getBounds();
			
			tempCritChance = p.npoints*0.25;
			tempCritChance += (d.width/20+d.height/10)-tempDmg/50;
			tempRealDmg = (int) ((int) new Point((int)d.getMinX(), (int) d.getMinY()).distance(d.getMaxX(), d.getMaxY())+p.npoints*5);
			
			Projectile.dmg = tempRealDmg;
			Projectile.critDmg = (int)tempDmg;
			Projectile.critChance = (int)tempCritChance;
			
//			System.out.println(Projectile.dmg);
//			System.out.println(Projectile.critDmg);
//			System.out.println(Projectile.critChance);
			
		}
		
		private double angleBetween(Point center, Point current, Point previous) {

			  return Math.toDegrees(Math.atan2(current.x - center.x,current.y - center.y)-
			                        Math.atan2(previous.x- center.x,previous.y- center.y));
			}
		
		private void drawLayout(Graphics g)
		{
			g.setColor(Color.PINK);
			g.fillRect(0, 0, Screen.myWidth, Screen.myHeight);
			
			g.setColor(Screen.currentColor);
			Graphics2D g2 = (Graphics2D) g;
	        g2.setStroke(new BasicStroke(2));
		}

}
