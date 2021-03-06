/**
 * 
 */
package sessionj.runtime.net;

import sessionj.runtime.SJIOException;
import sessionj.runtime.SJProtocol;
import sessionj.runtime.transport.*;

import java.util.Set;

/**
 * @author Raymond
 *
 */
abstract public class SJServerSocket implements SJChannel, SJSelectableChannel
{
	private final SJProtocol protocol;
	private final int port; // The local port.

	private SJPort sjPort; // Later probably replace port by just sjPort.
	
	protected boolean isOpen = false;
	
	private final SJSessionParameters params;
	
	protected SJServerSocket(SJProtocol protocol, int port, SJSessionParameters params) {
		this.protocol = protocol;
		this.port = port;
		this.params = params;
	}	
		
	public static SJServerSocket create(SJProtocol protocol, int port) throws SJIOException
	{	
		return create(protocol, port, SJSessionParameters.DEFAULT_PARAMETERS);
	}
	
	public static SJServerSocket create(SJProtocol protocol, int port, SJSessionParameters params) throws SJIOException
	{
		SJServerSocket ss = new SJServerSocketImpl(protocol, port, params);
		
		ss.init();
		
		return ss;
	}
	
	public static SJServerSocket create(SJProtocol protocol, SJPort sjPort) throws SJIOException
	{		
		SJServerSocket ss = new SJServerSocketImpl(protocol, sjPort.getValue(), sjPort.getParameters()); // No need to keep hold of the SJPort object?
		
		ss.sjPort = sjPort;
		
		ss.init(); // Need to call init after the SJPort has been recorded (cannot reuse above create routines).
		
		return ss;
	}
	
	abstract public SJAbstractSocket accept() throws SJIOException, SJIncompatibleSessionException;
	abstract public void close();
	
	public SJProtocol getProtocol()
	{
		return protocol;
	}
	
	public int getLocalPort()
	{
		return port;
	}
	
	public boolean isClosed()
	{
		return !isOpen;
	}
	
	abstract protected void init() throws SJIOException;
	
	abstract protected SJAcceptorThreadGroup getAcceptorGroup();
	abstract protected void setAcceptorGroup(SJAcceptorThreadGroup acceptors);
	
	public SJServerSocketCloser getCloser()
	{
		return new SJServerSocketCloser(this);
	}
	
	public SJPort getLocalSJPort()
	{
		return sjPort; // FIXME: currently returns null if the server socket was initialised using an integer port value.
	}
	
	public SJSessionParameters getParameters()
	{
		return params;
	}

    public boolean typeStartsWithOutput() throws SJIOException {
        return getProtocol().type().child().startsWithOutput();
    }

    public synchronized SJConnection nextConnection() throws SJIOException {
        while (true) {
            SJConnection conn = getAcceptorGroup().nextConnection();
            if (params.validConnection(conn)) {
                return conn;
            } else {
                getAcceptorGroup().queueConnection(conn);
            }
        }
    }

    public SJConnectionAcceptor getAcceptorFor(String transportName) {
        return getAcceptorGroup().getAcceptorFor(transportName);
    }

	public Set<SJTransport> activeTransports() {
		return getAcceptorGroup().activeTransports();
	}

	@Override
	public String toString() {
		return "SJServerSocket{" +
			"protocol=" + protocol +
			", port=" + port +
			", sjPort=" + sjPort +
			", isOpen=" + isOpen +
			", params=" + params +
			'}';
	}
}
