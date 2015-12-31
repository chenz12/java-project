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
	int level;
	int score;
	Add pile;
	double maxspeed = 10.0;
	double tempspeed = 0.0;
	public timer t;
	Types currenttype;
	Types nexttype;
	boolean ispaused;
	boolean over;
	
	
	Tetris(){
		super("Tetris");
		level =1;
		score =0;
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
				case KeyEvent.VK_LEFT:
					moveleft(currenttype);break;
				case KeyEvent.VK_RIGHT:
					moveright(currenttype);break;
				case KeyEvent.VK_DOWN:
					tempspeed = t.speed;
					t.speed = maxspeed;break;
				case KeyEvent.VK_SPACE:
					if(currenttype.istypeI()){
						if(currenttype.row+currenttype.leftindex[currenttype.currentrotation]==0){
							currenttype.row +=2;
						currenttype.currentrotation = (currenttype.currentrotation+1)%4;
						moveleft(currenttype);
						moveleft(currenttype);
						}
						else if(currenttype.row+3-currenttype.rightindex[currenttype.currentrotation]==9){
							currenttype.row -=2;
							currenttype.currentrotation = (currenttype.currentrotation+1)%4;
							moveright(currenttype);
							moveright(currenttype);
						}
						else
							currenttype.currentrotation = (currenttype.currentrotation+1)%4;
					}
					
					else if(currenttype.row+currenttype.leftindex[currenttype.currentrotation]==0){
						currenttype.row++;
					currenttype.currentrotation = (currenttype.currentrotation+1)%4;
					moveleft(currenttype);}
					else if(currenttype.row+3-currenttype.rightindex[currenttype.currentrotation]==9){
						currenttype.row--;
						currenttype.currentrotation = (currenttype.currentrotation+1)%4;
						moveright(currenttype);
					}
					else
						currenttype.currentrotation = (currenttype.currentrotation+1)%4;
					break;
				/*case KeyEvent.VK_E:
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
					
					break;*/
				case KeyEvent.VK_ADD:
					if(level<10){
					t.speed = t.speed*1.7;
					level++;
					}
					break;
				case KeyEvent.VK_SUBTRACT:
					if(level>1){
					t.speed = t.speed/1.7;
					level--;
					}
					break;
				case KeyEvent.VK_B:
					
					if(over){
						over = false;
						}
					newgame();
					
					break;
				case KeyEvent.VK_P:
					pause();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				}
			}
			private void pause() {
				if(!ispaused)// TODO Auto-generated method stub
				{
					ispaused = true;
				}
				else ispaused = false;
				
			}
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					t.speed = Math.pow(1.7, level);
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
	void newgame(){
		this.level = 1;
		this.score =0;
		main.clean();
		t.reset();
		this.over = false;
		this.currenttype =Tetris.RandomType();
		//startgame();
		main.repaint();
		side.repaint();
	}
	
	void startgame(){
		while(true){
			try {
				Thread.sleep(2);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		while(!over){
			
			cycle();
			if(pile.update()!=0)
			{
				score += 10 << pile.update();
			}
			isover();
			
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
	}
	
	void isover() {
		// TODO Auto-generated method stub
		if(pile.getheight()<=4-currenttype.upindex[currenttype.currentrotation]-currenttype.lowindex[currenttype.currentrotation]){
			over = true;
		}
	}
	void cycle(){
		if(t.iscycled()&&!ispaused){
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
		if(a.row+a.leftindex[a.currentrotation]>=0&&pile.leftmoveable(a)){
		a.row--;
		}
	}
	public void moveright(Types a){
		if(a.row+3-a.rightindex[a.currentrotation]<=9&&pile.rightmoveable(a)){
			a.row++;
		}
	}
	
}
