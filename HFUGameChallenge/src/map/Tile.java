package map;


import java.awt.Graphics;
import java.awt.Rectangle;

import scenceStuff.Screen;


public class Tile extends Rectangle{
	
	public Rectangle towerBlock;
	public int towerSize = 125;
	public int tileId;
	
	public Tile(int x, int y, int width, int height, int groundId) {
		setBounds(x, y, width, height);

		towerBlock = new Rectangle(x - (towerSize / 2), y - (towerSize / 2),
				width + (towerSize), height + (towerSize));
	}

	public void draw(Graphics g) {
		g.drawImage(Screen.tileset[0], x, y, width, height, null);
		
	}
	
}
