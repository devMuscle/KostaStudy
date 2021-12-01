package exception;

import java.util.Scanner;
	
public class ExceptionTest {
	public static void test(int num) {
		if( num == 0) {
			System.out.println("0으로는 나눌 수 없습니다");
		}else {
			
		}
//		try {
//			int mod = 99%num; //예외발생가능코드
//			System.out.println("99를" + num + " 으로 나눈 나머지값:" + mod);
//		}catch(ArithmeticException e) {
//			System.out.println("0으로는 나눌 수 없습니다");
//		}
	}
	public static void main(String[] args) {
		Scanner sc;
		sc = new Scanner(System.in);
		System.out.print("0이 아닌 숫자값을 입력하세요:");
		String line = sc.nextLine();
		try {
			//String값을 int로 전환
			int num = Integer.parseInt(line); //예외발생가능코드
			test(num);
		}catch(NumberFormatException e) {
			System.out.println("숫자값만 입력가능!");
		}
		System.out.println("프로그램의 끝입니다");
	} 
}
