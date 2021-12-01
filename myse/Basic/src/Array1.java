public class Array1 {
	public static void main(String[] args) {
//		int sal1=41; //1월급여
//		int sal2=25;
//		int sal3=25;
//		int sal4=33;
//		int sal5=44;
//		int sal6=55; //6월급여
		int[] sal; //배열선언
		sal = new int[6]; //배열생성
		sal[0] = 41;
		sal[1] = 25;
		System.out.println("1월급여:"+ sal[0]);
		System.out.println("2월급여:"+ sal[1]);
		System.out.println("6월급여:"+ sal[5]); //0
//		System.out.println("0월급여:"+ sal[-1]); //ArrayIndexOutOfBoundsException
		
		System.out.println("배열길이:"+sal.length); //6
		
		//배열초기화 : 배열선언,생성,대입을 한번에 할 수 있다.
		int[] arr = {1, 3, 5};
		System.out.println(arr[0]); //1
		System.out.println(arr[2]); //5
		System.out.println(arr.length); //3
		
		sal[2] = 25; sal[3] =33; sal[4]=44; sal[5]=55;
		//상반기 급여의 월평균을 계산하시오. 평균급여값은 소숫점이하값을 정확히 갖는다
		//int salTotal = sal[0]+sal[1]+sal[2]+sal[3]+sal[4]+sal[5];
		int salTotal =0;
		int monthCnt = sal.length;
		for(int index=0; index<monthCnt; index++) {
			salTotal += sal[index];
		}
		double salAvg = salTotal / 6.0;
		System.out.println("상반기 총급여=" + salTotal);
		System.out.println("상반기 평균급여=" + salAvg);
		
		int []arr1 = new int[10];
		/*
		arr1[0] = 1;
		arr1[1] = 2;
		arr1[2] = 3;
		arr1[3] = 4;
		arr1[4] = 5;
		arr1[5] = 6;
		arr1[6] = 7;
		arr1[7] = 8;
		arr1[8] = 9;
		arr1[9] = 10;
		*/
		int arr1Cnt = arr1.length;
		for(int i=0; i<arr1Cnt; i++) {
			arr1[i] = i+1;
			System.out.print(arr1[i] + "\t");
		}
		System.out.println();
	}

}
