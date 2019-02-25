package chatting.client.login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Properties;

import chatting.client.interact.controller.UserChatController;
import chatting.client.login.view.SignUpView;

public class SignUpController {

	private SignUpView suv = new SignUpView();
	private Properties pp = new  Properties();
	DataInputStream dis;
	DataOutputStream dos;
	Socket socket;
	
	public SignUpController() {
		suv.getCheckId().addActionListener(new  Myaction());
		suv.getMan().addActionListener(new  Myaction());
		suv.getGirl().addActionListener(new  Myaction());
		suv.getJoin().addActionListener(new Myaction());
		suv.getCannel().addActionListener(new  Myaction());
	}
	
	public void sender(String msg) {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(msg);
		}catch(IOException e) {
		}
	}
	
		//회원정보  null값 걸러내기
		public  String nullPoint(String id,String pwd,String pn,String name,String gender) {
			String sel ="";
			if(id.equals("")) {
				sel += "아이디";
			}else if(pwd.equals("")){
				sel += "패스워드";
			}else if(pn.equals("")) {
				sel += "전화번호";
			}else if(name.equals("")) {
				sel += "이름";
			}else if(gender.equals("")) {
				sel += "성별";
			}
			return sel;
		}
		class Myaction implements ActionListener {
			String gender ="";
			DataInputStream in;
			DataOutputStream out;
			Socket socket;
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					in = new DataInputStream(socket.getInputStream());
					out = new  DataOutputStream(socket.getOutputStream());
				}catch(IOException m) {
				}
				String valid= "";
				if(e.getSource()==suv.getCheckId()) {
					sender(suv.getInId().toString());
					while(in != null) {
						try {
							valid = in.readUTF();
						}catch(IOException q) {
						}
						if(valid=="true") {
							suv.getIdd().setText("이미 사용중인 아이디 입니다");
						}else {
							suv.getIdd().setText("사용가능한 아이디입니다");
						}
					}
				}
				else if(e.getSource()==suv.getMan()) {
					gender ="남";
				}else if(e.getSource()==suv.getGirl()) {
					gender = "여";
					
				}else if(e.getSource()==suv.getJoin()) {
					String id =suv.getInId().getText();
					String pwd = suv.getInPwd().getText();
					String name =suv.getInName().getText();
					String pn = suv.getInPn().getText();
					if(nullPoint(id, pwd, pn, name, gender).equals("")) {	  //빈값 있는지 확인하기
						sender(name+","+id+","+pwd+","+pn+","+gender.toString());
						while(in != null) {
							try {
								valid = in.readUTF();
							}catch(IOException q) {
							}
						}
						if(valid.equals("succes")==false) {
							suv.getIdd().setText(nullPoint(id, pwd, pn, name, pn)+"을 확인해주세요");
							return;
						}
						suv.getFrame().dispose();
					}else {
						suv.getIdd().setText(nullPoint(id, pwd, pn, name, pn)+"을 확인해주세요");	
					}
				}else if(e.getSource()==suv.getCannel()) {
					suv.getFrame().dispose();
				}
			}//end of action
		}//end of actionClass
}
