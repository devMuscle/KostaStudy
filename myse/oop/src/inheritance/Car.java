package inheritance;

public class Car {
	//private int speed;
	//public Car() {} default Constructor
 	protected int speed;
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void speedUp() {
		speed++;
	}
	public void speedDown() {
		if(speed > 0) {
			speed--;
		}
	}
	public void printInfo() {
		System.out.println("속도:" + speed);
		//System.out.println("바퀴수 : " + getWheelCount()); //Child Method호출 안됨
	}
}