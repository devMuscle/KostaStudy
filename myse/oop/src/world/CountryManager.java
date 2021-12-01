package world;
import world.asia.Japen;
//위의 2개의 import구문은 import.world.asia.*;로 사용가능하다 
import world.asia.korea;//best
import world.europe.France;
//위의 2개의 import구문은 import.world.asia.*;로 사용가능하다 

//import java.util.Scanner;  //import java.util.*;
//import java.sql.Date;      //import java.sql.*;

//사용시
//Scanner sc = new Scanner(System.in);
//Date d = new Date(); - Scnner는 util패키지의 것인거를 아는데 Date는 util,sql 패키지 모두에 있어서 구분이 힘들다

public class CountryManager {
	public static void main(String[] args) {
		/*world.asia.korea k;
		k = new world.asia.korea();*/
		korea k;
		k = new korea();

		Japen J = new Japen();
		France f =new France();
		
		k.capital = "평양";
		//k.language = "중국어";
		//k.population = -1;		
		k.setPopulation(-1);
		k.setPopulation(5000);
	}

}
