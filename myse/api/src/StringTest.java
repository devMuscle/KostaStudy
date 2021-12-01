import java.util.Scanner;
import java.util.StringTokenizer;

public class StringTest {

	public static void main(String[] args) {
		String s = "가ABC";
		char c = s.charAt(0);
		System.out.println(c); //가
		
		byte[] bArr = s.getBytes(); //6bytes
		for(byte b : bArr) { //가의 바이트개수: 3bytes
			System.out.println(b);
		}
		int index = s.indexOf("AB");
		System.out.println(index); //1
		index = s.indexOf('X');
		System.out.println(index); //-1, 없는값을 입력하면 -1반환
		
		int size = s.length();
		System.out.println(size); //문자열길이 : 4
		
		int beginIndex = 1;
		int endIndex = 3;
		String substr = s.substring(beginIndex, endIndex); //Index 1뿌터 3-1까지의 부분문자열
		System.out.println(substr); //AB
		
		
		//String palindrom = "토마토"; //"ABBA" 
		System.out.println("Palindrom문자열 판별하기");
		Scanner sc = new Scanner(System.in);
		String palStr = sc.nextLine(); //입력값이 "경기도 성남시" 엔터인 경우 -> "경기도 성남시"
		//sc.next(); -> "경기도" 반환
		//TODO 결과가 Palindrom문자열 입니다 또는, Palindrom문자열이 아닙니다를 출력한다.
		int palStrSize = palStr.length();
		int halfSize = palStrSize/2;
		
		int i = 0;
		for(; i<halfSize; i++) {
			int palStrEndIndex = palStrSize-1 -i;
			char c1 = palStr.charAt(i);
			char c2 = palStr.charAt(palStrEndIndex);
			if(c1 != c2) {
				break;
			}
		}
		if(i == halfSize) {
			System.out.println("Palindrom문자열 입니다");
		}else {
			System.out.println("Palindrom문자열이 아닙니다");
		}
		
		System.out.println("--String의 split()메서드--");
		String splitStr = "90:100:80"; //"국어점수:수학점수:영어점수"
		System.out.println("점수들=" + splitStr);
		String delim = ":"; //구분자
		String[] arr = splitStr.split(delim, 3);
		for(String score:arr) {
			System.out.println(score);
		}
		
		splitStr = "90::80";
		System.out.println("점수들=" + splitStr);
		arr = splitStr.split(delim, 3);
		for(String score:arr) {
			System.out.println(score);
		}
		
		System.out.println("--StringTokenizer--");
		String stStr = "90:100:80";
		System.out.println("점수들=" + stStr);
		StringTokenizer st = new StringTokenizer(stStr, delim);
		while(st.hasMoreTokens()) {
			String score = st.nextToken();
			System.out.println(score);
		}
		
		stStr = "90::80";
		System.out.println("점수들=" + stStr);
		st = new StringTokenizer(stStr, delim);
		while(st.hasMoreTokens()) {
			String score = st.nextToken();
			System.out.println(score);
		}

		
		String urlStr = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%9E%90%EB%B0%94";
		//urlStr.~~~()활용하여 문자열의 protocol값을 추출하시오
		int index1 = urlStr.indexOf(":");
		if(index1 > -1) {
			String protocol = urlStr.substring(0, index1); //0 ~ index1-1
			System.out.println(protocol);
		}
		
		delim = "\\?";
		arr = urlStr.split(delim, 2);
		for(String urlS : arr) {
			System.out.println(urlS);
		}
	} 
}
