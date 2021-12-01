import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.my.customer.vo.Customer;

public class ObjectIOTest {
	public static void write() {
		String fileName = "a.ser";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(fileName);
				oos = new ObjectOutputStream(fos);
				Date dt = new Date();
				oos.writeObject(dt); //객체직렬화
				Customer c = new Customer("id1", "p1", "n1", "a1");
				oos.writeObject(c);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		
	}
	public static void read() {
		String fileName = "a.ser";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject(); //객체역직렬화
			System.out.println(obj); //obj.toString() 자동호출
			
			obj = ois.readObject();
			Customer c = (Customer)obj;
			c.printInfo();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
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
