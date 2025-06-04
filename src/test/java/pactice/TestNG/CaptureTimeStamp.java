package pactice.TestNG;

import java.util.Date;

public class CaptureTimeStamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//We write replace to remove the space BCOZ in the name of files can not be Spaces
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		System.out.println(time);

	}

}
