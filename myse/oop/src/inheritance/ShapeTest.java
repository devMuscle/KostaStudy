package inheritance;
class Shape{
	protected double area;
//	void makeArea() {
//		
//	}
	void printInfo() {
		System.out.println("면적은 " + area + "입니다");
	}
}
class Circle extends Shape{
	private int radius;
	Circle(int radius){
		this.radius = radius;
	}
	void makeArea() {
		area = Math.pow(radius, 2)*Math.PI;
	}
	void printInfo() {
		System.out.print("반지름이 " + radius + "인 원의 ");
		super.printInfo();
	}
}
class Rectangle extends Shape{
	private int height;
	private int width;
	Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	Rectangle(int side) {
		this(side, side);
	}
	void makeArea() {
		area = width*height;
	}
	void printInfo() {
		System.out.print("가로" + width + ", 세로" + height + "인 사각형의 ");
		super.printInfo();
	}
}
class Triangle extends Shape{
	private int height;
	private int base;
	Triangle(int base, int height) {
		this.base = base;
		this.height = height;
	}
	void makeArea() {
		area = base*height / 2;
	}
	void printInfo() {
		System.out.print("밑변" + base + ", 높이" + height + "인 이등변삼각형의 ");
		super.printInfo();
	}
}
public class ShapeTest {
	public static void main(String[] args) {
		Circle c = new Circle(5); //반지름이 5인 원객체
		Rectangle r = new Rectangle(3,4); //가로3, 세로4인 사각형객체
		Triangle t = new Triangle(2,3); //밑변2, 높이3인 이등변삼각형객체
		c.makeArea(); //원의 면적을 구한다
		r.makeArea(); //사각형의 면적을 구한다
		t.makeArea(); //삼각형의 면적을 구한다
		
		c.printInfo(); //"반지름이 5인 원의 면적은 ~입니다" 출력한다
		r.printInfo(); //"가로3, 세로4인 사각형의 면적은 ~입니다" 출력한다
		t.printInfo(); //"밑변2, 높이 3인 이등변삼각형의 면적은 ~입니다" 출력한다
		
		Shape s;
		s = c; //부모 변수에 자식 객체가 들어간다 = upcasting //c가 참조하는것과 같은 포인터지만, Circle영역까진 참조하지 못하고, Shape영역만 참조할 수 있다 
		//s.makeArea();
		s.printInfo();
		
		Shape []arr = new Shape[3];
		arr[0] = c;
		arr[1] = r;
		arr[2] = t;
		for(int i=0; i<arr.length; i++) {
			arr[i].printInfo();
		}	
	}
}

