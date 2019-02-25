package chatting.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ServerSendReciv extends Thread{
	DataOutputStream out;
	DataInputStream in;
	Socket socket;
	Properties pp = new Properties();
	Map<String,DataOutputStream> UserData;
	
	public ServerSendReciv(Socket socket) {
		this.socket = socket;
		try {
			//서버로 데이터 전송통로 지정
			out = new DataOutputStream(socket.getOutputStream());
			//서베에서 들어오는 데이터 통로 지정
			in = new DataInputStream(socket.getInputStream());
		
		}catch( IOException e){
		}
	}
	
	
	@Override
	public void run() {
		String name ="";
		try {
			name =in.readUTF();
			//name에 읽어온 아이디 입력해주기
			//대화방 참여 메시지 전송
			sendAll("#"+name+"님이 입장하셨습니다.");
			
			//접속한 유저 Map에 저장
			UserData.put(name, out);
			System.out.println("접속자 수 :"+UserData.size());
			
			while(in != null) {	//입력대기 상태(무한루프)
				sendAll(in.readUTF());
			}
			
		}catch(IOException e) {	//유저나가면 Exception 발생
		}finally {
			sendAll(name+"님이 나감");
			//접속을 종료한 유저 지우기
			UserData.remove(name);
			
			System.out.println(socket.getInetAddress()+":"+socket.getPort()+"에서 접속 종료");
			System.out.println("접속 자 수 :"+UserData.size());
		}//end of finally
	}//end of run
	
	public void sendAll(String msg) {
		Iterator<String> it = UserData.keySet().iterator();
		//접속한 유저 닉네임 배열 생성
		
		while(it.hasNext()) {
			try {
				String name = it.next();
				DataOutputStream out = UserData.get(name);
				//들어온 내용 전체 유저들에게 뿌려주기
				out.writeUTF(msg);
				System.out.println(msg);
				//서버에도 출력 (테스트용)
			}catch(IOException e) {	//에러잡기 용도
			}
		}//end of while
	}//end of while
	
} // end of Thread

