package dazuoye;

import java.awt.Color;

public class Add {
	int[] bound;
	Types[][] main;
	
	Add(){
		main = new Types[20][10];
	}
	
	int update(){
		int res=0;
		for(int i=0;i<20;i++){
			if(checklines(i)){
				res++;
			}
		}
		return res;
	}
	
	boolean checklines(int line){
		
			int temp=0;
			for(int j=0;j<10;j++){
				if(main[line][j]!=null)
				temp ++;
			}
			if(temp==10){
				for(int i=line;i>0;i--){
					for(int j=0;j<10;j++){
						main[i][j] = main[i-1][j];
					}
				}
				return true;
			}
		
		return false;
	}
	boolean contains(){
		for(int i=0;i<20;i++){
			for(int j=0;j<10;j++){
				if(main[i][j]!=null){
					return true;
				}
			}
		}
		return false;
	}
	
	void addtype(Types a, int x, int y){
		int[][] matrix = Types.converse(a);
		for(int i=a.col+a.upindex[a.currentrotation];i<=a.col+3-a.lowindex[a.currentrotation];i++){
			for(int j=a.row+a.leftindex[a.currentrotation];j<=a.row+3-a.rightindex[a.currentrotation];j++){
				if(matrix[i-a.col][j-a.row]==1){
					set(a, i, j);
				}
			}
		}
		/*for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(matrix[i][j]==1){
					main[x+i][y+j]=a;
				}
			}
		}*/
	}
	
	void set(Types a, int x, int y){
		main[x][y] = a;
	}
	
	boolean place(Types a, int x, int y){
		int[][] matrix = Types.converse(a);
		if(x<0){
			for(int i=0;i<=x+3;i++){
				for(int j=y+a.leftindex[a.currentrotation];j<=y+3-a.rightindex[a.currentrotation];j++){
					if(matrix[i-x][j-y]==1&&main[i][j]!=null){
						return false;
					}
				}
			}
			return true;
		}
		else if(x+3-a.lowindex[a.currentrotation]==20){
			return false;
		}
		else{
		for(int i=x+a.upindex[a.currentrotation];i<=x+3-a.lowindex[a.currentrotation];i++){
			for(int j=y+a.leftindex[a.currentrotation];j<=y+3-a.rightindex[a.currentrotation];j++){
				if(matrix[i-x][j-y]==1&&main[i][j]!=null){
					return false;
				}
			}
		}
		return true;
		}
		/*
		int j;
		if(y<0){
			j = 1;
		}
		else {j=0;}
		if(x+3-a.lowindex[a.currentrotation]>=19)
		{
			return false;
		}
		for(int i=0;i<4;i++){
			for(;j<4;j++){
				if(matrix[i][j]==1&&main[3-a.lowindex[a.currentrotation]+x+i][y+j]!=null){
					return false;
				}
			}
		}
		return true;*/
	}
	
	boolean leftmoveable(Types a){
		if(a.row+a.leftindex[a.currentrotation]>0){
		return place(a, a.col, a.row-1);}
		return false;
	}
	boolean rightmoveable(Types a){
		if(a.row-a.rightindex[a.currentrotation]+3<9){
		return place(a, a.col, a.row+1);
		}
		return false;
	}
	
	
}

