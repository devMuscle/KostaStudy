public class Condition {
	public static void main(String[] args) {
		//if(조건문) {}  if(논리형){}
		int gend = 1;
		if(gend==1 || gend==3) {
			System.out.println("남자");
		}
		
		//if else
		if(gend==1 || gend==3) {
			System.out.println("남자");
			//:
			//:
		}else {
			System.out.println("여자");
			//:
		}
		
		if(gend==1 || gend==3)
			System.out.println("남자");
		else
			System.out.println("여자");
		
		//if else if
		int hour = 16;
		if(hour > 12 && hour <=15) {
			System.out.println("점심");
		}else if(hour>=6 && hour<12) {
			System.out.println("아침");
		}else {
			System.out.println("저녁");
		}
		
		//년도에 해당하는 12지를 출력하시오
		//년도를 12로 나눈 나머지가 0이면 원숭이, 나머지가 1이면 닭, 나머지가 2이면 개, 나머지가 3이면 돼지, 4 쥐, 5 소, 6 호랑이, 7 토끼, 8 용, 9 뱀, 10 말, 11 양
		//
		
		int year = 2021;
		int ha = year%12;
		
		
		if(ha%12==0) {
			System.out.println("원숭이의 해");
		}else if(ha==1) {
			System.out.println("닭의 해");
		}else if(ha==2) {
			System.out.println("개의 해");
		}else if(ha==3) {
			System.out.println("돼지의 해");
		}else if(ha==4) {
			System.out.println("쥐의 해");
		}else if(ha==5) {
			System.out.println("소의 해");
		}else if(ha==6) {
			System.out.println("호랑이의 해");
		}else if(ha==7) {
			System.out.println("토끼의 해");
		}else if(ha==8) {
			System.out.println("용의 해");
		}else if(ha==9) {
			System.out.println("뱀의 해");
		}else if(ha==10) {
			System.out.println("말의 해");
		}else if(ha==11) {
			System.out.println("양의 해");
		}
		
		int sal1 = 41; //1월 급여
		int sal2 = 25;
		int sal3 = 25;
		int sal4 = 33;
		int sal5 = 44;
		int sal6 = 55; //6월 급여
		//상반기 급여의 평균을 계산하시오. 평균급여값은 소숫점이하값을 정확히 갖는다
		int sum = sal1 + sal2 + sal3 + sal4 + sal5 + sal6; 
		double avg = (double)sum/6;
		System.out.println("상반기 총 급여=" +sum);
		System.out.println("상반기 평균급여="+avg);
		
		
		if(avg>45) {
			System.out.println("A+등급");
		}else if(avg>30 && avg<=45) {
			System.out.println("A등급");
		}else if(avg>=15 && avg<=30) {
			System.out.println("B등급");
		}else if(avg<15) {
			System.out.println("C등급");
		}
		
		String s1 = new String("가나다");
		String s2 = new String("가나다");
		System.out.println(s1==s2); //false , String은 equals로 비교해야한다
		System.out.println(s1.equals(s2)); //true
		
		double r = Math.random(); // 0.0<= r <1.0
		System.out.println(r);
		int intR = (int)(r*3) + 1; // 1<= (int)(r*3) + 1 <4
		System.out.println(intR); // 1<= intR < 4
		
		
		//컴퓨터가 낸 값은 intR값이다. 1인경우는 가위, 2인경우는 바위, 3인경우 보
		//사용자가 낸값이 1인경우는 가위, 2인경우는 바위, 3인경우 보
		int user = 1; //가위
		
//		이기는 경우 user:1(가위)  intR :3(보)
//		             :2(바위)       :1(가위)
//		             :3(보)         :2(바위)
		if(intR == user) {
			System.out.println("비겼습니다");
		}
		else {
//			if((user==1 && intR ==3) || (user==2 && intR==1) || (user==3 || intR==2)) {
//				System.out.println("이겼습니다.");
//			}
//			else {
//				System.out.println("졌습니다.");
//			}
			if(user -(intR%3) ==1) {
				System.out.println("이겼습니다.");
			}else {
				System.out.println("졌습니다.");
			}
		}
		
		//switch(표현식){}  표현식의 자료형은 byte, short, int, char, String만 가능하다. long, float, double은 사용불가
		switch(ha) {
			case 0:
				System.out.println("원숭이");
				break;
			case 5:
				System.out.println("소");
				break;
			case 1:
				System.out.println("닭");
				break;
			case 2:
				System.out.println("개");
				break;
			default:
				System.out.println("그 외의 동물");
		}

	}

}
