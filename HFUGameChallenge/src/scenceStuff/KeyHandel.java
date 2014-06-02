package scenceStuff;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entitis.Player;
import entitis.Projectile;

public class KeyHandel implements MouseListener, MouseMotionListener,
		ActionListener, KeyListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Screen.upgradeProjectileScence.doClickEvent(new Point(arg0.getX(),arg0.getY()-20));
		Point p = new Point(arg0.getX(),arg0.getY()-20);
		if(Screen.skillMenu.contains(p))
		{
			if(Screen.isShowingSkillMenu)
			{
				Screen.upgradeProjectileScence.checkDmg(Screen.upgradeProjectileScence);
				Player.SkillPoints += Screen.upgradeProjectileScence.spendSkillPoints;
				Screen.upgradeProjectileScence.spendSkillPoints=0;
			}
			Screen.isShowingSkillMenu = !Screen.isShowingSkillMenu;
			Screen.upgradeProjectileScence.setUpConfig();
		}
		
		if(!Screen.isShowingSkillMenu && Screen.startButton.contains(p) && !Screen.hasStarted)
		{
			Screen.hasStarted = true;
			if(Screen.hasselHofAppears)
			{
				Screen.hasselHofsMoves = true;
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Screen.mse = new Point(
				(e.getX()) - (Frame.size.width - Screen.myWidth) / 2,
				(e.getY())
						- ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth) / 2));

		System.out.println(Screen.mse.x + " " + Screen.mse.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Screen.mse = new Point(
				(e.getX()) - (Frame.size.width - Screen.myWidth) / 2,
				(e.getY())
						- ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth) / 2));

		System.out.println(Screen.mse.x + " " + Screen.mse.y);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!Screen.isLost){
		int keyCode = e.getKeyCode();
		if(!Screen.isShowingSkillMenu){
			if (keyCode == KeyEvent.VK_LEFT) // LinkePfeiltaste
			{
				Screen.playerDirection = 2;
			}
			if (keyCode == KeyEvent.VK_RIGHT) // RechtePfeilTaste
			{
				Screen.playerDirection = 1;
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				Screen.playerDirection = 4;
			}
			if (keyCode == KeyEvent.VK_UP) {
				Screen.playerDirection = 3;
			}

			if (keyCode == KeyEvent.VK_F) {
				if(Screen.hasStarted || !Screen.monsterList.isEmpty() || Screen.hasselHofAppears){
					Screen.spawnShoot = true;
					//System.out.println("getShoot");
				}
			}
		}
		
		if(Screen.isIntroOver == false)
		{
			Screen.isIntroOver =true;
		}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
