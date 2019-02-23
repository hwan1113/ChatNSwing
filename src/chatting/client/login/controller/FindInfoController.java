package chatting.client.login.controller;

import chatting.client.login.view.FindInfoView;

public class FindInfoController {
	
	FindInfoView fi = new FindInfoView();
	
	public String getId(){
		return fi.idStr;
	}
	public String getName(){
		return fi.nameStr;
	}

}
