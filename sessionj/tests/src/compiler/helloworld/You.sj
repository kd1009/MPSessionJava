package helloworld;

import sessionj.runtime.net.*;
import sessionj.runtime.*;

public class You {


	public static void main (String [] args) throws SJIOException, ClassNotFoundException {

		HelloWorldProt hwp = new HelloWorldProt();

		hwp.you.setLocal();
		hwp.world.setRemote("localhost", 1400, 1500);
		hwp.me.setRemote("localhost", 1700, 1600);
		hwp.him.setRemote("localhost", 1900, 1800); 
		hwp.her.setRemote("localhost", 2600, 2700);

		hwp.invite();

		hwp.me.send("hello, i am YOU saying hello to ME!!");
		hwp.world.send("hello, i am YOU saying hello to WORLD!!");
		
	}

}
