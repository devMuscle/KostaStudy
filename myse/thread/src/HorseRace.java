import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 * 이벤트 프로그램 처리순서
 * 1.이벤트소스와 이벤트종류결정 ex: 이벤트소스-btReady,이벤트종류-ActionEvent
 * 2.이벤트핸들러 작성(이벤트발생했을때 할 일을 작성)
 *   class MyReadyHandler implements ActionListener{
 *     public void actionPerformed(ActionEvent e){
 *       System.out.println("준비버튼이 클릭되었습니다");
 *     }
 *   }  
 * 3.이벤트소스와 이벤트핸들러를 연결
 *   MyReadyHandler eh = new MyReadyHandler();
 *   btReady.addActionListener(eh);
 * @author KOSTA
 *
 */

class MyReadyHandler implements ActionListener{
	JTextField jt;
	Horse[] horses;
	MyReadyHandler(JTextField jt, Horse[] horses){
		this.jt = jt;
		this.horses = horses;
	}
	public void actionPerformed(ActionEvent e){
		//System.out.println("준비버튼이 클릭되었습니다");
		jt.setText("준비버튼이 클릭되었습니다");
		for(Horse h: horses) {
			h.x = 0;
			h.repaint(); // ->canvas 지워지고 paint()호출됨
		}
	}
}  
class Horse extends java.awt.Canvas implements Runnable{
	private String name;
	int x = 10;
	int y = 10;
	Horse(String name){
		this.name = name;
	}
	@Override
	public void paint(Graphics g) {
		
		g.drawString(name, x, y);
	}
	
	public void run() {
		for(int i=0; i<50; i++) {
			x += 10;
			repaint();
			try {
				long millis = (long)(Math.random()*500); //0.0<= r <500.0
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
}
public class HorseRace {
	JFrame jf; //프레임(액자)
	JTextField jt; //한줄입력란
	Horse[] horses;
	JButton btStart; //달려버튼
	JButton btReady; //준비버튼
	
	public HorseRace() {
		jf = new JFrame("달리기");//프레임(액자)생성
		jt = new JTextField(); //hint: 값설정하려면 jt.setText("hello"); 
		                       //값얻기하려면 String s = jt.getText();
		//---------
		String []names = {"거북이", "토끼", "우사인볼트"}; 
		horses = new Horse[3];
		for(int i=0; i<horses.length; i++) {
			horses[i] = new Horse(names[i]);
		}
		//----------
		
		btStart = new JButton("달려");
		btReady = new JButton("준비");
		Container c = jf.getContentPane(); //프레임뒷판
		c.setLayout(new GridLayout(6,1)); //프레임의 레이아웃을 6행1열로 설정
		c.add(jt);
		for(Horse h: horses) {
			c.add(h);
		}
		c.add(btReady);
		c.add(btStart);
		
		MyReadyHandler eh = new MyReadyHandler(jt, horses);
		btReady.addActionListener(eh);
		
		/*MyStartHandler eh1 = new MyStartHandler();
		btStart.addActionListener(eh1);
		*/
		//익명클래스 객체생성
//		btStart.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//jt에 "달리기시작되었습니다"설정
//				jt.setText("달리기 시작되었습니다");
//			}
//		});
		
		//람다식을 사용한 익명클래스 객체생성
		btStart.addActionListener((e)-> {
			//jt에 "달리기시작되었습니다"설정
			jt.setText("달리기 시작되었습니다");
			for(Horse h: horses) {
				//h.start(); //스레드시작한다
				new Thread(h).start();
				
//				for(int i=0; i<30; i++) {
//					h.x += 10;
//					h.repaint();
			}
//			}
		});

		jf.setSize(400, 200); //프레임의 크기설정 가로400픽셀, 세로200픽셀
		jf.setVisible(true);//화면에 보여주기
	}
	public static void main(String[] args) {
		new HorseRace();
	}
}
