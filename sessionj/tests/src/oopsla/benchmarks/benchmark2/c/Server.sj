//$ bin/sessionjc -cp tests/classes/ tests/src/benchmarks/benchmark2/c/Server.sj -d tests/classes/
//$ bin/sessionj -cp tests/classes/ benchmarks.benchmark2.c.Server 8888

package benchmarks.benchmark2.c;

import java.io.*;
import java.net.*;

import sessionj.runtime.*;
import sessionj.runtime.net.*;

import benchmarks.*;

/**
 * The same as benchmark1.a.Server except uses benchmarks.NoAliasBinaryTree and noalias, and does not print any debug info. (this class is used by benchmark2a.) Cannot print the noalias tree after sending it anyway - it's null.
 */
public class Server 
{
	private final noalias protocol p 
	{
		sbegin.?[?(NoAliasBinaryTree).!<NoAliasBinaryTree>]*
	}	
	
	public Server(int port) throws Exception 
	{							
		ServerThread st = new ServerThread(port);		
		
		st.start();
	}

	private class ServerThread extends Thread 
	{
		private int port;
		
		public ServerThread(int port)
		{
			this.port = port;
		}
		
		public void run()
		{
			final noalias SJServerSocket ss;
			
			try (ss)
			{
				boolean run = true;
				
				ss = SJServerSocketImpl.create(p, port);
				
				new KillThread(port + Kill.KILL_PORT_ADJUST, ss.getCloser()).start();
				
				noalias NoAliasBinaryTree bt;
				
				for (int counter = 0; run; counter++) 
				{						
					final noalias SJSocket ds;
					
					try (ds)
					{					
						ds = ss.accept(); // Dummy run.							
						
						ds.inwhile()
						{
							bt = (NoAliasBinaryTree) ds.receive();
							 
							bt.inc();
							 
							ds.send(bt);
						}			
		
						final noalias SJSocket s;
					
						try (s) 
						{	
							s = ss.accept(); // Actual run.
							
							s.inwhile()
							{
							 	bt = (NoAliasBinaryTree) s.receive();				
							 	
							 	bt.inc();
							 	
							 	s.send(bt);
							}
						}
						finally 
						{ 
		
						}					
					}			
					catch (Exception x)
					{
						run = false;
					}
					finally
					{
						
					}
				}
			}
			catch (Exception x)
			{
				throw new RuntimeException(x);
			}
			finally
			{
				
			}			
		}
	}
	
	public static void main(String[] args) throws Exception 
	{
		int port = Integer.parseInt(args[0]);
		
		new Server(port);
	}
}
