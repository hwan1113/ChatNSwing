package chatting.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServerDataAccess extends Thread{
	DataInputStream in ;
	DataOutputStream out;
	Socket socket;
	Properties pp = new Properties();
	private Map<String,DataOutputStream> UserData;
	
	public ServerDataAccess(Socket socket) {
		UserData = Collections.synchronizedMap(
				new HashMap<String, DataOutputStream>());
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		}catch(IOException e) {
		}
	}
	
	public void run() {
		String info = "";
		try { 
			// 서버에서는 최초에 클라이언트가 보낸 대화명을 받아야 한다. 
			info = in.readUTF();
			UserData.put(info, out);
			info(info);
			
			
//			// 대화명을 받아, 전에 클라이언트에게 대화방 참여 메시지를 보낸다. 
//			//sendToAll("#" + info + "님이 들어오셨습니다.");
//
//			// 대화명, 클라이언로 메시지를 보낼 수 있는 OutputStream 객체를
//			// 대화방 Map에 저장한다.  
//			//clients.put(info, out); 
//			//System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");
//              
//			// 클라이언트가 전송한 메시지를 받아, 전에 클라이언트에게 메시지를 보낸다. 
////			while (in != null) { 
////				//sendToAll(in.readUTF()); 
////			}//while  
//             
		} catch (IOException e) { 
			// ignore 
		} 
//			finally {
//			// 	finally절이 실행된다는 것은 클라이언트가 빠져나간 것을 의미한다. 
//			sendToAll("#" + info + "님이 나가셨습니다.");
//              
//			// 대화방에서 객체 삭제 
//			clients.remove(info);
//			System.out.println("[" + socket.getInetAddress() //
//			+ ":" + socket.getPort() + "]" + "에서 접속을 종료하였습니다.");
//			System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");
//		} // try 
	} // run 
	
	public void info(String info) {
		String[] infoo = info.split(",");
//		if(infoo.length==1) {			//아이디 중복 체크
//			System.out.println("infoooooo");
//			if(search(userinfo(),infoo[0])== false) send("false",info);
//			else send("true",info);
//			
//		}else if(infoo.length==2) {	//아이디 찾기
//			String id = findid(userinfo(),infoo);
//			if(id != null) send(id,info);
//			else send("null",info);
//			
//		}else if(infoo.length==3) {	//비밀번호 찾기
//			String pwd = findpwd(userinfo(),infoo);
//			if(pwd != null) send(pwd,info);
//			send("null",info);
			
		if(infoo.length==4) {	//로그인 
			Enumeration<?> it = userinfo();
			 while(it.hasMoreElements()) {
	                String user = (String)it.nextElement();
	                String[] userr = pp.getProperty(user).split(","); 
	                if(userr[1].equals(infoo[0])&&userr[2].equals(infoo[1])) {
	                	send("true",info);
	                }else 
	                {
	                	send("false",info);
	                }
			 }
			 
		} 
//		else if(infoo.length==5) {	//회원 가입
//			signUp(userinfo(),infoo);
//			send("succes",info);
//		}
		
	}
	public Enumeration<?> userinfo(){
		try {
			pp.load(new  FileReader("Customer.txt"));
		} catch (IOException e) {
		}
		Enumeration<?> it = pp.propertyNames();
		return it;
	}
	
	public void send(String msg,String info) {
		try {
			DataOutputStream out =UserData.get(info);
			out.writeUTF(msg);
		}catch(IOException e) {
		}
	}
	
	
	

}
