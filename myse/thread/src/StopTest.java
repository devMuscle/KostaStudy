class Stop extends Thread {
	boolean flag = true;
	public void run() {
		while(flag) {
			System.out.println("run...");
		}
	}
}
class Stop1 extends Thread {
	int max = 10000;
	public void run() {
		for(int i=0; i<max; i++) {
			System.out.println("run:" + i);
		}
	}
}

public class StopTest {
	public static void main(String[] args) {
//		Stop s = new Stop();
//		s.start();
//		try {
//			Thread.sleep(1000);
//			s.flag = false;
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		Stop1 s1 = new Stop1();
		s1.start();
		s1.max = 0;
	}

}
