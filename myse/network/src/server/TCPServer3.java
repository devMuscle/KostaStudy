package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer3 {
	ServerSocket ss;
	int port;
	TCPServer3() {
		this(5432);
	}
	TCPServer3(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port); //포트열기
			
			while(true) {
				Socket s = null;
				InputStream is = null;
				DataInputStream dis = null;
				String clientAddress = null;
				
				OutputStream os = null;
				DataOutputStream dos = null;
				
				try {
					s = ss.accept(); //클라이언트의 접속기다리기
					InetAddress clientIP = s.getInetAddress();
					clientAddress = clientIP.getHostAddress();
					System.out.println(clientAddress + "클라이언트가 접속했습니다");
					is = s.getInputStream();
					dis = new DataInputStream(is);
					
					os = s.getOutputStream();
					dos = new DataOutputStream(os);
					
					String readUTFValue = "quit";
					while(!(readUTFValue = dis.readUTF()).equals("quit")) {
						//TODO 수신받은 내용을 클라이언트에게  그대로 송신
						dos.writeUTF(readUTFValue);
					}
				}catch(EOFException e) {
					
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
					if(dos != null) {
						try {
							dos.close();
						} catch(IOException e) {
						}
					}
					if(os != null) {
						try {
							os.close();
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
		new TCPServer3();
		//new TCPServer1(6543);
		
	}

}
