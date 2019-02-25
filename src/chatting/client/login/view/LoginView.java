package chatting.client.login.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chatting.client.interact.controller.UserChatController;
import chatting.client.interact.view.UserChatView;
import chatting.client.login.controller.FindInfoController;
import chatting.client.login.controller.SignUpController;

public class LoginView extends JFrame{

	private JLabel signUp;
	private JLabel findInfo;
	private JTextField inputId;
	private JPasswordField inputPassword;
	private JLabel outputMsg;
	private JButton btn;
	
	public LoginView() {}
	public LoginView(int x, int y, int w, int h) {
			setBounds(x,y,w,h);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Login");
			setLayout(null);
			
			JLabel id = new JLabel("아이디 : ");
			id.setBounds(50, 100, 100, 50);
			
			//사용자 입력폼
			inputId = new JTextField(20);
			inputId.setBounds(110, 100, 200, 50);
			
			JLabel password = new JLabel("비밀번호 : ");
			password.setBounds(40, 160, 70, 30);
			
			inputPassword = new JPasswordField(20);
			inputPassword.setBounds(110, 160, 200, 50);
			
			btn = new JButton("로그인");
			btn.setBounds(330,100,110,110);
			
			signUp = new JLabel("계정이 없으신가요?");
			signUp.setForeground(Color.BLUE);
			signUp.setBounds(140, 270, 200, 20);
			signUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			findInfo = new JLabel("아이디/비밀번호 찾기");
			findInfo.setForeground(Color.BLUE);
			findInfo.setBounds(140, 300, 200, 20);
			findInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			outputMsg = new JLabel();
			outputMsg.setBounds(140, 230, 200, 20);
			
			//컨테이너 객체에 추가
			
			add(id);
			add(inputId);
			add(password);
			add(inputPassword);
			add(btn);
			add(outputMsg);
			add(signUp);
			add(findInfo);
			setResizable(false);
			setVisible(true);
		}
		
	
	
	
	public JLabel getSignUp() {
		return signUp;
	}
	public void setSignUp(JLabel signUp) {
		this.signUp = signUp;
	}
	public JLabel getFindInfo() {
		return findInfo;
	}
	public void setFindInfo(JLabel findInfo) {
		this.findInfo = findInfo;
	}
	public JButton getBtn() {
		return btn;
	}
	public void setBtn(JButton btn) {
		this.btn = btn;
	}
	
	public JTextField getInputId() {
		return inputId;
	}
	public void setInputId(JTextField inputId) {
		this.inputId = inputId;
	}
	public JPasswordField getInputPassword() {
		return inputPassword;
	}
	public void setInputPassword(JPasswordField inputPassword) {
		this.inputPassword = inputPassword;
	}

	
	public JLabel getOutputMsg() {
		return outputMsg;
	}
	public void setOutputMsg(JLabel outputMsg) {
		this.outputMsg = outputMsg;
	}
}
