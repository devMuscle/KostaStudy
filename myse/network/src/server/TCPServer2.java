package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer2 {
	ServerSocket ss;
	int port;
	TCPServer2() {
		this(5432);
	}
	TCPServer2(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port); //포트열기
			
			while(true) {
				Socket s = null;
				InputStream is = null;
				DataInputStream dis = null;
				String clientAddress = null;
				try {
					s = ss.accept(); //클라이언트의 접속기다리기
					InetAddress clientIP = s.getInetAddress();
					clientAddress = clientIP.getHostAddress();
					System.out.println(clientAddress + "클라이언트가 접속했습니다");
					is = s.getInputStream();
					//System.out.println("수신된 내용:" + is.read());
					dis = new DataInputStream(is);
					
//					while(true) {
//						String readUTFValue = dis.readUTF();
//						if(readUTFValue.equals("quit")) {
//							break;
//						}
//						System.out.println("수신된 내용:" + readUTFValue);
//					}
					String readUTFValue = "quit";
					while(!(readUTFValue = dis.readUTF()).equals("quit")) {
						System.out.println(clientAddress + "로 부터 수신된 내용:" + readUTFValue);
					}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					if(dis != null) {
						try {
							dis.close();
						} catch(IOException e) {	
						}
					}
					if(is != null) {
						try {
							is.close();
						} catch(IOException e) {
						}
					}
					if(s != null) {
						try {
							s.close();
						} catch(IOException e) {
						}
					}
					System.out.println(clientAddress + "클라이언트가 접속을 해제했습니다");
				}
			}//end of while
		} catch(BindException e) {
			System.out.println(port + "포트가 사용중이거나 이미 서버가 구동중입니다");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new TCPServer2();
		//new TCPServer1(6543);
		
	}

}
