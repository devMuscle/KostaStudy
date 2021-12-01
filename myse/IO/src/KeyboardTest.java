import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KeyboardTest {

	public static void main(String[] args) {
		InputStream is;
		is = System.in;
//		바이트단위 스트림		
//		try {
//			int readValue = is.read();
//			System.out.println("키보드로 읽어온 값:" + readValue + ", 문자값:" + (char)readValue);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		문자단위 스크림으로 가공
		
//		InputStreamReader isr = new InputStreamReader(is);
//		
//			try {
//				//반복하여 키보드로 입력된 값을 출력하기
////				while(true) {
////				int readValue = isr.read();
////				if(readValue == -1) {
////					break;
////				}
//				int readValue = -1;
//				while((readValue = isr.read() ) != -1) {
//					System.out.println("키보드로 읽어온 값:"+ readValue+", 문자값:" + (char)readValue);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		
//		Scanner라이브러리 사용 : 문자열읽기편리, 기본형으로 읽기 편리
		Scanner sc = new Scanner(is);
		String line = sc.next(); //sc.nextLine();
		System.out.println(line);
	}

}
