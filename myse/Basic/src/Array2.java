
public class Array2 {
	public static void main(String[] args) {
		//배열초기화
		int[] arr = {1, 2, 3, 4};
		//arr = {0, 1}; //배열재초기화 안됨
		arr = new int [] {0, 1}; //배열재초기화
		
		char[] alpha = new char[5];
		/*alpha[0] = 'A';
		alpha[1] = 'B';
		alpha[2] = 'C';
		alpha[3] = 'D';
		alpha[4] = 'E'; */
		
		for(int i=0; i<alpha.length; i++) {
			alpha[i] = (char)('A'+i);
		}
		for(int i=0; i<alpha.length; i++) {
			System.out.println(i + "-" + alpha[i] + ",");
		}
		System.out.println();
		
		//1~10사이의 배열
		arr = new int[] {1, 4, 3, 2, 10, 4, 2, 7, 3, 1, 1};
		//각 수자의 출현횟수를 출력하시오
		int[] cntArr = new int[10];
		
		for(int i=0; i<arr.length; i++) {
			int num = arr[i]; // i:0; num:1,      i:1, num:4
			cntArr[num-1]++;  // cntArr[1-1]++;   cntArr[4-1]++;
		}
		
		for(int i=0; i<cntArr.length; i++) {
			System.out.println((i+1) + "의 출현횟수 : " + cntArr[i]);
		}
		System.out.println();
		
		//로또숫자 : 1~45사이의 중복되지 않는 숫자6개만들기
		int[] lotto = new int[6];
		for(int i=0; i<lotto.length; i++) {
			//현재숫자
			lotto[i] = (int)(Math.random()*45)+1; //1 <=(int)(r*45)+1   <46
			
			//중복: 이전숫자들과 현재숫자가 같은 경우
			for(int j=0; j<i; j++) {
				if(lotto[j] == lotto[i]) {
					i--;
					break;
				}
			}	
		}
		for(int i=0; i<lotto.length; i++) {
			System.out.println(lotto[i]);
		}
		
		
		//2차원 배열
		char[][] star;
		star = new char[5][];
//		star[0] = new char[1];
//		star[1] = new char[2];
//		star[2] = new char[3];
//		star[3] = new char[4];
//		star[4] = new char[5];
		
		for(int i=0; i<star.length; i++) {
			star[i] = new char [i+1];
		}
		
		for(int row=0; row<star.length; row++) {
			for(int col=0; col<star[row].length; col++) {
				star[row][col] = '*';
			}
		}
		
		for(int row=0; row<star.length; row++) {
			for(int col=0; col<star[row].length; col++) {
				System.out.print(star[row][col]);
			}
			System.out.println();
		}
		
		int[][] arr2 = new int[3][3];
		int num = 1;
		
		for(int row=0; row<arr2.length; row++) {
			for(int col=0; col<arr2[row].length; col++) {
				arr2[row][col] = num;
				num++;
			}
		}
		
		for(int row=0; row<arr2.length; row++) {
			for(int col=0; col<arr2[row].length; col++) {
				System.out.print(arr2[row][col]);
			}
			System.out.println();
		}
		
		//최대값구하기, 최소값구하기, 정렬하기(종류, 특징, 구현:선택정렬, 버블정렬)
		int[] arr3 = {4, 8, 3, 7, 2, 5};
		int maxNum =0;
		for(int i=0; i<arr3.length; i++) {
			if(maxNum < arr3[i]) {
				maxNum = arr3[i];
			}
		}
		System.out.println("최대값:" + maxNum);
		
		java.util.Arrays.sort(arr3); //오름차순 정렬용 라이브러리
		for(int i=0; i<arr3.length; i++) {
			System.out.println(arr3[i]);
		}
		
	}
}
