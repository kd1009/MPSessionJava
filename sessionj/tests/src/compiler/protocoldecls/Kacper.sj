//$ bin/sessionjc tests/src/compiler/protocoldecls/Test.sj -d tests/classes/
//$ bin/sessionj -cp tests/classes/ compiler.protocoldecls.Test

package compiler.protocoldecls;

import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;

/*
public class Test 
{			
	public static void main(String[] args) throws Exception
	{		
		//final noalias protocol p1 { cbegin.?(!(int).!(String)) }
		//final noalias protocol p2 { cbegin.@(A.pa) }
		//final noalias protocol p3{ ^(B.pb).!(Integer) }
	}
}

class A
{
	//public static final noalias protocol pa { ?(!(int).!(String)) }
}
*/

public global_protocol Kacper { |agency,buyer1|!<int>.|agency,buyer2|!<int>.|agency,creditcards|?(int) }

/*
class MP
{ 
	private global_protocol kacper2 { |a,b|!<int>.|a,b|!<int>, |b,a|!<int> }
	//public static final noalias protocol kacper3 {?(int).!<int>}
}
*/


