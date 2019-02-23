package chatting.client.interact.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import chatting.client.interact.view.ChatFrame;

public class UserChat {
	ChatFrame chatView = new ChatFrame();
	String name;
	Socket socket;
	DataOutputStream dos;
	DataInputStream dis;
	
	public UserChat(String name) {
		this.name = name;
		try {
			// 클라이언트 쪽에서 접속할 서버의 ip입력
			String serverIp = "192.168.25.32";
			// 서버의 ip,port번호 입력-연결
			socket = new Socket(serverIp, 5000);
			System.out.println("서버에 연결되었습니다. 채팅 시작합니다.");

			// 메시지 전송용 Thread
			MessageSender(socket);
			
			// 수신용 Thread
			Thread thread = new Thread(new ClientJoin(socket));
			thread.start();

		} catch (ConnectException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void MessageSender(Socket socket) {
		try {dos = new DataOutputStream(socket.getOutputStream());
			if (dos != null)
				dos.writeUTF(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void send(String message) {
			if (dos != null) {
		try {
			dos.writeUTF("[" + name + "]" + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
	}
		
	
	public class ClientJoin implements Runnable {
		Socket socket;
		DataInputStream dis;

		public ClientJoin(Socket socket) {
			this.socket = socket;
			try {
				// 서버로 부터 다른(혹은 본인) 클라이언트가 입력한 메시지 받기
				this.dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					// 메시지 수신용
					String s = dis.readUTF();
					System.out.println(s);
					setMessage(s);
				} catch (IOException e) {
				}
			}
		}
	}
	
	class myActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == chatView.getSend()) {
				String message = null;
				message = chatView.getInput().getText();
				send(message);

				chatView.getInput().setText("");
				chatView.getInput().requestFocus();
			} else if (e.getSource() == chatView.getClose()) {
				chatView.dispose();
			}
		}
	}
	
	public void setMessage(String message) {
		chatView.getTxtArea().append(message+"\n");
	}
	
	public static void main(String[] args) {
		new UserChat("name");
	}

}
