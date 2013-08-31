package gunnars;

public class Enemy extends Character {

	private int ENEMY_SPEED = -1;
	private int HP = 100;
	
	public Enemy(String sprite, int x, int y, int width, int height) {
		
		super("erik.png", x, y, width, height);
		setDx(ENEMY_SPEED);
		setHP(HP);
		
	}
	
	public void update() {
		super.update();
	}

}
