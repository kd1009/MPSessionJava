//$ bin/sessionjc tests/src/compiler/protocoldecls/Test.sj -d tests/classes/
//$ bin/sessionj -cp tests/classes/ compiler.protocoldecls.Test

package compiler.protocoldecls;

import java.util.*;
import java.lang.reflect.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;


public class KacperAgent {

	public static void main (String[] args) throws SJIOException {		

		KacperProt ss = new KacperProt();

	   System.out.println(ss.agency.toString());
		System.out.println(ss.customer1.toString());
		
		System.out.println("I am " + ss.getClass());
		
		ss.agency.setLocal();
		ss.customer1.setRemote("localhost", 1050);
		ss.acceptInvite();

		System.out.println(ss.agency.receiveDouble());
	}
}

