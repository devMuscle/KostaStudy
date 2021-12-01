class B {
	int iv; //인스턴스변수(nonstatic 변수) , 객체 생성시 초기화(Heap 영역)
	static int sv; //클래스변수(static 변수), 클래스가 로드가 되면 초기화(클래스 영역)
	
	void im() { //인스턴스 메서드
		sm();
		System.out.println(iv);
		System.out.println(sv);
	}
	static void sm() { // static method
		//im(); //this.im(); static메소드 안에서는 this. 사용이 불가능(인스턴스 변수, 인스턴스 메소드 사용 불가능) - 객체가 생성이 안됨
		//System.out.println(iv);
		System.out.println(sv);
	}
}
/**
 * static변수 : 객체와 무관한 객체들간의 공유변수로 사용됨
 * 			   클래스로드후 자동초기화된다
 * 			   사용법 - 객체참조변수.static변수
 * 					  클래스명.static변수
 * static메서드 : 객체와 무관한 기능제공
 * 				사용법 - 객체참조변수.static 메서드();
 * 					   클래스명.static 메서드();
 * 					   static메서드 내부에서는 this사용못함, 인스턴스메서드 사용못함, 인스턴스변수 사용못함
 * 	ex)Math.random();            
 * 
 * @author HongJiPyo
 *
 */
public class StaticTest {
	public static void main(String[] args) {
		B b1 = new B();
		B b2 = new B();
		b1.iv = 10;
		System.out.println(b1.iv); //10
		System.out.println(b2.iv); //0
		System.out.println(b1.sv); //0
		System.out.println(B.sv); //0
		
		b1.sv++;
		System.out.println(B.sv);//1
		System.out.println(b2.sv);//1
		
		b1.im();
		b1.sm();
		//B.im();
		B.sm();
	}

}

