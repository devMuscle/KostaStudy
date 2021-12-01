package inheritance;

public class Truck extends Car{
	private int wheelCount; //바퀴수
	// public Truck() { //default constructor
	// super();
	//}
	public void setWheelCount(int wheelCount) {
		this.wheelCount = wheelCount;
	}
	public int getWheelCount() {
		return wheelCount;
	}
	public void printInfo() {
		System.out.println("속도:" + speed + ", 바퀴수:" + wheelCount);
	}
}