package gunnars;

import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Platform extends GameObject{
	
	boolean isVisible = false;
	String image;
	
	public Platform(int a, int b, String image){
		super(image);
		setX(a);
		setY(b);
		//topOfPlatformY = getY() - getHeight();
		//
		isVisible = true;
		
	}
	
}
