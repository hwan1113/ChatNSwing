package chatting.client.login.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public interface FindPwd {
	//비밀번호 찾기 메소드
	
	static String findpwd(String id,String name) {
		Properties pp = new Properties();
        try {
            pp.load(new FileReader("Customer.txt")); //불러오기
        }catch(IOException e) {
            e.printStackTrace();
        }
        Enumeration<?> e = pp.propertyNames();
        while(e.hasMoreElements()) {
            String temp = (String) e.nextElement();
            String[] userInfo = pp.getProperty(temp).split(",");
            
            for(int i=0;i<userInfo.length;i++) {
                if(id.equals(userInfo[1])&&name.equals(userInfo[0])) {
                    return userInfo[2]; //비밀번호 리턴
                }
            }
        }
        return null;  //회원 정보없으면 null리턴
        //아이디 또는 비밀번호 재입력 메시지띄우기
    }
}
