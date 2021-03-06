package dazuoye;


import java.awt.*;


import javax.swing.JPanel;

public class SidePanel extends JPanel{
	
	private static final int size = 12;
	private static final int sq_lenth = 72;
	private static final int center_x = 120;
	private static final int center_y = 65;
	private static final int inset1 = 20;
	private static final int inset2 = 40;
	private static final int interval = 25;
	
	private static final Font small = new Font("Tahoma", Font.BOLD,11);
	private static final Font large = new Font("Tahoma", Font.BOLD,13);
	private static final Color color = new Color(5,179,5);
	
	private Tetris te;
	
	SidePanel(Tetris tetris){
		this.te = tetris;
		this.setPreferredSize(new Dimension(200, 500));
		this.setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		g.setFont(large);
		g.drawString("Level:"+te.level, inset1, 175);
		g.drawString("Score:"+te.score, inset1, 175+interval);
		g.drawString("Next Tile:", inset1, 70);
		g.drawString("Controls:", inset1, 280);
		
		int a = 280+interval;
		g.setFont(small);

		g.drawString("P -pause the game", inset2, a+=interval);
		g.drawString("+ -add the speed",inset2, a+=interval);
		g.drawString("- -reduce the speed", inset2, a+=interval);
		g.drawString("B -begin a new game", inset2, a+=interval);
		g.drawString("R -recover the game", inset2, a+=interval);
		g.drawString("ese -exit the game", inset2, a+=interval);
		g.drawRect(center_x-30, center_y-30, sq_lenth, sq_lenth);
		
		drawtile(g);
	}
	public void drawtile(Graphics g){
		
		int[][] position = Types.converse(te.nexttype);
		g.setColor(te.nexttype.color);
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				g.setColor(te.nexttype.color);
				if(position[i][j]==1){
					g.fillRect(90+12*j, 47+12*i, 12, 12);
					g.setColor(Color.BLACK);
					g.drawRect(90+12*j, 47+12*i, 12, 12);
				}
			}
		}
	}
}
