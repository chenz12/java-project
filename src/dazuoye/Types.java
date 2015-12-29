package dazuoye;

import java.awt.Color;
import java.util.*;

public class Types {
	private final static int[][] pattern = {
			{0x0f00, 0x4444, 0x0f00, 0x444},
			{0x04e0, 0x0464, 0x00e4, 0x04c4},
			{0x4620, 0x6c00, 0x4620, 0x6c00},
			{0x2640, 0xc600, 0x2640, 0xc600},
			{0x6220, 0x1700, 0x2230, 0x0740},
			{0x6440, 0x0e20, 0x44c0, 0x8e00},
			{0x0660, 0x0660, 0x0660, 0x0660}
			};
	public Color color;

	public int[] matrix;
	
	public int currentrotation;
	
	public int col;
	public int row;
	
	Types(int max, int mid, int min){
		this.color = new Color(max, mid, min);
		currentrotation = 0;
		col=0;
		row=0;
	}
	
	public static Types TypeI(){
		Types I = new Types(Tetris.MAXCOLOR, Tetris.MIDCOLOR, Tetris.MINCOLOR);
		I.matrix = pattern[0];
		return I;
	}
	
	public static Types TypeT(){
		Types T = new Types(Tetris.MIDCOLOR, Tetris.MINCOLOR, Tetris.MAXCOLOR);
		T.matrix = pattern[1];
		return T;
	}
	
	public static Types TypeS(){
		Types S = new Types(Tetris.MAXCOLOR, Tetris.MINCOLOR, Tetris.MIDCOLOR);
		S.matrix = pattern[2];
		return S;
	}
	
	public static Types TypeZ(){
		Types Z = new Types(Tetris.MIDCOLOR, Tetris.MAXCOLOR,Tetris.MINCOLOR);
		Z.matrix = pattern[3];
		return Z;
	}
	
	public static Types TypeL(){
		Types L = new Types(Tetris.MINCOLOR, Tetris.MINCOLOR, Tetris.MINCOLOR);
		L.matrix = pattern[4];
		return L;
	}
	
	public static Types TypeJ(){
		Types J = new Types(Tetris.MINCOLOR, Tetris.MIDCOLOR, Tetris.MAXCOLOR);
		J.matrix = pattern[5];
		return J;
	}
	
	public static Types TypeO(){
		Types O = new Types(Tetris.MINCOLOR, Tetris.MINCOLOR, Tetris.MIDCOLOR);
		O.matrix = pattern[6];
		return O;
	}
	
	

	
	public static int[][] converse(Types a){
		int i = a.matrix[a.currentrotation];
		System.out.println(Integer.toHexString(i));
		int[][] result = new int[4][4];
		for(int j=0;j<4;j++){
			for(int k=0;k<4;k++){
				result[3-j][3-k] = (i&(0x1 << (4*j+k)))>>(4*j+k);
			}
		}
		return result;
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
		if(a.col>0){
		a.col--;
		}
	}
	public void moveright(Types a){
		if(a.col<9){
			a.col++;
		}
	}
}
