package world.asia;
/**
 * 접근제어자(Access Modifier)
 * public - 누구나 접근가능, class/mf/constructor/method
 * default(아무런 접근제어자도 선언안됨) - 동일패키지에서 접근가능, class/mf/constructor/method
 * private - 자기클래스에서 접근가능, mf/constructor/method
 * @author HongJiPyo
 *
 */

public class korea {
	public String capital;
	String language;
	private int population;
	public void setPopulation(int population) {
		if(population < 1) {
			System.out.println("인구는 0이상이어야 한다");
		}else {
			this.population = population;
		}
	}
	public int getPopulation() {
		return population;
	}
}
