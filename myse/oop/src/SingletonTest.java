class Single {
	int i;
	private static Single s = new Single();
	private Single() {
		i = 10;
	}
	public static Single getInstance() {
		return s;
	}
}
public class SingletonTest {
	public static void m1() {
		Single s1 = Single.getInstance();
		System.out.println("in main: s1정보값=" + s1);
		s1.i = 99;
	}
	public static void m2() {
		Single s1 = Single.getInstance();
		System.out.println("in main: s1정보값=" + s1);
		s1.i--;
	}
	public static void main(String[] args) {
		//new Single();
		//Single.s = null; 
		Single s1 = Single.getInstance();
		System.out.println(s1.i); //10
		System.out.println("in main: s1정보값=" + s1);
		m1();
		System.out.println(s1.i); //99
		m2();
		System.out.println(s1.i); //98
	}
	
}

