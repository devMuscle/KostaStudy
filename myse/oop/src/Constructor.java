class A{
	int iv;
	/*기본생성자(default constructor)
	//컴파일시에 생성자가 없으면 매개변수없는 생성자를 자동 포함시킨다
	//다음과 같은 생성자코드를 포함시킨다
	//A(){}
	 */

	A(){}
	A(int iv){
		this.iv = iv;
	}
}
class Rectangle{
	int width;
	int height;
	int area;
	Rectangle(){}
	Rectangle(int vertical, int horizontal){
		this.width = vertical;
		this.height = horizontal;
	}
	Rectangle(int length){
		//this.width = length;
		//this.height = length;
		//System.out.println("생성자");
		this(length, length); //this생성자 호출: 현재객체가 갖는 다른 생성자를 호출한다
							  //			  생성자에서만 호출할 수 있다
							  // 			  생성자의 처음에 와야한다
	}
	void makeArea() {
		area = width*height;
	}
	void printInfo() {
		System.out.println("가로" + width + ", 세로" + height + "인 사각형의 면적은" + width + "입니다");	
	}
}
public class Constructor {
	public static void main(String[] args) {
		A a1 = new A();
		a1.iv = 1;

		A a2 = new A();
		a2.iv = 2;

		A a3 = new A();
		a3.iv = 3;

		A a4 =new A(4);
		
		Rectangle r1 = new Rectangle(3, 4); //가로길이가 3, 세로길이가 4인 사각형 객체
		Rectangle r2 = new Rectangle(5); //가로길이와 세로길이가 5인 사각형객체
		r1.makeArea(); //사각형의 면적을 계산한다
		r1.printInfo(); //"가로3, 세로4인 사각형의 면적은 12입니다"를 출력한다
		
		r2.makeArea();
		r2.printInfo();
	}

}