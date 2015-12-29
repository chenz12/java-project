package dazuoye;

import java.awt.*;

import javax.swing.*;

public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tetris te;
	private static final Font small = new Font("Tahoma", Font.BOLD,11);
	public static final int cube = 24;
	
	
	MainPanel(Tetris tetris){
		this.te = tetris;
		this.setPreferredSize(new Dimension(250,500));
		this.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(5,175,5));
		g.setFont(small);
		for(int i=0;i<11;i++){
			for(int j=0;j<21;j++){
				g.drawLine(5, 10+j*24, 245, 10+j*24);
				g.drawLine(5+24*i, 10, 5+24*i, 490);
				
			}
		}
		
	}
	
}
