package override;

class Shape{
	protected double area;
	void makeArea() {
		System.out.println("도형의 면적을 계산한다");
	}
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
class Hexa extends Shape{
	
}
public class ShapeTest {
	public static void m(Shape s) {
		s.makeArea();
		s.printInfo();
	}
	public static void main(String[] args) {
		Circle c = new Circle(5); //반지름이 5인 원객체
		m(c);
		Rectangle r = new Rectangle(3,4); //가로3, 세로4인 사각형객체
		m(r);
		Triangle t = new Triangle(2,3); //밑변2, 높이3인 이등변삼각형객체
		m(t);
		
		Hexa h = new Hexa();
		m(h);
	}	
}