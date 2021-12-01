import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {
	public static void readTest() {
		/*
		 * 1.리소스결정 : a.txt파일
		 * 2.노드스트림결정 : 문자단위스트림-FileReader 
		 */
		String fileName = "a.txt"; //경로가 정확히 설정되어 있지 않음 - 현재경로에 있는 파일
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
//1문자씩 읽기			
//			int readValue = -1;
//			while((readValue = fr.read()) !=-1) {
//			System.out.print((char)readValue);
//			}	
			char[] cbuf = new char[5];
			int offset = 0;
			int length = 5;
			while(true) {
				
				int size = fr.read(cbuf, offset, length);
				if(size == -1) {
					break;
				}
//				for(int i=0; i<size; i++) {
//					char c = cbuf[i];
//					System.out.println(i + ":" + c);
//				}
				String str = new String(cbuf, 0, size);
				System.out.println(str);
				System.out.println("실제 읽어온 문자수: " + size);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeTest() {
		/*
		 * a.txt파일에 바이트단위로 쓰기
		 * 1. 목적지결정 : a.txt파일
		 * 2. 노드스트림결정 : FileOutputStream
		 */
		String fileName = "a.txt";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);//새파일만들기
			fos.write(65);// 'A'
			fos.write(97);// 'a'
			fos.write(13); fos.write(10); //enter
			fos.write(49); //'1'
			
			//fos.write(44032); //16bit unicode '가'
			//fos.write(234); fos.write(176); fos.write(128); // utf-8 '가'
			byte[] buf = "가".getBytes();
			fos.write(buf);
			fos.write("안녕하세요".getBytes());
			
			fos.write('A');
			fos.write('a');
			fos.write('1');
			//fos.write
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeTest1() {
		/*
		 * 문자단위로 a.txt파일에 쓰기
		 */
		String fileName = "a.txt";
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName); //새파일 만들기
			fw.write("hello");
			fw.write("\n");
			
			fw.flush();
			fw.write("자바입출력");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		//writeTest();
		writeTest1();
		readTest();
	}

}
