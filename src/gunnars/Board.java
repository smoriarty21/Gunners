package gunnars;

import gunnars.Character.CharacterStatus;
import java.util.Random;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.geom.AffineTransform;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private Player player;
	private Platform ground;
	private Platform platform;
	private Enemy enemy;
	private Image backgroundImage;
	
	Random random = new Random();

	private int enemyY = random.nextInt(450) + 50;
	
	public Board bTest = this;
	
	public Board() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		
		player = new Player();
		ground = new Platform(0, 570, "floor.jpg");
		platform = new Platform(280, 400, "platform.jpg");
	
		enemy = new Enemy("erik.png", 800, enemyY, 50, 70);
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		enemy.setVisable(false);
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("background.jpg"));
		backgroundImage = ii.getImage();
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(backgroundImage, 0, 0, null);
		g2d.drawImage(platform.getImage(), platform.getX(), platform.getY(), this);
		g2d.drawImage(ground.getImage(), ground.getX(), ground.getY(), this);
		
		//If enemy is visable draw
		if(enemy.isVisable() == false) {
			enemy.setVisable(true);
			g2d.drawImage(enemy.getImage(), enemy.getX() , enemy.getY(), this);
		}
		
		/*while(enemy.isVisable() == false){
			enemyY = random.nextInt(450) + 50;
			enemy.setVisable(true);
			System.out.println(enemyY);
		}*/
		
		if(player.isVisable() == true) {
			g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}
		
		
		
		ArrayList<Bullet> bulletsCreated = player.getBullets();
		
		for (int i = 0; i < bulletsCreated.size(); i++) {
			Bullet b =  (Bullet) bulletsCreated.get(i);
			g2d.drawImage(b.getImage(), b.getX(), b.getY(), this);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			player.fire();
		}
		
		if (key == KeyEvent.VK_LEFT) {
			player.setDx(-1);
        }

        if (key == KeyEvent.VK_RIGHT) {
            player.setDx(1);
        }
        
        if(key == KeyEvent.VK_UP && !player.isJump()) {
        	player.jump();
        	player.setDy(-24);
        }
        
        if (key == KeyEvent.VK_ESCAPE) {
        	System.exit(0);
         } 
           
      }
	
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            player.setDx(0);
        }
    }	
	
	public void actionPerformed(ActionEvent e) {	
		player.update();
		enemy.update();
		checkCollision();
		repaint();
	}
	
	public void checkCollision() {
		Rectangle playerBounds = player.getBounds();
		Rectangle groundBounds = ground.getBounds();
		Rectangle platformBounds = platform.getBounds();
		Rectangle enemyBounds = enemy.getBounds();
		
		ArrayList<Bullet> bulletsCreated = player.getBullets();
		
		for (int i = 0; i < bulletsCreated.size(); i++) {
			Bullet b =  (Bullet) bulletsCreated.get(i);
			Rectangle bulletBounds = b.getBounds();
			
			//Check intersection with bullets and enemys
			if(enemyBounds.intersects(bulletBounds)) {
				enemyY = random.nextInt(450) + 50;
				enemy.setVisable(false);
				b.setVisable(false);
				enemy.setX(650);
				enemy.setY(enemyY);
			}
		}
		
		//Check collision with player and enemy
		if(enemyBounds.intersects(playerBounds)) {
			player.setHP(player.getHP() - 50);
			
			System.out.println("Jew Hit" + player.getHP());
		}
			
		
		
		// Intersects with ground
		if(groundBounds.intersects(playerBounds)) {
			player.run();
			player.setY(ground.getY() - player.getHeight() + 1);
		} else {
			player.currentStatus = CharacterStatus.JUMPING;
		}
		
		// Intersect with platform from left side
		if(platformBounds.intersects(playerBounds) && player.getX() < platform.getX()) {
		
		}
		 
		// Intersect with platform from above
		if(platformBounds.intersects(playerBounds) && player.getY() < platform.getY() && player.getDy() >= 0) {
			player.run();
			player.setY(platform.getY() - player.getHeight() + 1);
		}
		
		//Send enemy back to start if he goes off screen(temporary)
		if(enemy.getX() <= 0) {
			enemy.setX(650);
		}
		
		// Enemy intersects with ground
		/*if(groundBounds.intersects(enemyBounds)) {
			enemy.jump();
			enemy.setY(ground.getY() - enemy.getHeight() - 75);
		}*/
		
	}
	
	
	private class TAdapter extends KeyAdapter {
		
		public void keyReleased(KeyEvent e) {
	        bTest.keyReleased(e);
	    }
	
	    public void keyPressed(KeyEvent e) {
	        bTest.keyPressed(e);
	    }
		
	}

}
