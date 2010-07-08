//$ bin/sessionjc tests/src/serverclient/server/Server.sj -d tests/classes/
//$ bin/sessionj -cp tests/classes/ serverclient.server.Server false d d 8888

package serverclient.server;

import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;
import sessionj.runtime.transport.*;
import sessionj.runtime.transport.tcp.*;
import sessionj.runtime.transport.sharedmem.*;
import sessionj.runtime.transport.httpservlet.*;

public class Server
{	
	//public final noalias protocol p_server { sbegin.?(String).!<String> }
	public final noalias protocol p_server { sbegin.?[?{$1:?(String).!<String>}]*.!<int> }
	
	public void run(boolean debug, String setups, String transports, int port) throws Exception
	{
		final noalias SJServerSocket ss;
		
		try (ss)
		{
			ss = SJServerSocket.create(p_server, port, SJTransportUtils.createSJSessionParameters(setups, transports));
			//ss = SJServerSocket.create(p_server, port);
			
			//while (true)
			{
				final noalias SJSocket s;
				
				try (s)
				{
					s = ss.accept();
						
					System.out.println("Accepted connection from: " + s.getHostName() + ":" + s.getPort()); 
										
					s.inwhile()
					{
						s.inbranch()
						{
							case $1:
							{
								//System.out.println("Received: " + (String) s.receive(1000));
								System.out.println("Received: " + (String) s.receive());
								
								//Thread.sleep(3000);
								
								s.send("Hello from Server!");		
							}
						}
					}
					
					s.send(12345);
				}
				/*catch (Exception x)
				{
					x.printStackTrace();
				}*/
				finally 
				{
					
				}
			}
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
 
		int port = Integer.parseInt(args[3]);
		
		new Server().run(debug, setups, transports, port);
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
