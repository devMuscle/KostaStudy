package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient1 {
	Socket s;
	OutputStream os;
	DataOutputStream dos;
	
	String serverIP;
	int serverPort;
	public TCPClient1() {
//		serverIP = "127.0.0.1";
//		serverPort = 5432;
		this("127.0.0.1", 5432);
	}
	public TCPClient1(String serverIp, int serverPort) {
		this.serverIP = serverIp;
		this.serverPort = serverPort;
		try {
			s = new Socket(serverIP, serverPort);
			os = s.getOutputStream();
			//os.write(65);
			
			dos = new DataOutputStream(os);
			dos.writeInt(10);
			dos.writeBoolean(true);
			dos.writeUTF("가나다");
			
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
		}
	}
	public static void main(String[] args) {
		new TCPClient1();
		//new TCPClient1("192.168.75.64", 6543);

	}

}
