package gunnars;

import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Player extends Character{
	
	private int HP = 100;
	
	public Player() {
		super("player.png", 0, 425);
		setVisable(true);
		setHP(HP);
	}
		
	public void update() {
		super.update();
		
		if(getHP() <= 0){
			setVisable(false);
		}
	}
}
