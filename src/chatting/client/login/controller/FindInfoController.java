package chatting.client.login.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import chatting.client.login.view.FindInfoView;

public class FindInfoController {
	DataOutputStream dos;
	Socket socket;
	FindInfoView fi = new FindInfoView();
	
	public String getId(){
		return fi.getId().toString();
	}
	public String getName(){
		return fi.getNameStr();
	}
	
	public void sender(String msg) {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(msg);
		}catch(IOException e) {
		}
	}
	
	class MyActionListener implements ActionListener {
		String str;
		String idStr;
		String nameStr;
		String pnStr;
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
			UIManager.put("OptionPane.messageFont", new Font("맑은고딕", Font.PLAIN, 20));
			String valid= "";
			if (e.getSource() == fi.getSerchPwd()) {
				idStr = fi.getId().getText();
				nameStr = fi.getName().getText();
				sender(nameStr+","+idStr+","+"0");
				while(in != null) {
					try {
						valid = in.readUTF();
					}catch(IOException q) {
					}
					if(valid==null) {
						JOptionPane.showMessageDialog(null, "정보가 검색되지 않습니다.");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호: " + valid);
					}
				}
			}
				
			 else if (e.getSource() == fi.getSerchId()) {
				nameStr = fi.getName().getText();
				pnStr = fi.getPn().getText();
				sender(nameStr+","+pnStr);
				while(in != null) {
					try {
						valid = in.readUTF();
					}catch(IOException q) {
					}
				}
				if (valid.equals("null")) {
					JOptionPane.showMessageDialog(null, "정보가 검색되지 않습니다.");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "아이디: " + valid);
				}
			}
		}
	}

}
