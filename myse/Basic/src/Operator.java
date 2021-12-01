class Operator{
    public static void main(String[]args){
    //산술연산자 +,-,*, / ,%
    int a, b, c;
    a=2;
    b=3;
    c=a+b; //5
    c=a/b; //0
    double c1 = a/b; // 0.0  정수를 정수로 나눈 결과가 정수이다. 정수값을 double에 넣었기 때문에 .0이 붙은거 뿐
    double c2 = (double)a/b; //0.5

    short s1, s2, s3; //int보다 작은 자료형은 산술연산불가하다. 산술연산시 int로 자동형변환 된다.
    s1 = 2;
    s2 = 3;
    //s3 = s1+s2;  //자동형변환된 int값을 short에 넣을려니 컴파일 에러발생
    s3 = (short)(s1+s2);
    
    
    //증감연산자 ++, --
    byte by =127;
    by++; //b변수값을 1증가한다
    System.out.println(by); //-128

    a=2;
    b=a++; //a값이 b에 대입된 뒤에 ++연산, b가 2가되고 a는 3이된다
    System.out.println(b); //2

    a=2;
    b=++a;//a값이 ++가 된 뒤에 b에 a값이 대입, b가 3이되고 a도 3이된다
    System.out.println(b); //3

    //대입연산자 =,+=,-=,*=,/=,%=
    a=2;
    a=a+5;

    a=2;
    a+=5;
    //비교연산자 >,<,>=,<=,==,!=
    a=2;
    b=3;
    boolean result = a>b;
    System.out.println(result); //false
    System.out.println(a!=b); //true
    System.out.println(a==b);//false

    //논리연산자 &,&&,|,||,! (1개 쓰면 연산속도가 느리고, 2개 쓰면 연산속도가 빠르다, 2개짜리 같은경우 and연산에서 앞에것이 false면 뒤에것은
                       //             연산도 안해보고 바로 false 반환)
                    //              (&의 좌우에 리터럴 값이 들어가면 비트 and연산이다)
     result = 2<3 && 2%3 == 1;
     System.out.println(result); //false

     result = true || false ;
     result = 2<3 || 2%3 == 1;
     System.out.println(result); //true
     
     //삼항 연산자
     a = 2;
     b = 3;
     c = a<b? 10: 20;
     System.out.println(c); //10
     c = a==b? 10: 20;  
     System.out.println(c); //20
     
     //성별값이 1이거나 3인 겨웅엔 남자를 출력, 그렇지 않은 경우 여자를 출력
     int gend = 1;
     System.out.println(gend==1 || gend==3? "남자":"여자");
     
     //성별값이 홀수인 경우엔 남자를 출력, 그외의 경우 여자를 출력
     System.out.println(gend%2==1 ? "남자":"여자");
     
    }
}