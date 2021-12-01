package inte;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 * 이벤트 프로그램 처리순서
 * 1.이벤트소스와 이벤트종류 결정 ex: 이벤트소스-btReady, 이벤트종류-ActionEvent //준비 버튼이 눌렸을때 이벤트발생
 * 2.이벤트핸들러 작성(이벤트발생했을때 할 일을 작성)
 *   class MyReadyHandler implements ActionListener{
 *   	public void actionPerformed(ActionEvent e){
 *   		System.out.prtinln("준비버튼이 클릭되었습니다");
 *   	}
 *   }
 * 3.이벤트소스와 이벤트핸들러를 연결
 *   MyReadyhandler eh = new MyReadyHandler();
 *   btReady.addActionListener(eh);
 * @author HongJiPyo
 *
 */
class MyReadyHandler implements ActionListener{
	JTextField jt;
	MyReadyHandler(JTextField jt){
		this.jt = jt;
	}
	public void actionPerformed(ActionEvent e){
		//System.out.println("준비버튼이 클릭되었습니다");
		jt.setText("준비버튼이 클리되었습니다");
	}
}

public class HorseRace {
	JFrame jf; //프레임(액자)
	JTextField jt; //한줄입력란
	JButton btStart; //달려버튼
	JButton btReady; //준비버튼
	
	/*내부(Inner)클레스
	 *외부(Outer)클레스의 mf와 method를 모두 사용할 수 있다
	 */
	/*class MyStartHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//jt에 "달리기시작되었습니다"
			jt.setText("달리기 시작되었습니다");
		}
	}*/
	
	public HorseRace() {
		jf = new JFrame("달리기"); //프레임(액자)생성
		jt = new JTextField(); //hint: 값설정하려면 jt.setText("hello");
							   //값얻기하려면 String s = jt.getText();
		btStart = new JButton("달려");
		btReady = new JButton("준비");
		Container c = jf.getContentPane(); //프레임뒷판종이
		c.setLayout(new GridLayout(3,1)); //프레임의 레이아웃을 3행 1열로 설정
		c.add(jt);
		c.add(btReady);
		c.add(btStart);
		
		MyReadyHandler eh = new MyReadyHandler(jt);
		btReady.addActionListener(eh);
		
		/*MyStartHandler eh1 = new MyStartHandler();
		btStart.addActionListener(eh1);
		*/
		//익명클래스 객체생성
//		btStart.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//jt에 "달리기시작되었습니다"
//				jt.setText("달리기 시작되었습니다");
//			}
//		});
		
		//람다식을 사용한 익명클래스 객체생성
		btStart.addActionListener((e)-> {
				//jt에 "달리기시작되었습니다"
				jt.setText("달리기 시작되었습니다");
		});
		jf.setSize(400, 200); //프레임의 크기설정 가로400픽셀, 세로200픽셀
		jf.setVisible(true); //화면에 보여주기	
	}
	public static void main(String[] args) {
		new HorseRace();
	}

}
