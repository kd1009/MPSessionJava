//$ bin/sessionjc -cp tests/classes/ tests/src/serverclient/client/Client.sj -d tests/classes/
//$ bin/sessionj -cp tests/classes/ serverclient.client.Client false d d localhost 8888 

package serverclient.client;

import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;
import sessionj.runtime.transport.*;
import sessionj.runtime.transport.tcp.*;
import sessionj.runtime.transport.sharedmem.*;
import sessionj.runtime.transport.httpservlet.*;

import serverclient.server.Server;

public class Client
{		
	//private final noalias protocol p_client { cbegin.!<int> }
	private final noalias protocol p_client { ^(Server.p_server) }
	
	public void run(boolean debug, String setups, String transports, String server, int port) throws Exception
	{
		final noalias SJSocket s;	
			
		try (s)
		{
			//long start = System.nanoTime();
							
			s = SJService.create(p_client, server, port).request(SJTransportUtils.createSJSessionParameters(setups, transports));
			//s = SJService.create(p_client, server, port).request();
			
			long start = System.nanoTime();
			
			int i = 0;
			
			if (i == 0)
			{
				//throw new RuntimeException("foo");
			}
			
			s.outwhile(i++ < 3)
			{
				s.outbranch($1)
				{								
					//Thread.sleep(10000);					

					/*System.out.println("Current session type: " + s.currentSessionType());
					System.out.println("Remaining session type: " + s.remainingSessionType());*/
					
					s.send("Hello from Client!");
					//s.send(123);
										
					/*System.out.println("Current session type: " + s.currentSessionType());
					System.out.println("Remaining session type: " + s.remainingSessionType());*/
					
					//System.out.println("Received: " + (String) s.receive(5000));
					System.out.println("Received: " + (String) s.receive());
				}
			}
			
			System.out.println("Received: " + s.receiveInt());
			
			long finish = System.nanoTime();
			
			System.out.println("time = " + (finish - start) / 1000000 + " millis.");
		}
		finally
		{
			
		}
	}

	public static void main(String[] args) throws Exception
	{
		boolean debug = Boolean.parseBoolean(args[0]);
		
		String setups = args[1];
		String transports = args[2];
		
		//configureTransports(setups, transports);
 
		String server = args[3];
		int port = Integer.parseInt(args[4]);
		//int size = Integer.parseInt(args[5]);
		//int len = Integer.parseInt(args[6]);		
		
		new Client().run(debug, setups, transports, server, port);
	}

	/*private static SJSessionParameters createSJSessionParameters(String setups, String transports)
	{
		SJSessionParameters params;
		
		if (setups.contains("d") && transports.contains("d"))
		{
			params = new SJSessionParameters();
		}
		else
		{
			List ss = new LinkedList();
			List ts = new LinkedList();				
			
			parseTransportFlags(ss, setups);
			parseTransportFlags(ts, transports);
								
			params = new SJSessionParameters(ss, ts);
		}

		return params;
	}
	
	private static void parseTransportFlags(List ts, String transports)
	{
		if (transports.contains("d"))
		{
			ts.add(new SJFifoPair());
			ts.add(new SJStreamTCP());
			
			return;
		}
		
		char[] cs = transports.toCharArray();
		
		for (int i = 0; i < cs.length; i++)
		{
			switch (cs[i])
			{
				case 'f':
				{
					ts.add(new SJFifoPair());
					
					break;
				}
				case 's':
				{
					ts.add(new SJStreamTCP());
					
					break;
				}					
				case 'm':
				{			
					ts.add(new SJManualTCP());
					
					break;
				}					
				case 'h':
				{			
					ts.add(new SJHTTPServlet());
					
					break;
				}
			}
		}					
	}
	
	private static void configureTransports(String setups, String transports)
	{
		SJTransportManager sjtm = SJRuntime.getTransportManager();	
		
		if (!setups.contains("d"))
		{
			List ss = new LinkedList();
			
			parseTransportFlags(ss, setups);		
			
			sjtm.configureSetups(ss);
		}
		
		if (!transports.contains("d"))
		{
			List ts = new LinkedList();
			
			parseTransportFlags(ts, transports);	
			
			sjtm.configureTransports(ts);
		}		
	}*/	
}
