import java.util.ArrayList;
import java.util.List;

public class GenericTest {

	public static void main(String[] args) {
		List<String> list; //element generiz
		list = new ArrayList<>();
		list.add("HELLO");
		list.add(new String("제네릭"));
		//list.add(new Integer(1));
		String s = list.get(0);
		for(String s1: list) {
			System.out.println(s1);
		}
	}

}
