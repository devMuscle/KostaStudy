public class TV {
	boolean power;
	int channel;
	int volume;
	boolean isPower(){
		return power;
	}
	void powerOn(){
		power = true;
	}
	void powerOff() {
		power = false;
	}
	
	void setChannel(int ch) {
		channel = ch;
	}
	
	void volumeUp() {
		volume++;
	}
	void printInfo() {
		System.out.println("전원은 " + power);
		System.out.println("채널은 " + channel);
		System.out.println("볼륨은 " + volume);
	}
}
