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
		//paintadd(g);
		
		for(int i=0;i<20;i++){
			for(int j=0;j<10;j++){
				if(te.pile.main[i][j]!=null){
				g.setColor(te.pile.main[i][j].color);
				g.fillRect(5+j*24, 10+i*24, 24, 24);
				g.setColor(Color.BLACK);
				g.drawRect(5+24*j, 10+24*i, 24, 24);
				}
			}
		}
		
		painttype(g);
		
	}
	
	/*void paintadd(Graphics g){
		for(int i=0;i<20;i++){
			for(int j=0;j<10;j++){
				if(te.pile.main[i][j]!=null){
				System.out.println("ok");
				g.setColor(te.pile.main[i][j].color);
				g.fillRect(5+j*24, 10+i*24, 24, 24);
				g.setColor(Color.BLACK);
				g.drawRect(5+24*j, 10+24*i, 24, 24);
				}
			}
		}
	}*/
	
	void painttype(Graphics g){
		int[][] position = Types.converse(te.currenttype);
		g.setColor(te.currenttype.color);
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(position[i][j]==1){
					if((i+te.currenttype.col)>=0&&(j+te.currenttype.row)>=0&&(i+te.currenttype.col)<20){
					g.setColor(te.currenttype.color);
					g.fillRect(5+te.currenttype.row*24+24*j, 10+te.currenttype.col*24+24*i, 24, 24);
					g.setColor(Color.BLACK);
					g.drawRect(5+te.currenttype.row*24+24*j, 10+te.currenttype.col*24+24*i, 24, 24);
					}
				}
			}
		}
	}
	
}
