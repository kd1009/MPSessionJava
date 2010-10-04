package helloworld;

import java.lang.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;


public class World {
	
	public static void main (String [] args) throws SJIOException, ClassNotFoundException {

		HelloWorldProt hwp = new HelloWorldProt();

		hwp.world.setLocal();
		hwp.you.setRemote("localhost", 1500, 1400);
		hwp.me.setRemote("localhost", 1200, 1300);
		hwp.him.setRemote("localhost", 2100, 2000);
		hwp.her.setRemote("localhost", 2400, 2500);

		hwp.acceptInvite();

		System.out.println((String)hwp.you.receive());
		System.out.println((String)hwp.me.receive());
		System.out.println((String)hwp.him.receive());
		System.out.println((String)hwp.her.receive());
	}

}
