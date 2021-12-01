import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
public class DataIOTest {
	
	public static void write() {
		String fileName = "a.dat";
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			dos.writeInt(10);
			dos.writeBoolean(true);
			dos.writeDouble(12.3);
			dos.writeUTF("가나다");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	
	public static void read() {
		String fileName = "a.dat";
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			int i = dis.readInt();
			boolean b = dis.readBoolean();
			double d = dis.readDouble();
			String s = dis.readUTF();
			System.out.println("int값=" + i);
			System.out.println("boolean값=" + b);
			System.out.println("double값=" + d);
			System.out.println("utf-8값=" + s);
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dis != null) {
				try {
					dis.close();
			} catch (IOException e) {
					e.printStackTrace();
				}
			}if(fis != null) {
				try {
					fis.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}



	public static void main(String[] args) {
			write();
			read();
		}

}
