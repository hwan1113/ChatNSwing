package chatting.client.interact.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

//import project.chatting.Server.MultichatServer;

public class UserChatView extends JFrame{
//	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc = new GridBagConstraints();
	private JTextArea txtArea;
	private JButton send;
	private JButton close;
	JTextField input;
	
	public UserChatView() {
		addRootPanel();
		showMsg();
		addinputMsg();
		
		
		setVisible(true);
//		new MultichatServer().start();
	}
	
	public void addRootPanel() {
		setTitle("Chat");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridBagLayout());
	}
	
	public void showMsg() {
		gbc.insets=new Insets(0,3,0,3);
	//	gbc.ipadx=20;
	//	gbc.ipady=20;
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weighty=0.9;
		gbc.gridheight=1;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.BOTH;
		setTxtArea(new JTextArea());
		getTxtArea().setText("hi");
		getTxtArea().setBorder(new LineBorder(Color.black));
		getTxtArea().setEditable(false);
		getTxtArea().setCursor(new Cursor (Cursor.TEXT_CURSOR));
		this.add(getTxtArea(), gbc);
		
				
	}
	
	public void addinputMsg() {
		send = new JButton("전송");
		close = new JButton("닫기");
		input = new JTextField(20);
		gbc.weighty=0.1;
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.NONE;
		//gbc.weightx=0.5;
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(input,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		//gbc.weightx=0.1;
		this.add(getSend(),gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		this.add(close,gbc);
		
	
	}

	public JTextArea getTxtArea() {
		return txtArea;
	}

	public void setTxtArea(JTextArea txtArea) {
		this.txtArea = txtArea;
	}

	public JButton getSend() {
		return send;
	}

	public void setSend(JButton send) {
		this.send = send;
	}

	public JButton getClose() {
		return close;
	}

	public void setClose(JButton close) {
		this.close = close;
	}

	public JTextField getInput() {
		return input;
	}

	public void setInput(JTextField input) {
		this.input = input;
	}
	
	
	
	

}
