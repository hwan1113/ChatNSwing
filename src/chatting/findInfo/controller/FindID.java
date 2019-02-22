package chatting.findInfo.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public interface FindID {
	//ID찾기 메소드
	static String findId(String name,String pn) {
		Properties pp = new Properties();
        try {
            pp.load(new FileReader("Customer.txt"));
        }catch(IOException e ) {
            e.printStackTrace();
        }
        Enumeration<?> ee = pp.propertyNames();
        while(ee.hasMoreElements()) {
            String search = (String)ee.nextElement();
            String[] userInfo = pp.getProperty(search).split(",");
            
            if(userInfo[0].equals(name)&&userInfo[3].equals(pn)) {
                return userInfo[1];
            }
        }
        return null;
    }
}

