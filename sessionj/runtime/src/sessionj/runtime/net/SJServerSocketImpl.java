package sessionj.runtime.net;

import sessionj.runtime.SJIOException;
import sessionj.runtime.SJProtocol;
import sessionj.runtime.transport.SJAcceptorThreadGroup;

/**
 * @author Raymond
 *
 * Defines methods which an SJServerSocket needs, and handles parameters etc..
 *
 */
public class SJServerSocketImpl extends SJServerSocket 
{
	//private static final SJRuntime runtime = SJRuntime.getSJRuntime();
	
	private SJAcceptorThreadGroup acceptors;
	
	/*protected SJServerSocketImpl(SJProtocol protocol, int port) throws SJIOException
	{
		super(protocol, port, SJSessionParameters.DEFAULT_PARAMETERS);
	}*/
	
	protected SJServerSocketImpl(SJProtocol protocol, int port, SJSessionParameters params) {
		super(protocol, port, params);
	}
	
	public SJAcceptingSocket accept() throws SJIOException, SJIncompatibleSessionException
	{
		if (isClosed())
		{
			throw new SJIOException("[SJServerSocketImpl] Server socket not open for accept.");
		}
		
		SJAcceptingSocket s = new SJAcceptingSocket(getProtocol(), getParameters());

        // nextConnection() blocks if there is no pending connection request.
		SJRuntime.bindSocket(s, nextConnection());			

        SJRuntime.accept(s);
		
		return s;
	}
	
	public void close() // throws SJIOException // Due to SJServerSocketCloser (or failure), can be closed early.
	{
		if (isOpen)
		{
			isOpen = false;
					
			if (!acceptors.isClosed())
			{		
				acceptors.close(); //FIXME: doesn't seem to be working... Seems the main acceptor thread isn't finishing?
			}
			
			SJRuntime.closeServerSocket(this);
		}			
	}
	
	protected void init() throws SJIOException
	{
		SJRuntime.openServerSocket(this);		
		
		isOpen = true;
	}
	
	public SJAcceptorThreadGroup getAcceptorGroup()
	{
		return acceptors;
	}
	
	protected void setAcceptorGroup(SJAcceptorThreadGroup acceptors)
	{
		this.acceptors = acceptors;
	}	
}
