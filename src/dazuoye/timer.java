package dazuoye;

public class timer {
	public double speed;
	public long start;
	public long dif;
	timer(double d){
		speed = d;
		start = System.nanoTime();
	}
	public boolean iscycled(){
		long temp = System.nanoTime();
		dif = temp - start;
		if(dif >= 1.0/(speed*Math.pow(10, -9))){
			start = System.nanoTime();
			return true;
		}
		return false;
	}
	public void pause(){
		start = System.nanoTime();
	}
	public void reset(){
		speed = 1;
		start = System.currentTimeMillis();
		dif = 0;
	}
}
