package chatting.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class ServerStarter {
	private ServerSocket serverSocket ;
	private Socket socket ;
	
	public ServerStarter() {
	}
	
	public void server() {
		try {
			serverSocket = new ServerSocket(4000);
			System.out.println("서버 시작");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println("----------");
				new ServerDataAccess(socket);
				new ServerSendReciv(socket);
			}
		}catch(IOException e) {
		}finally {
			System.out.println("접속 종료");
		}
	}
	public static void main(String[] args) {
		new ServerStarter().server();

	}

}
