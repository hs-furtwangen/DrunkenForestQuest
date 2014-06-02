package map;


import java.awt.Graphics;
import java.awt.Point;

import scenceStuff.Screen;
import scenceStuff.Value;

public class TileMap {
	public int worldWidth = 12;
	public int worldHeight = 13;
	public int blockSize = 40;

	public Tile[][] tileMap;

	public TileMap() {
		define();
	}

	public void define() {
		tileMap = new Tile[worldHeight][worldWidth];

		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[0].length; x++) {
				tileMap[y][x] = new Tile((Screen.myWidth / 2)
						- (worldWidth * blockSize) / 2 + blockSize * x, y
						* blockSize, blockSize, blockSize, Value.space);
			}
		}
	}

	private Tile getTileByLocation(Point p){
		
		for(int i = 0; i<tileMap.length; i++)
		{
			for(int j =0; j<tileMap[i].length; j++)
			{
				if(tileMap[i][j].contains(p))
				{
					return tileMap[i][j];
				}
			}
		}
		
		return null;
	}
	
	public boolean isCordinMap(Point p)
	{
		
		for(int i = 0; i<tileMap.length; i++)
		{
			for(int j =0; j<tileMap[i].length; j++)
			{
				if(tileMap[i][j].contains(p))
				{
					return true;
				}
			}
		}
		
		return false;
	}


	public void draw(Graphics g) // drawing the map
	{
		for (int y = 0; y < tileMap.length; y++) {
			for (int x = 0; x < tileMap[y].length; x++) {
				tileMap[y][x].draw(g);

			}

		}

	}

}