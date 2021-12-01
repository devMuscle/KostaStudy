package inheritance;
class Parent { //extends Object
	private int i;
	Parent(int i) {
		this.i = i;
	}
	public int hashCode() {
		return i;
	}
	/**
	 * 현재객체의 i값과 매개변수객체의 i값이 같으면 true반환,
	 * 그외의 경우 false를 반환한다
	 */
	public boolean equals(Object obj) {
//		if((obj instanceof Parent)==false) {
//			return false;
//		}
		if(obj instanceof Parent) {
			Parent p = (Parent) obj;
			if(this.i == p.i) {
				return true;
			}
		}
		/*if(this.i == obj.i) {
		 	return true;
		 */
		return false;
	}
	public String toString() {
		return "Parent의 Overriding된 toString()메서드입니다";
	}
}
public class ObjectTest {
	public static void test(Object obj1, Object obj2) {
		System.out.println("---test start---");
//		if(obj1 == null || obj2 ==null) {
//			System.out.println("매개변수 객체는 null이 될 수 없습니다");
//			return;
//		}
		if(obj1 != null) {
			System.out.println("obj1.hashCode()=" + obj1.hashCode());
		}
		if(obj2 != null) {
			System.out.println("obj2.hashCode()=" + obj2.hashCode());
		}
			System.out.println("obj1 == obj2 : " + obj1 == obj2);
		if(obj1 != null) {
			System.out.println("obj1.toString()=" + obj1.toString());
		}
		if(obj2 != null) {
			System.out.println("obj2.toString()=" + obj2.toString());
		}
		if(obj1 != null) {
			System.out.println("obj1.equals(obj2)=" + obj1.equals(obj2)); //false ==과 같음
		}
		System.out.println("---test end---");
	}

	public static void main(String[] args) {
		Object o1, o2;
		o1 = new Object();	o2 = new Object();
		test(o1, o2);
		
		Parent p1, p2;
		p1 = new Parent(10);	p2 = new Parent(10);
		test(p1, p2);
		test(p1, o1);
		test(p1, null);
		
		System.out.println("---System.out.println()---");
		System.out.println(1);
		System.out.println(false);
		System.out.println(o1);
		System.out.println("hello");
		System.out.println(p1); //p1.toString() 자동호출됨
		System.out.println(p1.toString());
		
		p1 = null;
		System.out.println(p1); //null
		System.out.println(p1.toString()); //NullPointerException발생
		}
}
