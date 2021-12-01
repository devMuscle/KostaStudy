class Datatype{
    public static void main(String[]args){
      //정수형
      byte b = 127;
      //b = 128; //error발생

      int i = 5;
      System.out.println(i); //5
      System.out.println(5); //컴파일러가 5를 int로 판단

      //long lo1 = 12345678901234;
      long lo2 = 12345678901234L;

      double d = 45.67;
      System.out.println(d); //45.67

      //i=45.67; //error발생
      System.out.println(45.67F);

      //문자형
      char c;
      c = 'A';
      c = 49; //'1'과 같음
      System.out.println(c);
      c = 44032; //'가'와 같음
      System.out.println(c);

      //논리형
      boolean flag;
      //flag = True; //TRUE; // error발생
      flag = true;

      //자료형변환
      //자동형변환  byte -> short -> int -> long ->float -> double
      //                           char -> 

      i=b; 
      d=i;
      System.out.println(b); //127
      System.out.println(i); //127
      System.out.println(d); //127.0
      i = c; //'가'의 utf-16값이 i에 대입
      System.out.println(i); //44032 

      //강제형변환(더 큰 자료형의 값을 작은 자료형의 값으로 구겨넣는것)
      i = 128;
      //b=i; //error발생
      b =(byte)i; //강제형변환연산자() 
      System.out.println(b); //범위를 넘어서서 최솟값인 -128
      
      i = 129;
      b =(byte)i;
      System.out.println(b); //-127

     d = 45.67;
      i = (int)d;
      System.out.println(i);  //45
    }
}