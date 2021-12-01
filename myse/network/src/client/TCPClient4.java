package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient4 {
	Socket s;
	OutputStream os;
	DataOutputStream dos;
	
	InputStream is;
	DataInputStream dis;
	
	Scanner sc; //키보드 입력 스트림
	
	String serverIP;
	int serverPort;
	public TCPClient4() {
//		serverIP = "127.0.0.1";
//		serverPort = 5432;
		this("127.0.0.1", 5432);
	}
	public TCPClient4(String serverIp, int serverPort) {
		this.serverIP = serverIp;
		this.serverPort = serverPort;
		try {
			s = new Socket(serverIP, serverPort);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			
			is = s.getInputStream();
			dis = new DataInputStream(is);
			
			//dos.writeUTF("가나다");
			sc = new Scanner(System.in);
			String line = "quit";
			do {
				line = sc.nextLine();
				dos.writeUTF(line);
				System.out.println("서버가 Echo한 내용:" + dis.readUTF());
			}while(!line.equals("quit"));
		} catch(EOFException e) {
			
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
		}
	}
	public static void main(String[] args) {
		new TCPClient4();

	}

}
