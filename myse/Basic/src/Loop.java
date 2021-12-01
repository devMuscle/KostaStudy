
public class Loop {

	public static void main(String[] args) {
		//while
		int num=0;
		/*
		num++; //1
		num++; //2
		num++; //3
		num++;num++;num++;num++;num++;
		*/
		//5번 num변수값을 1증가하기
		int i=0;
		while(i<5) {
			num++;
			i++; //반복횟수
			System.out.println("반복횟수:"+i+", num변수값:"+num);
		}
		System.out.println("---for.1---");
		int numa=0;
		for(int a=0; a<5;) {
			numa++;
			a++; //반복횟수
			System.out.println("반복횟수:"+a+", numa변수값:"+numa);
		}
		System.out.println("------------");
		
		num=1;
		
		System.out.println("5번반복하면서 num변수값을 3배증가하기");
		i=0;
		while(i<5) {
		num*=3;
		i++;
		System.out.println("반복횟수:"+i+", num변수값:"+num);
		}
		
		System.out.println("---for.2---");
		numa=1;
		for(int a=0; a<5;) {
			numa*=3;
			a++;
			System.out.println("반복횟수:"+a+", numa변수값:"+numa);
		}
		System.out.println("------------");
		
		num=1;
		i=0;
		int sum = 0;
		
//		sum += num; //sum=sum+num; sum=0+1;
//
//		num=2;
//		sum += num; //sum=sum+num; sum=1+2;
//		
//		num=3;
//		sum += num; //sum=sum+num; sum=3+2;
//		//						   0+1+2+3
		
		while(i<10) {
			sum += num;
			num++;
			i++;
		}
		System.out.println("1~10까지의 합:"+sum);
		
		
		System.out.println("---for.3---");
		int suma = 0;
		numa = 1;
		for(int a=0; a<10; a++) {
			suma += numa;
			numa++;
			//a++;
		}
		System.out.println("1~10까지의 합:"+suma);
		System.out.println("------------");
		
		
		num=1;
		sum=0;
		while(num<=10) {
			sum += num;
			num++;
		}
		System.out.println("1~10까지의 합:"+sum);
		
		System.out.println("---for.4---");
		for(numa=1, suma=0; numa<=10; numa++) {
			suma += numa;
		}
		System.out.println("1~10까지의 합:"+suma);
		System.out.println("------------");

		
		System.out.println("1~10까지의 숫자를 출력하시오");
		num=1;
		while(num<=10) {
			System.out.print(num++ +" ");
		}
		System.out.println();
		
		System.out.println("1~10까지의 숫자중 홀수를 출력하시오 1,3,5,7,9");
		num=1;
		while(num<=10) {
			System.out.print(num+" ");
			num+=2;
		}
		System.out.println();
		
		
		System.out.println("---for.5---");
		for(num=1; num<=10; num+=2) {
			System.out.print(num+" ");
		}
		System.out.println("\n------------");
		
		
		System.out.println();
		
		System.out.println("1~10까지의 숫자중 홀수의 합을 구하여 출력하시오 25");
		num=1;
		int oddSum=0;
		while(num<=10) {
			oddSum+=num;
			num+=2;
		}
		System.out.println("1~10까지의 숫자중 홀수의 합:"+oddSum);
		
		System.out.println("1부터 홀수 10개를 출력하시오 1,3,5,7,9,11,13,15,17,19");
		i=0;
		num=1;
		while(i<10) {
			System.out.print(num+" ");
			num+=2;
			i++;
		}
		System.out.println();
		
		//A, B, C, D, E, F, G를 출력하시오
		
		char c = 'A';
		while(c <= 'G') {
			System.out.print(c+" ");
			c++; // 'A'+1 => 'B'
		}
		System.out.println();
		
		/*
		 for(char ca='A'; ca<='G'; ca++){
		 	System.out.prtinln(ca);
		 */
		
		//키보드로 값 입력하기
		java.util.Scanner sc;
		sc = new java.util.Scanner(System.in);
		
		String readValue = "";
//		while(!readValue.equals("quit")) {
//			System.out.print("입력하세요(종료하려면 quit을 입력하세요):");
//			readValue = sc.nextLine();
//			System.out.println("입력하신 값은"+ readValue +"입니다");
//		}
		
//		System.out.println("---for.6---");
//		for( ; !readValue.equals("quit"); ) {
//			System.out.print("입력하세요(종료하려면 quit을 입력하세요):");
//			readValue = sc.nextLine();
//			System.out.println("입력하신 값은"+ readValue +"입니다");
//		}
//		System.out.println("-----------");
//		
//		readValue = "";
//		while(true) {
//			System.out.print("입력하세요(종료하려면 quit을 입력하세요):");
//			readValue = sc.nextLine();
//			if(readValue.equals("quit")) {
//				break;
//			}
//			System.out.println("입력하신 값은"+ readValue +"입니다");
//		}
		
//		System.out.println("---do while---");
//		do {
//			System.out.print("입력하세요(종료하려면 quit을 입력하세요):");
//			readValue = sc.nextLine();
//			System.out.println("입력하신 값은"+ readValue +"입니다");
//		}while(!readValue.equals("quit"));
//		
		
		//2중반복문
		//구구단의 4단의 *1~*9를 출력하시오
//		int dan=4;
//		num=1;
//		while(num<=9) {
//			System.out.print((dan*num) + "\t");
//			num++;
//		}
		
		int dan=2;
		while(dan <= 8) {
			num=1;
			while(num<=9) {
				//10의 배수들은 출력안함
				//1.
//				if(dan*num %10 != 0) {
//					System.out.print((dan*num) + "\t");
//				}
				if(dan*num % 10 == 0) {
					num++;
					continue;
				}
				System.out.print((dan*num) + "\t");	
				num++;
			}
			System.out.println();
			dan++;
		}
		
		System.out.println("피보나치수열값 10개를 출력하시오");
		i=0;
		int bb =1;
		int b=0;
		int current = bb + b;
		while(i<10) {
			System.out.print(current+"\t");
			bb = b;
			b = current;
			current = bb + b;
			i++;
		}
		System.out.println();
		
		//소수판별
		//
		
		//숫자 1부터의 합이 100미만인 최대숫자를 출력하시오
		sum = 0;
		num=1;
		while(sum+num<100) {
			sum+=num; 
			num++;  
		}
		System.out.println("최대숫자는 " + (num-1));
		
		//for(초기; 조건식; 증감식){}
		
	}
}
