//$ bin/sessionjc tests/src/Test.sj -d tests/classes/
//$ bin/sessionj -cp tests/classes/ Test

import java.io.*;
import java.net.*;
import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;

public class Test  
{		
	//static protocol foo { !<String> }
	//static protocol bar { !<String>.@(foo) }	
	
	/*static class A 
	{
		private noalias Object a1;
	
		public A meth2()
		{
			return null;
		}
	
		public A meth(noalias A a, noalias A aa) 
		{
			a1 = a;	
			
			return null;
		}
	}*/
	
	public static void main(String[] args) throws Exception
	{		
		//noalias Integer i = new Integer(123);
		
		//noalias A a2 = new A();
		//noalias A a3 = new A();
		
		//a2.meth(a3.meth2(), a3.meth(a3, null));
	
		//a3 = a2.meth2();
	
			noalias Object o;
			
			o.toString();
	
		/*final noalias protocol p1 { cbegin.?(String).!<String> }
		//final noalias protocol p1 { cbegin.?{L1: !<String>} }
		final noalias protocol p2 { cbegin.?[!<int>]* }
		
		final noalias SJService c1 = SJService.create(p1, "A", 1234);
		final noalias SJService c2 = SJService.create(p2, "B", 1234);
		
		protocol p_select { ?(String).!<String> }
		
		final noalias SJSelector sel = SJRuntime.selectorFor(p_select);
		
		try (sel)
		{
			noalias SJSocket s1, s2;
			
			try (s1, s2)
			{
				s1 = c1.request();
				
				sel.registerInput(s1);
			}
			finally
			{
				
			}
		}
		finally
		{
			
		}*/
	}
}
