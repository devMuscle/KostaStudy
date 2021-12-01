import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollectionTest {
	public static void test(Collection c) {
		c.add("One");
		c.add(new Integer("2")); //Object 타입으로 upcasting됨
		c.add(3); //int는 Object타입으로 upcasting불가
		          //Autoboxing됨.
		          //컴파일시 c.add( new Integer(3));로 변환됨
		c.add(new Integer(2));
		
		System.out.println("저장된 요소 개수:" + c.size());
		System.out.println("저장된 요소들");
		for(Object o: c) {
			System.out.println(o); //o.toString() 자동호출됨
		}
	}
	public static void test(Map m) {
		m.put("One", "first");
		m.put(new Integer(2), "second");
		m.put(3, "third");
		m.put(new Integer(2), "fourth");
		System.out.println("맵에 저장된 요소 개수:" + m.size());
		System.out.println("저장된 요소들");
		Set keys = m.keySet(); //키들
		for(Object k: keys) {
			Object v = m.get(k);
			System.out.println(k + ":" + v);
		}
	}
	public static void main(String[] args) {	
		Collection c;
		c = new ArrayList();
		test(c);
		
		c = new HashSet();
		test(c);
		
		Map m;
		m = new HashMap();
		test(m);
	}

}
