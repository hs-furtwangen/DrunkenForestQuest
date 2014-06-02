package scenceStuff;


import java.io.BufferedReader;
import java.util.Scanner;

public class Save {
	public void loadSave (BufferedReader file){
		Scanner loadScanner = new Scanner(file);
		
		while(loadScanner.hasNext()){
			
			for(int y=0; y<Screen.map.tileMap.length;y++){
				for(int x=0; x<Screen.map.tileMap[0].length;x++){
					Screen.map.tileMap[y][x].tileId = loadScanner.nextInt();
				}
			}
			
		}
		
		loadScanner.close();
	}
}
