package chatting.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{

	JLabel signUp;
	JLabel findInfo;
	JTextField inputId;
	JPasswordField inputPassword;
	JLabel outputMsg;
	
	public LoginFrame(int x, int y, int w, int h) {
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
			
			JButton btn = new JButton("로그인");
			btn.setBounds(330,100,110,110);
			btn.addActionListener(new MyActionListener());
			
			signUp = new JLabel("계정이 없으신가요?");
			signUp.setForeground(Color.BLUE);
			signUp.setBounds(140, 270, 200, 20);
			signUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			signUp.addMouseListener(new MyListener());
			
			findInfo = new JLabel("아이디/비밀번호 찾기");
			findInfo.setForeground(Color.BLUE);
			findInfo.setBounds(140, 300, 200, 20);
			findInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			findInfo.addMouseListener(new MyListener());
			
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
		
		
		class MyListener extends MouseAdapter{
			@Override
			public void mouseClicked(MouseEvent e) {
				
				}
			}
		}
		
		class MyActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
			}
		
	public static void main(String[] args) {
		new LoginFrame(200, 200, 500, 400);
	}

}
	//	master branch added
	//second master branch added
	//third master branch added

		//third change from forked
