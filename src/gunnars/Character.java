package gunnars;

import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Character extends GameObject {
	
	public enum CharacterStatus {RUNNING, JUMPING}
	CharacterStatus currentStatus;
	
	public final int PLAYER_SIZE = 20;
	
	private ArrayList<Bullet> bullets;
	
	private ArrayList<Enemy> enemies;
	
	public Character(String imageName) {
		super(imageName);
		bullets = new ArrayList<Bullet>();
		currentStatus = CharacterStatus.RUNNING;
	}
	public Character(String imageName, int newX, int newY) {
		super(imageName, newX, newY);
		bullets = new ArrayList<Bullet>();
		currentStatus = CharacterStatus.RUNNING;
	}
	public Character(String imageName, int newX, int newY, int newWidth, int newHeight) {
		super(imageName, newX, newY, newWidth, newHeight);
		bullets = new ArrayList<Bullet>();
		currentStatus = CharacterStatus.RUNNING;
	}
	public void run() {
		setDy(0);
		currentStatus = CharacterStatus.RUNNING;
	}
	
	public void jump() {
		currentStatus = CharacterStatus.JUMPING;
	}
	
	public boolean isJump() {
		if(currentStatus == CharacterStatus.JUMPING) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void fire() {
		bullets.add(new Bullet(getX() + getWidth(), getY() + getHeight()/2));
	}
	
	public void createEnemy() {
		enemies.add(new Enemy("erik.png", getX(), getY(), getWidth(), getHeight()));
	}
	
	public void update() {
		super.update();
		
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).isVisable()) {
				bullets.get(i).update();
			} else {
				bullets.remove(i);
			}
		}
		
		if(currentStatus == CharacterStatus.JUMPING){
			setDy(getDy() + 1);
		}
	}

}
