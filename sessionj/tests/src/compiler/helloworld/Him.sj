package helloworld;

import sessionj.runtime.net.*;
import sessionj.runtime.*;

public class Him {


	public static void main (String [] args) throws SJIOException, ClassNotFoundException {

		HelloWorldProt hwp = new HelloWorldProt();

		hwp.him.setLocal();
		hwp.world.setRemote("localhost", 2000, 2100);
		hwp.you.setRemote("localhost", 1800, 1900); 
		hwp.me.setRemote("localhost", 2200, 2300); 
		hwp.her.setRemote("localhost", 2800, 2900);

		hwp.acceptInvite();

		hwp.world.send("hello, i am HIM saying hello to WORLD");
	}

}
