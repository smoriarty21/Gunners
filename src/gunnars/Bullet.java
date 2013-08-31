package gunnars;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Bullet extends GameObject{
	
	private final int BOARD_WIDTH = 800;
	private int BULLET_SPEED = 3;
	
	public Bullet(int x, int y) {
		super("bullet.png", x, y);
		setVisable(true);
		setDx(BULLET_SPEED);
	}
	
	public void update() {
		super.update();
		if(getX() > BOARD_WIDTH)
			setVisable(false);
	}
	

}
