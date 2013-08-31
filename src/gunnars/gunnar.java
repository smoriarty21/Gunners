package gunnars;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Image;

public class gunnar extends JFrame {
	
	public gunnar() {
		
		add(new Board());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Gunnars");
		setResizable(false);
		setVisible(true);	
		
	}
	
	public static void main(String args[]) {
		new gunnar();
		
	}
}
