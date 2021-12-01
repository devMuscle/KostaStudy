package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient2 {
	Socket s;
	OutputStream os;
	DataOutputStream dos;
	
	Scanner sc; //키보드 입력 스트림
	
	String serverIP;
	int serverPort;
	public TCPClient2() {
//		serverIP = "127.0.0.1";
//		serverPort = 5432;
		this("127.0.0.1", 5432);
	}
	public TCPClient2(String serverIp, int serverPort) {
		this.serverIP = serverIp;
		this.serverPort = serverPort;
		try {
			s = new Socket(serverIP, serverPort);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			
			//dos.writeUTF("가나다");
			sc = new Scanner(System.in);
//			String line = sc.nextLine();
//			dos.writeUTF(line);
			String line = "quit";
			do {
				line = sc.nextLine();
				dos.writeUTF(line);
			}while(!line.equals("quit"));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch(ConnectException e) {
			System.out.println("서버와 연결실패. 서버가 켜져있는지 확인하세요.");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(sc != null) {
				sc.close();
			}
		}
	}
	public static void main(String[] args) {
		new TCPClient2();
		//new TCPClient1("192.168.75.64", 6543);

	}

}
