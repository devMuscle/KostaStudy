import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

	public static void main(String[] args) {
		//파일복사붙여넣기 : a.txt파일을 읽어서 acopy.txt파일에 쓰기
		String originFileName = "a.txt"; //원본
		String destFileName = "acopy.txt";//대상
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(originFileName);
			fos = new FileOutputStream(destFileName);
//			int readValue = -1;
//			while((readValue = fis.read()) != -1) {
//				fos.write(readValue);
//			}
			byte []buf = new byte[100];
			while(true) {
				int count = fis.read(buf, 0, buf.length);
				if(count == -1) {
					break;
				}
				fos.write(buf, 0, count);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
