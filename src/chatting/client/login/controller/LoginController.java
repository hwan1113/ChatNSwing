package chatting.client.login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import chatting.client.interact.controller.UserChatController;
import chatting.client.login.view.LoginView;


public class LoginController {
	private LoginView lv = new LoginView(200, 200, 500, 400);
	private SignUpController signupc;
	private UserChatController ucc;
	private FindInfoController finc;
	private String name;
	private Socket socket;
	private DataOutputStream dos;
	
	LoginController(){
		lv.getBtn().addActionListener(new MyActionListener());
		lv.getSignUp().addMouseListener(new MyListener());
		lv.getFindInfo().addMouseListener(new MyListener());
		String ip = "200.200.200.219";
		try {socket = new Socket(ip,9000);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	class MyListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==lv.getSignUp()) {
				signupc= new SignUpController();
			}else if(e.getSource()==lv.getFindInfo()) {
				finc = new FindInfoController();
			}
		}
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String pwd = String.valueOf(lv.getInputPassword().getPassword());
			String str = lv.getInputId().getText()+","+pwd+",0,0";
			sender(str);
			Thread thread = new Receive(socket);
			thread.start();
		}
	}
	
	public void sender(String msg) {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(msg);
		}catch(IOException e) {
		}
	}

	class Receive extends Thread{
		DataInputStream in;
		DataOutputStream out;
		Socket socket;
		public Receive() {}
		public Receive(Socket socket) {
			this.socket=socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new  DataOutputStream(socket.getOutputStream());
			}catch(IOException e) {
			}
		}
		public void run() {
			String valid = "";
			while(in != null) {
				try {
					valid = in.readUTF();
				}catch(IOException e) {
				}
				if(valid=="true") {
					new UserChatController(lv.getName(), socket);
				}else {
					lv.getOutputMsg().setText("잘못된 입력입니다.");
			}
		}
	}
	}
	
	public static void main(String[] args) {
		new LoginController();
	}
}

