package helloworld;

import sessionj.runtime.net.*;
import sessionj.runtime.*;

public class Her {


	public static void main (String [] args) throws SJIOException, ClassNotFoundException {

		HelloWorldProt hwp = new HelloWorldProt();

		hwp.her.setLocal();
		hwp.world.setRemote("localhost", 2500, 2400);
		hwp.you.setRemote("localhost", 2700, 2600); 
		hwp.me.setRemote("localhost", 3100, 3000);
		hwp.him.setRemote("localhost", 2900, 2800);  

		hwp.acceptInvite();

		hwp.world.send("hello, i am HER saying hello to WORLD");
	}

}
