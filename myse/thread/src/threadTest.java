import java.text.SimpleDateFormat;
import java.util.Date;

class Video extends Thread {
	@Override
	public void run() {
		for(int i=1; i<=100; i++) {
			System.out.println("비디오" + i);
		}
	}
}
class Sound extends Thread {
	@Override
	public void run() {
		for(int i=1; i<=100; i++) {
			System.out.println("사운드" + i);
		}
	}
	
}
class Timer extends Thread {
	//TODO 이 스레드가 할일은 현재시간을 100번 출력한다
	//Date, Calendar, SimpleDateFormat, printf...
	private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println(sdf.format(new Date()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class threadTest {
	public static void m() {
		Thread t = Thread.currentThread(); //현재사용중인 스레드 얻기
		System.out.println("m()를 호출한 thread:" + t.getName());
	}
	
	public static void main(String[] args) {
		Thread t = Thread.currentThread();//현재사용중인 스레드 얻기
		System.out.println("main()를 호출한 thread:" + t.getName());
		//Video스레드객체생성
		Video v = new Video();
		Sound s = new Sound();
		Timer timer = new Timer();
		
		//스레드 시작
		//v.run();
		v.start();
		s.start();
		timer.start();
		
		m();
	}

}
