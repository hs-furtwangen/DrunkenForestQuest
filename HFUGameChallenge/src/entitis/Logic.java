package entitis;

import java.security.SecureRandom;

import scenceStuff.ImageLoader;
import scenceStuff.Screen;

public class Logic {

	public int monsterCount = 0;
	public int maxCount =4;
	public int round = 0;
	public static boolean spawnPizza=false;
	SecureRandom generator = new SecureRandom();
	
	public static int killCount=0;
	
	public void spawnMonster()
	{
		//spawnPizza();
		if(monsterCount < maxCount && round <5){
			monsterCount ++;
			Screen.monsterList.add(new Monster(ImageLoader.monsterOne, (round*round*100)+100));
		}
		if(killCount >= maxCount && Screen.monsterList.isEmpty())
		{
				Screen.hasStarted = false;
				monsterCount = 0;
				killCount = 0;
				maxCount++;
				round++;
			
		}
		if(spawnPizza)
		{
			spawnPizza = false;
			Monster pizza = new Monster(ImageLoader.pizza, (round*round*200)+100);
			pizza.isPizza = true;
			Screen.monsterList.add(pizza);
		}
		if(round >=5)
		{
			Screen.hasselHofAppears = true;
		}
	}
	
	public boolean spawnPizza()
	{
		int randpizza= generator.nextInt(50);
		if(randpizza%49 == 0)
		{
			spawnPizza = true;
			return true;
		}
		return false;
	}
	
	
	
}
