
package twobuyer;

import java.net.*;
import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;
import sessionj.runtime.transport.*;

class Buyer1 {

	private String BOOK = "How to pass the MSc";
	private float quote;
	
	/* 	1. b1 sends book title to s and receives quote back
		2. s also sends quote to b2
		3. b1 sends what she is prepared to contribute to b2
		4. b2 decides if he is prepared to pay the rest and sends address to s -> receives days to delivery
	*/ 
	
	mp_protocol glob_twobuyer {
		mp_begin
		.|b1,s| : !<String>.?(float)
		.|s,b2| : !<float>
		.|b1,b2|: !<float>
		.|b2,s| : !{
					ACCEPT: 
						!<String>.?(int)
					,REJECT:
					}
	}
	
public Buyer1 (String setups, String transports, String addr_s, int port_s, String addr_b2, int port_b2) throws Exception{	
		
		SJSessionParameters params = SJTransportUtils.createSJSessionParameters(setups, transports);
		
		final noalias SJService c_b1_s = SJService.create(glob_twobuyer, "b1", "s", addr_s, port_s);
		final noalias SJSocket s_b1_s;
		
		final noalias SJService c_b1_b2 = SJService.create(glob_twobuyer, "b1", "b2", addr_b2, port_b2);
		final noalias SJSocket s_b1_b2;
		
		try (s_b1_s, s_b1_b2) {
		
			s_b1_s = c_b1_s.request(params);
			s_b1_b2 = c_b1_b2.request(params);
			
			s_b1_s.send(BOOK);
			
			System.out.println("Requested quote for book: " + BOOK);
			
			quote = s_b1_s.receiveFloat();
			
			System.out.println("Received quote: " + cost);
			
			s_b1_b2.send(quote/2);
			
			System.out.println("Sent information: prepared to pay " + (quote/2) + "£.");	
		}
		
		finally {
			
		}
}

	public static void main(String[] args) throws Exception
	{
		String setups = args[0];
		String transports = args[1];
		
		String host_a = args[2];
		int port_a = Integer.parseInt(args[3]);
		
		String host_b = args[4];
		int port_b = Integer.parseInt(args[5]);
		
		new Buyer1(setups, transports, host_a, port_a, host_b, port_b);
	}	
	
		
		
		