package chatting.client.login.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import chatting.client.login.controller.SignUpController;


public class SignUpView {
	private JButton checkId,man,girl,join,cannel;
	private JTextField inId,inPwd,inName,inPn;
	private JLabel idd;
	private JFrame frame;
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private String gender ;
	
	public SignUpView() {

		frame = new JFrame("회원가입");

		frame.setBounds(200, 300, 450, 450);
		frame.setLayout(null);

		JLabel id = new JLabel("ID");
		id.setBounds(50, 35, 150, 50);
		idd = new JLabel("5글자 이상, 특수문자 사용 불가");
		idd.setBounds(120, 75, 200, 30);
		JLabel pwd = new JLabel("비밀번호");
		pwd.setBounds(50, 85, 150, 50);
		JLabel name = new JLabel("이름");
		name.setBounds(50, 145, 150, 50);
		JLabel pn = new JLabel("전화번호");
		pn.setBounds(50, 195, 150, 50);
		JLabel gen = new JLabel("성별");
		gen.setBounds(50, 245, 150, 50);

		inId = new JTextField(20);
		inId.setBounds(130, 50, 150, 30);
		inPwd = new JTextField(20);
		inPwd.setBounds(130, 100, 150, 30);
		inName = new JTextField(20);
		inName.setBounds(130, 155, 150, 30);
		inPn = new JTextField(20);
		inPn.setBounds(130, 210, 150, 30);
		checkId = new JButton("중복 확인");
		checkId.setBounds(310, 47, 90, 50);
		JLabel print = new JLabel();
		
		man = new JButton("남");
		man.setBounds(130, 255, 50, 30);
		girl = new JButton("여");
		girl.setBounds(190, 255, 50, 30);
		join = new JButton("회원가입");
		join.setBounds(90, 330,110, 30);
		cannel= new  JButton("취소");
		cannel.setBounds(220, 330, 70, 30);

		
		frame.add(id);
		frame.add(idd);
		frame.add(pwd);
		frame.add(name);
		frame.add(pn);
		frame.add(inId);
		frame.add(inPwd);
		frame.add(inName);
		frame.add(inPn);
		frame.add(checkId);
		frame.add(gen);
		frame.add(man);
		frame.add(girl);
		frame.add(join);
		frame.add(cannel);

		print.setBounds(130, 250, 150, 70);
		frame.add(print);

		frame.setResizable(false);

		frame.setVisible(true);
	}

	public JTextField getInId() {
		return inId;
	}

	public void setInId(JTextField inId) {
		this.inId = inId;
	}

	public JTextField getInPwd() {
		return inPwd;
	}

	public void setInPwd(JTextField inPwd) {
		this.inPwd = inPwd;
	}

	public JTextField getInName() {
		return inName;
	}

	public void setInName(JTextField inName) {
		this.inName = inName;
	}

	public JTextField getInPn() {
		return inPn;
	}

	public void setInPn(JTextField inPn) {
		this.inPn = inPn;
	}

	public JLabel getIdd() {
		return idd;
	}

	public void setIdd(JLabel idd) {
		this.idd = idd;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public JButton getCheckId() {
		return checkId;
	}

	public void setCheckId(JButton checkId) {
		this.checkId = checkId;
	}

	public JButton getMan() {
		return man;
	}

	public void setMan(JButton man) {
		this.man = man;
	}

	public JButton getGirl() {
		return girl;
	}

	public void setGirl(JButton girl) {
		this.girl = girl;
	}

	public JButton getJoin() {
		return join;
	}

	public void setJoin(JButton join) {
		this.join = join;
	}

	public JButton getCannel() {
		return cannel;
	}

	public void setCannel(JButton cannel) {
		this.cannel = cannel;
	}

}

