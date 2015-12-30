package dazuoye;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;

public class Tetris extends JFrame{
	public final static int MAXCOLOR=230;
	public final static int MINCOLOR=90;
	public final static int MIDCOLOR=160;
	private SidePanel side;
	private MainPanel main;
	private int level;
	private int score;
	Add pile;
	public timer t;
	Types currenttype;
	Types nexttype;
	
	
	Tetris(){
		super("Tetris");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.side = new SidePanel(this);
		this.main = new MainPanel(this);
		add(side,BorderLayout.EAST);
		add(main,BorderLayout.CENTER);
		pack();
		pile = new Add();
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				switch(e.getKeyCode()){
				case KeyEvent.VK_A:
					moveleft(currenttype);break;
				case KeyEvent.VK_D:
					moveright(currenttype);break;
				case KeyEvent.VK_S:
					t.speed = 10;break;
				case KeyEvent.VK_Q:
					if(currenttype.row+currenttype.leftindex[currenttype.currentrotation]==0){
						currenttype.row++;
					currenttype.currentrotation = (currenttype.currentrotation+1)%4;
					moveleft(currenttype);}
					else if(currenttype.row+3-currenttype.rightindex[currenttype.currentrotation]==9){
						currenttype.row--;
						currenttype.currentrotation = (currenttype.currentrotation+3)%4;
						moveright(currenttype);
					}
					else
						currenttype.currentrotation = (currenttype.currentrotation+1)%4;
					break;
				case KeyEvent.VK_E:
					if(currenttype.row+3-currenttype.rightindex[currenttype.currentrotation]==9){
						currenttype.row--;
						currenttype.currentrotation = (currenttype.currentrotation+3)%4;
						moveright(currenttype);
					}
					else if(currenttype.row+currenttype.leftindex[currenttype.currentrotation]==0){
						currenttype.row++;
					currenttype.currentrotation = (currenttype.currentrotation+1)%4;
					moveleft(currenttype);}
					else 
					currenttype.currentrotation = (currenttype.currentrotation+3)%4;
					
					break;
				}
			}
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_S){
					t.speed = 1;
				}
			}
		});
		t = new timer(1);
		nexttype = Tetris.RandomType();
		currenttype = Tetris.RandomType();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tetris game = new Tetris();
		game.startgame();
	}
	
	void startgame(){
		while(true){
			
			cycle();
			
			score += pile.update() << 2;
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main.repaint();
			side.repaint();
		}
	}
	
	void cycle(){
		if(t.iscycled()){
			if(nextable()){
			currenttype.col++;
			}
			else{
				pile.addtype(currenttype, currenttype.col, currenttype.row);
				currenttype = nexttype;
				nexttype = Tetris.RandomType();
			}
		}
		
	}
	
	boolean nextable(){
		return pile.place(currenttype, currenttype.col+1, currenttype.row);
	}
	
	
	public static Types RandomType(){
		int n = new Random().nextInt(7);
		//int n =6;
		switch(n){
		case 0:
			return Types.TypeI();
		case 1:
			return Types.TypeT();
		case 2:
			return Types.TypeS();
		case 3:
			return Types.TypeZ();
		case 4:
			return Types.TypeL();
		case 5:
			return Types.TypeJ();
		case 6:
			return Types.TypeO();
		default:
			return Types.TypeI();
		}
		
	}
	public int[] bound(Types a){
		int[] res =  new int[4];
		int[][] matrix = Types.converse(a);
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(matrix[i][j]==1&&res[0]==0){
					res[0]=i;
				}
				if(matrix[i][j]==1){
					res[1]=i;
				}
				if(matrix[i][j]==1&&res[2]==0){
					res[2]=j;
				}
				if(matrix[i][j]==1){
					res[3]=j;
				}
			}
		}
		return res;
	}
	
	public void moveleft(Types a){
		if(a.row>=0&&pile.leftmoveable(a)){
		a.row--;
		}
	}
	public void moveright(Types a){
		if(a.row<=9&&pile.rightmoveable(a)){
			a.row++;
		}
	}
	
}
