package gunnars;

import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class GameObject {
	private int dx, dy, x, y, width, height, HP;
	
	private Image image;
	
	private boolean Visable;
	
	public GameObject(String imageName) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imageName));
		image = ii.getImage();
	}
	
	public GameObject(String imageName, int newX, int newY) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imageName));
		image = ii.getImage();
		x = newX;
		y = newY;
	}
	
	public GameObject(String imageName, int newX, int newY, int newWidth, int newHeight) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imageName));
		image = ii.getImage();
		image = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		x = newX;
		y = newY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int newHP) {
		HP = newHP;
	}
	public int getWidth() {
		return image.getWidth(null);
	}
	
	public int getHeight() {
		return image.getHeight(null);
	}
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public void setDx(int newDx) {
		dx = newDx;
	}
	
	public void setDy(int newDy) {
		dy = newDy;
	}
	
	public Image getImage() {
		return image;
	}
	
	public boolean isVisable() {
		return Visable;
	}
	
	public void setVisable(boolean newVisable) {
		Visable = newVisable;
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
}
