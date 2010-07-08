
package twobuyer;

import java.net.*;
import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;
import sessionj.runtime.transport.*;

class Buyer2 {
	
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
	
public Buyer2 (String setups, String transports, String addr_s, int port_s, int port_for_b1) throws Exception{	
		
		SJSessionParameters params = SJTransportUtils.createSJSessionParameters(setups, transports);
		
		final noalias SJServerSocket ss_b2_b1 = SJServerSocketImpl.create(glob_twobuyer, "b2", "b1", port_for_b1, params);
		final noalias SJSocket s_b2_b1;
		
		final noalias SJService c_b2_s = SJService.create(glob_twobuyer, "b2", "s", addr_s, port_s);
		final noalias SJSocket s_b2_s;
		
		
		try (s_b2_b1, s_b2_b1) {
		
			s_b2_b1 = ss_b2_b1.accept;
			s_b2_s = c_b2_s.request(params);
			
			float quote = s_b2_s.receiveFloat();
			System.out.println("Received quote for a book: " + quote);
			
			float contr = s_b2_b1.receiveFloat();
			System.out.println("Buyer1 willing to contribute: " + contr);
			System.out.println("I need to pay: " + (quote-contr));
			
			if ((quote-contr)<60) {
				s_b2_s.outbranch(ACCEPT){
					s_b2_s.send("This is my address");
					int days = s_b2_s.receiveInt();
					
					System.out.println("I will receive the book in " + days + " days.");
				}
			}
			
			else {
				s_b2.s.outbranch(REJECT) {
					System.out.println("I am not prepared to pay that much!");
				}
			}
		}
		
		finally {
			
		}
}

	public static void main(String[] args) throws Exception
	{
		String setups = args[0];
		String transports = args[1];
		
		String seller_add = args[2];
		int seller_port = Integer.parseInt(args[3]);
	
		int port_for_b1 = Integer.parseInt(args[4]);
		
		new Buyer2(setups, transports, seller_add, seller_port, port_for_b1);
	}	
	
		
		
		