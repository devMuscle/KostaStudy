import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {

	public static void main(String[] args) throws IOException {
		File dir;
		dir = new File("."); //현재경로
		
		System.out.println("dir.getName():" + dir.getName()); //.
		System.out.println("dir.getAbsoluteFile():" + dir.getAbsoluteFile()); //D:\230\myse\io\.
		System.out.println(dir.getAbsolutePath()); //D:\230\myse\io\.
		System.out.println(dir.getCanonicalPath()); //D:\230\myse\io
		
		System.out.println(dir.isDirectory()); //true
		String[] fileNames = dir.list();
		for(String name : fileNames) {
			System.out.println(name);
		}
		System.out.println("--------");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		File[] files = dir.listFiles(); //하위 디렉토리, 가지고있는 파일 정보들
		for(File f1: files) {
			Date dt = new Date(f1.lastModified());
			System.out.print(sdf.format(dt));
			System.out.print("\t");
			if(f1.isDirectory()) { //하위폴더인경우
				//System.out.print("<DIR> ");
				System.out.printf("%s  ", "<DIR>");
			}else { //파일인경우
//				System.out.print(f1.length());
//				System.out.print("  ");
				System.out.printf("**%-10%d  **  ", f1.length());
			}
			System.out.println(f1.getName());
		}
		System.out.println("--------");
		
		File f;
		f = new File(".\\src\\FileIOTest.java");
		System.out.println(f.exists()); //true
		System.out.println("f.length():" + f.length()); //2747byte
		System.out.println("f.canWrite():" + f.canWrite()); //true
		System.out.println(new Date(f.lastModified())); //최종 사용 시간
		
	}

}
