package chatting.findInfo;

public class FindInfoController {
	
	FindInfoView fi = new FindInfoView();
	
	public String getId(){
		return fi.idStr;
	}
	public String getName(){
		return fi.nameStr;
	}

}
