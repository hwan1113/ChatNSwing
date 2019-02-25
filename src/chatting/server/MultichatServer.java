package chatting.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class MultichatServer {
	
	ServerSocket serverSocket ;
	Socket socket ;

	// 대화명, 클라이언트 OutputStream 저장용 대화방(HashMap) 정의
	Map<String, DataOutputStream> clients; 

	// 생성자 
	public MultichatServer() { 
		clients = Collections.synchronizedMap(
				new HashMap<String, DataOutputStream>());
	} 

	// 비즈니스 로직을 처리하는 메서드 
	public void start() {
		ServerSocket serverSocket =null;
		Socket socket; 

		try { 
			// 7777 포트에 바인딩된 서버 소켓 생성 
			serverSocket = new ServerSocket(9000);
			System.out.println("서버가 시작되었습니다.");
			while (true) { 
				// 클라이언트 접속 대기 accept()
				socket = serverSocket.accept();
				
				System.out.println("[" + socket.getInetAddress() //
				+ ":" + socket.getPort() + "]" + "에서 접속하였습니다.");
				
				// 서버에서 클라이언트로 메시지를 전송할 Thread 생성
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start(); 

			}// while 
            

		} catch (Exception e) { 
				e.printStackTrace(); 
		} finally { 
//			SocketUtil.close(serverSocket);
		} 
	} // start() 

	// 대화방에 있는 전체 유저에게 메시지 전송 
	void sendToAll(String msg) { 
		// 대화방에 접속한 유저의 대화명 리스트 추출 
		Iterator<String> it = clients.keySet().iterator(); 

		while (it.hasNext()) { 
			try { 
				String name = it.next();
				DataOutputStream out = clients.get(name); 
				out.writeUTF(msg);			//입력이 없어도 무한반복가능하게 만든 코드
				System.out.println(msg);
			} catch (IOException e) { 
			} 
		} // while 
	} // sendToAll 
	
	
	public void send(String msg,String info) {
		try {
			DataOutputStream out =clients.get(info);
			out.writeUTF(msg);
			
		}catch(IOException e) {
		}
	}
	
	
	
	// Inner Class로 정의 하여, 대화방 field에 접근 할 수 있도록 한다. 
	// 서버에서 클라이언트로 메시지를 전송할 Thread 
	class ServerReceiver extends Thread {
		Socket socket; 
		DataInputStream in; 
		DataOutputStream out;
		Properties pp = new Properties();

		ServerReceiver(Socket socket) { 
			this.socket = socket; 
			try { 
				// 클라이언트 소켓에서 데이터를 수신받기 위한 InputStream 생성
				in = new DataInputStream(socket.getInputStream());
                  
				// 클라이언트 소켓에서 데이터를 전송하기 위한 OutputStream 생성
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) { } 
		}
		
		
		//Inner Class안의 메소드는 자동실행되나?
		public void run() {
			String info = "";
			try { 
				// 서버에서는 최초에 클라이언트가 보낸 대화명을 받아야 한다. 
				info = in.readUTF();
				clients.put(info, out);
	//			info(info);
				
				
				// 대화명을 받아, 전에 클라이언트에게 대화방 참여 메시지를 보낸다. 
				sendToAll("#" + info + "님이 들어오셨습니다.");
 
				// 대화명, 클라이언로 메시지를 보낼 수 있는 OutputStream 객체를
				// 대화방 Map에 저장한다.  
				clients.put(info, out); 
				System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");
                  
				// 클라이언트가 전송한 메시지를 받아, 전에 클라이언트에게 메시지를 보낸다. 
				while (in != null) { 
					sendToAll(in.readUTF()); 
				}//while  
                 
			} catch (IOException e) { 
				// ignore 
			} finally { 
				// 	finally절이 실행된다는 것은 클라이언트가 빠져나간 것을 의미한다. 
				sendToAll("#" + info + "님이 나가셨습니다.");
                  
				// 대화방에서 객체 삭제 
				clients.remove(info);
				System.out.println("[" + socket.getInetAddress() //
				+ ":" + socket.getPort() + "]" + "에서 접속을 종료하였습니다.");
				System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");
			} // try 
		} // run 
		
//		public void info(String info) {
//			String[] infoo = info.split(",");
//			if(infoo.length==1) {			//아이디 중복 체크
//				System.out.println("infoooooo");
//				if(search(userinfo(),infoo[0])== false) send("false",info);
//				else send("true",info);
//				
//			}else if(infoo.length==2) {	//아이디 찾기
//				String id = findid(userinfo(),infoo);
//				if(id != null) send(id,info);
//				else send("null",info);
//				
//			}else if(infoo.length==3) {	//비밀번호 찾기
//				String pwd = findpwd(userinfo(),infoo);
//				if(pwd != null) send(pwd,info);
//				send("null",info);
//				
//			}else if(infoo.length==4) {	//로그인 
//				try {
//					pp.load(new  FileReader("Customer.txt"));
//				} catch (IOException e) {
//				}
//				Enumeration<?> it = pp.propertyNames();
//				 while(it.hasMoreElements()) {
//		                String user = (String)it.nextElement();
//		                String[] userr = pp.getProperty(user).split(","); 
//		                if(userr[1].equals(infoo[0])&&userr[2].equals(infoo[1])) {
//		                    return true;
//		                }
//		            }
//		            return false;
//		            
//		            send("true",info);
//					else send("false",info);
//		        }
//				
//				
//				if(login(userinfo(),infoo)) send("true",info);
//				else send("false",info);
//				
//			} else if(infoo.length==5) {	//회원 가입
//				signUp(userinfo(),infoo);
//				send("succes",info);
//			}
//		}
	} // ReceiverThread 
	
	
	
	public static void main(String [] args) {
		new MultichatServer().start();
	}
    
} // class 