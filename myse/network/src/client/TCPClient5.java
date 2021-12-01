package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
class ClientThread extends Thread{
	Socket s;
	InputStream is;
	DataInputStream dis;	
	ClientThread(Socket s) throws IOException{
		this.s = s;
		is = s.getInputStream();
		dis = new DataInputStream(is);		
	}
	public void run() {
		try {
			while(true) {
				System.out.println("서버가 보내준 내용:" + dis.readUTF());
			}
		}catch(EOFException e) {
		}catch(Exception e) {			
		}finally {
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
				}
			}
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
public class TCPClient5 {
	Socket s;
	OutputStream os;
	DataOutputStream dos;
	
	
	
	Scanner sc; //키보드 입력스트림
	
	String serverIP;
	int serverPort;
	public TCPClient5() {
		this("127.0.0.1", 5432);
	}
	public TCPClient5(String serverIp, int serverPort) {
		this.serverIP = serverIp;
		this.serverPort = serverPort;
		try {
			s = new Socket(serverIP, serverPort);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			
			
			ClientThread t = new ClientThread(s);
			t.start();
			
			sc = new Scanner(System.in);
			String line = "quit";
			do {
				line = sc.nextLine();
				dos.writeUTF(line);
			}while(!line.equals("quit"));	
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}catch(ConnectException e) {
			System.out.println("서버와 연결실패. 서버가 켜져있는지 확인하세요.");
		} catch(SocketException e) {
			
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
		new TCPClient5();
		//new TCPClient1("192.168.75.64", 6543);
	}

}
