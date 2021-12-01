class Share{
	int i;
	
	public void pop() {
		for(int i=0; i<100; i++) {
			synchronized(this) {
				if(this.i == 0) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.print("before pop:" + this.i);
				this.i--;
				System.out.println(", after pop:" + this.i);
			}
		}
	}
	
	public void push() {
		for(int i=0; i<100; i++) {
			synchronized(this) {
				this.notify();
				System.out.print("before push:" + this.i);
				this.i++;
				System.out.println(", after push:" + this.i);
			}
		}
	}
}

class Pop extends Thread{
	Share s;
	
	Pop(Share s){
		this.s = s;
	}
	
	public void run() {
		s.pop();
	}
}

class Push extends Thread{
	Share s;
	
	Push(Share s){
		this.s = s;
	}
	
	public void run() {
		s.push();
	}
}

public class ShareTest {
	public static void main(String[] args) {
		Share s;
		s = new Share();
		
		Pop pop;
		pop = new Pop(s);
		
		Push push;
		push = new Push(s);
		
		pop.start();
		push.start();
	}
}
