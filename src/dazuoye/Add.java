package dazuoye;

public class Add {
	int[] bound;
	int[][] main;
	
	Add(){}
	
	int checklines(){
		int res=0;
		
		for(int i=0;i<21;i++){
			int temp=0;
			for(int j=0;j<11;j++){
				temp += main[i][j];
			}
			if(temp==10){
				res ++;
			}
		}
		return res;
	}
	
	void addtype(Types a, int x, int y){
		int[][] matrix = Types.converse(a);
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(matrix[i][j]==1){
					main[x+i][y+j]=1;
				}
			}
		}
	}
	
	boolean place(Types a, int x, int y){
		int[][] matrix = Types.converse(a);
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(matrix[i][j]==1&&main[x+i][y+j]==1){
					return false;
				}
			}
		}
		return true;
	}
	
	boolean leftmoveable(Types a){
		if(a.row>0){
		return place(a, a.col, a.row-1);}
		return false;
	}
	boolean rightmoveable(Types a){
		if(a.row<9){
		return place(a, a.col, a.row+1);
		}
		return false;
	}
	
	
}
