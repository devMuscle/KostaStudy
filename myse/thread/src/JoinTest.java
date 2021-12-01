class Gray extends Thread {
	int start, end;
	int sum;
	Gray(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public void run() {
		for(int i=start; i<=end; i++) {
			sum += i;
		}
	}
}

public class JoinTest {
	public static void main(String[] args) {
		Gray gray = new Gray(1, 10);
		Gray gray2 = new Gray(11, 20);
		
		gray.start();
		gray2.start();
		
		try {
			gray.join();
			gray2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("1~10까지의 합:" + gray.sum);
		System.out.println("11~20까지의 합:" + gray2.sum);
	}
}
