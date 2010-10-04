package helloworld;

import sessionj.runtime.net.*;
import sessionj.runtime.*;

public class Me {


	public static void main (String [] args) throws SJIOException, ClassNotFoundException {

		HelloWorldProt hwp = new HelloWorldProt();

		hwp.me.setLocal();
		hwp.world.setRemote("localhost", 1300, 1200);
		hwp.you.setRemote("localhost", 1600, 1700); 
		hwp.him.setRemote("localhost", 2300, 2200);
		hwp.her.setRemote("localhost", 3000, 3100); 

		hwp.acceptInvite();

		System.out.println((String)hwp.you.receive());
		hwp.world.send("hello, i am ME saying hello to WORLD");
	}

}
