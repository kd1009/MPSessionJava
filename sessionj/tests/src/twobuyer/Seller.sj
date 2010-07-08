package twobuyer;

import java.net.*;
import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;
import sessionj.runtime.transport.*;

class Seller {

	private float quote = 50.6;
	
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
	
public Seller (String setups, String transports, String addr_s, int port_for_b1, int port_for_b2) throws Exception {	
		
		SJSessionParameters params = SJTransportUtils.createSJSessionParameters(setups, transports);
		
		final noalias SJServerSocket ss_s_b1 = SJServerSocketImpl.create(glob_twobuyer, "s", "b1", port_for_b1, params);
		final noalias SJServerSocket ss_s_b2 = SJServerSocketImpl.create(glob_twobuyer, "s", "b2", port_for_b2, params);
		
		final noalias SJSocket s_s_b1;
		final noalias SJSocket s_s_b2;
		
		try (s_s_b1, s_s_b2) {
		
			s_s_b1 = ss_s_b1.accept();
			s_s_b2 = ss_s_b2.accept();
		
			String book = s_s_b1.receiveString();
			System.out.println("Requested quote for book: " + book);
			
			s_s_b1.send(quote);
			s_s_b2.send(quote);
			System.out.println("Sent quote: " + quote);
			
			String customer_address;
			s_s_b2.inbranch() {
				case ACCEPT: {
					customer_address = s_s_b2.receiveString();
					s_s_b2.send(5);
				}
				case REJECT: {
					System.out.println("Buyer 2 not prepared to contribute enough. Transaction aborted.");
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
		
		int port_for_b1 = Integer.parseInt(args[2]);
		int port_for_b2 = Integer.parseInt(args[3]);
		
		new Seller(setups, transports, port_for_b1, port_for_b2);
	}	
	
		
		
		