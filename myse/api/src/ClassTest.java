import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ClassTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("런타임 다이너믹 클래스로드");
		System.out.print("패키지명.클래스명을 정확히 입력하세요:");
		String className = sc.nextLine(); //입력받은 클래스이름
		
		try {
			Class clazz = Class.forName(className);
			System.out.println("로드된 실제클래스이름은" + clazz.getName());
			Field[] fields = clazz.getDeclaredFields();//fields정보얻기
			System.out.println("--로드된 클래스의 멤버필드들---");
			for(Field f: fields) {
				System.out.println(f.getName());
			}
			System.out.println("--로드된 클래스의 멤버메서드들---");
			Method[] method = clazz.getDeclaredMethods(); //methods정보얻기
			for(Method m:method) {
				System.out.println(m.getName());
			}
			//객체생성
			Object o = clazz.newInstance();
			
			//toString메서드찾기
			Method toStringMethod = 
					clazz.getDeclaredMethod("toString");
			//toString메서드호출
			Object result = toStringMethod.invoke(o, null);
			//결과 출력
			System.out.println("toString()결과:" + result);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
