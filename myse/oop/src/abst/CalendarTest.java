package abst;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {

	public static void main(String[] args) {
		Calendar now;
//		now = Calendar.getInstance(); //추상클래스느 객체생성 못함
		now = new GregorianCalendar();
		System.out.println(now.getClass().getName());
		//now = new GregorianCalendar();
		System.out.println(now); //now.toString() 호출됨
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		System.out.println("년도:" + year); //2021
		System.out.println("월:" + (month+1));//9
		System.out.println("일:" + now.get(Calendar.DATE));
		System.out.println("시:" + now.get(Calendar.HOUR_OF_DAY));
		System.out.println("분:" + now.get(Calendar.MINUTE));
		System.out.println("초:" + now.get(Calendar.SECOND));
		
		int day = now.get(Calendar.DAY_OF_WEEK); //금요일이면 6을 반환
		//if(day == 1 ) {}
		switch(day) {
		case Calendar.SUNDAY:
			System.out.println("일요일");
			break;
		case Calendar.MONDAY:
			System.out.println("월요일");
		case Calendar.TUESDAY:
			System.out.println("화요일");
		case Calendar.WEDNESDAY:
			System.out.println("수요일");
		case Calendar.THURSDAY:
			System.out.println("목요일");
		case Calendar.FRIDAY:
			System.out.println("금요일");
			break;
		default:
			System.out.println("토요일");
		}
		
		
		
	}
}
