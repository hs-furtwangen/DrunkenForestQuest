package scenceStuff;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class ResetButton extends Rectangle {

	private Image im;

	public ResetButton(Image im,int x, int y)
	{
		this.im = im;
		this.x = x;
		this.y = y;
		
		height = im.getHeight(null);
		width = im.getWidth(null);
	}
	
	public void doCodeIfContains(Point p){
		if(this.contains(p))
		{
			
		}
	}
	
	public void draw(Graphics g) {

		g.drawImage(im, x, y, width, height, null);
		
	}
}
