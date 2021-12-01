/**
 * 고객객체용 클래스이다
 * 고객정보(아이디, 비밀번호, 이름, 주소)
 * @author HongJiPyo
 *
 */
class Customer{
	String id;
	String pwd;
	String name;
	String address;
	Customer(){}
	
	/**
	 * 고객정보를 초기화한다
	 * @param id 아이디
	 * @param pwd 비밀번호
	 * @param name 이름
	 * @param address 주소
	 */
	Customer(String id, String pwd, String name, String address){
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
	}
	
	/**
	 * 고객정보를 출력한다
	 * 출력예로 아이디:1 , 비밀번호:2, 이름:3, 주소:4 이다 
	 */
	void printInfo() {
		System.out.println("아이디:" + id + ", 비밀번호:" + pwd + ", 이름:" + name + ", 주소:" + address);	
	}
}

/**
 * 고객객체를 저장소에 추가, 조회, 수정, 삭제하는 클래스이다
 * @author HongJiPyo
 *
 */
class CustomerDAO{
	Customer[] customers; //고객저장소
	int count; //저장된 고객수
	CustomerDAO(){
		customers = new Customer[5];
	}
	CustomerDAO(int size) {
		customers = new Customer[size];
	}
	void printInfo() {
		System.out.println("고객저장소의 크기" + customers.length);
		System.out.println("저장된 고객수" + count);
		for(int i=0; i<count; i++) {
			Customer c = customers[i];
			System.out.print("[" + i + "]=");
			c.printInfo();
		}
	}
	void add(Customer customer) {
		if(count >= customers.length) {
			System.out.println("저장소가 꽉 찼습니다");
		}else {
			customers[count] = customer;
			count++; //Good Code
			System.out.println("고객추가 성공");
		}
//		customers[count++] = customer; //Bad Code
	}
	Customer[] findAll(){ //Customer[]all = dao.findAll(); 로 사용한다
		Customer[] all = new Customer[count];
//		for(int i=0; i<count; i++) {
//			all[i] = customers[i];
//		}
		System.arraycopy(customers, 0, all, 0, count);
		return all;
	}
	Customer findById(String id) { //Customer c = dao.findById(id); 로 사용한다
		for(int i=0; i<count; i++) {
			Customer c = customers[i];
			String cId = c.id; //저장된 고객ID
			if(cId.equals(id)) {
				return c;
			}
		}
		return null;
	}
	Customer[] findByName(String word) { //Customer[] cArr = dao.findByName(word);로 사용한다
//			int wordCnt = 0; //단어를 포함한 고객수
//			for(int i=0; i<count; i++) {
//				Customer c = customers[i];
//				String cName = c.name; //저장된 고객이름
//				if(cName.contains(word)) { //if(cName.contains(word)==true){
//					wordCnt++;
//				}
//			}
//			
//			Customer []cArr = new Customer[wordCnt];
//			int cArrIndex = 0;
//			for(int i=0; i<count; i++) {
//				Customer c = customers[i];
//				String cName = c.name; //저장된 고객이름
//				if(cName.contains(word)) { //if(cName.contains(word)==true){
//					cArr[cArrIndex] = c;
//					cArrIndex++;
//				}
//			}
			
			Customer []cArr1 = new Customer[count];
			int cArrIndex = 0;
			for(int i=0; i<count; i++) {
				Customer c = customers[i];
				String cName = c.name;
				if(cName.contains(word)) {
					//cArr1[i] = c; //이부분이 오타. 아래코드로 바꿈
					cArr1[cArrIndex] = c; 
					cArrIndex++;
				}
			}
			//Customer []cArr =  new Customer[cArrIndex];//null값을 반환하거나 참조값을 반환해야하니까 먼저 null로 초기화
			Customer []cArr = null;
			if(cArrIndex > 0) { 
				cArr = new Customer[cArrIndex];
				System.arraycopy(cArr1, 0, cArr, 0, cArrIndex);
			}
			return cArr;
			
		}
		void modify(Customer c) {
			Customer c1 = findById(c.id);
			boolean flag = false;
			if(c1 != null) {
				if(!"".equals(c.pwd)) {
					c1.pwd = c.pwd;
					flag = true;
				}
				if(!"".equals(c.name)) {
					c1.name = c.name;
					flag = true;
				}
				if(!"".equals(c.address)) {
					c1.address = c.address;
					flag = true;
				}
			}
			
			if(flag) { //if(flag==true){
				System.out.println("고객정보가 변경되었습니다");
			}else {
				System.out.println("고객정보가 변경되지않았습니다");
			}
		}
}




public class CustomerManager {
	public static void main(String[] args) {
		CustomerDAO dao;
		dao = new CustomerDAO(); //최대5명이 저장될 저장소로 초기화
		
		java.util.Scanner sc;
		sc = new java.util.Scanner(System.in);	
		String readValue;
		while(true) {
			System.out.println("작업구분: 1-추가, 2-저장소, 3-전체조회, 4-아이디로 검색, 5-이름으로 검색, 6-고객 수정, 9-종료");
			System.out.print("작업을 선택하세요:");
			readValue = sc.nextLine();
			switch(readValue) {
			case "1" :
				System.out.println(">>고객 추가<<");
				System.out.print("아이디:");
				String id = sc.nextLine();
				System.out.print("비밀번호:");
				String pwd = sc.nextLine();
				System.out.print("이름:");
				String name = sc.nextLine();
				System.out.print("주소:");
				String address = sc.nextLine();
					
				Customer c1 = new Customer(id, pwd, name, address);
				dao.add(c1);
				break;
			case "2" :
				System.out.println(">>저장소 정보<<");
				dao.printInfo();
				break;
			case "3" :
				System.out.println(">>고객 전체조회<<");
				Customer[] all = dao.findAll();
				for(Customer c: all) {
					c.printInfo();
				}
				break;
			case "4" :
				System.out.println(">>아이디로 검색<<");
				System.out.print("아이디:");
				id = sc.nextLine();
				Customer c = dao.findById(id);
				if(c==null) {
					System.out.println(id + "고객은 존재하지 않습니다");
				}else {
				c.printInfo();
				}
				break;
			case "5" :
				System.out.println(">>이름으로 검색<<");
				System.out.println("단어:");
				String word = sc.nextLine();
				Customer[] cArr = dao.findByName(word);
				if(cArr == null) {
					System.out.println("단어[" + word + "]를 이름에 포함한 고객은 존재하지 않습니다");
				}else {
					for(Customer c2: cArr) {
						c2.printInfo();
					}
				}
				break;
			case "6" :
				System.out.println(">>고객 수정<<");
				System.out.print("아이디:");
				id = sc.nextLine();
				//조회
				Customer c6 = dao.findById(id);
				if(c6 == null) {
					System.out.println(id + "고객은 존재하지 않습니다");
				}else {
					c6.printInfo();
					System.out.println("변경안하려면 enter를 입력하고, 변경하려면 값을 입력하시오");
					System.out.print("비밀번호[" + c6.pwd + "]:");
					String c6Pwd = sc.nextLine();
					//System.out.println("입력된 비번값:" + c6Pwd);
					System.out.print("이름[" + c6.name + "]:");
					String c6Name = sc.nextLine();
					System.out.print("주소[" + c6.address + "]:");
					String c6Address = sc.nextLine();
					Customer c6Modify = new Customer(id, c6Pwd, c6Name, c6Address);
					dao.modify(c6Modify);
				}
				break;
			case "9" :
				System.exit(0);
				break;
			}
		}
	}
}
