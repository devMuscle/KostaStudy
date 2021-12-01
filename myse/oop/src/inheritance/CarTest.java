package inheritance;

public class CarTest {

	public static void main(String[] args) {
		Car c = new Car();
		for(int i=0; i<10; i++) {
			c.speedUp();
		}
		c.printInfo();
		
		Truck t = new Truck();
		t.setWheelCount(12);
		t.speedUp();
		t.printInfo();
	}
}
