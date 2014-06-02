package entitis;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import scenceStuff.*;

public class Player extends Rectangle{

	public Image im;
	int speed;
	int maxSpeed = 2;
	public int myDirection =0;
	public static int Level = 1;
	public static int SkillPoints = 0;
	public static int expNeeded =50;
	public static int currentExp = 0;
	public static int life = 3;
	public static int pizzaCount=0;
	
	
	public Player(int x, int y,Image im)
	{
		this.x = x;
		this.y = y;
		this.height = im.getHeight(null)+5;
		this.width = im.getWidth(null)+5;
		this.im = im;
	}
	
	public static void checkLevelUp()
	{
		if(currentExp>expNeeded)
		{
			currentExp =0;
			expNeeded*=1.1;
			Level++;
			SkillPoints++;
		}
	}
	
	public void move(int direction)
	{
		if(myDirection != direction)
		{
			myDirection = direction;
			speed = 1;
		}
		else
		{
			System.out.print(speed);
			if(speed < maxSpeed)
			{
				speed ++;
			}
		}
		
		switch (direction) {
		case 1:
			if (Screen.map.isCordinMap(new Point(x + width + speed, y))) {
				this.x += speed;
			}else
			{
				Screen.playerDirection = 0;
			}
			break;

		case 2:
			if (Screen.map.isCordinMap(new Point(x - speed, y))) {
				this.x -= speed;
			}else
			{
				Screen.playerDirection = 0;
			}
			break;

		case 3:
			if (Screen.map.isCordinMap(new Point(x , y - speed))) {
				this.y -= speed;
			}else
			{
				Screen.playerDirection = 0;
			}
			break;

		case 4:
			if (Screen.map.isCordinMap(new Point(x, y + height + speed))) {
				this.y += speed;
			}else
			{
				Screen.playerDirection = 0;
			}
			break;
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(im, x, y, width, height, null);
 	}
}
