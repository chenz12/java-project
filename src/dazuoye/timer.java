package dazuoye;

public class timer {
	public int speed;
	public long start;
	timer(int a){
		speed = a;
		start = System.nanoTime();
	}
	public boolean iscycled(){
		long temp = System.nanoTime();
		long dif = temp - start;
		if(dif >= 1.0/speed){
			return true;
		}
		return false;
	}
	public void pause(){
		start = System.nanoTime();
	}
	public void reset(int a){
		pause();
		speed = a;
	}
}
