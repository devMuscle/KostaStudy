public class CircleTest {
	public static void main(String[] args) {
		Circle c;
		c = new Circle();
		c.radius = 3.0;
		c.makeArea();
		System.out.println("반지름이" + c.radius + "인 원의 면적은" + c.area + "입니다");
	}

}
