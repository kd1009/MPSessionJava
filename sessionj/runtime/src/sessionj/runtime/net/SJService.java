package sessionj.runtime.net;

import java.io.*;

import java.net.InetSocketAddress;
import java.util.*;

import sessionj.runtime.*;
import sessionj.runtime.transport.*;

import static sessionj.SJConstants.*;

public class SJService implements SJServiceInterface
{
	public static final long serialVersionUID = SJ_VERSION;

	private SJProtocol protocol;
	private SJServerIdentifier si;

	protected SJService() { }
	
	private SJService(SJProtocol protocol, SJServerIdentifier si)
	{
		this.protocol = protocol;
		this.si = si;
	}

	public static SJService create(SJProtocol protocol, String hostName, int port) 
	{ 
		return new SJService(protocol, new SJServerIdentifier(hostName, port)); 
	}

	public static SJService create(SJProtocol protocol, String hostName, SJPort port) // FIXME: record SJPort properly.
	{ 
		return create(protocol, hostName, port.getValue()); 
	}
	
	public SJRequestingSocket request() throws SJIOException, SJIncompatibleSessionException
	{
		return request(SJSessionParameters.DEFAULT_PARAMETERS); // Use default setups and transports.
	}
		
	public SJRequestingSocket request(SJSessionParameters params) throws SJIOException, SJIncompatibleSessionException
	{
		SJRequestingSocket s = new SJRequestingSocket(this, params); 
		
		SJRuntime.connectSocket(s);		
		SJRuntime.request(s);
		
		return s;
	}
	
	public SJProtocol getProtocol()
	{
		return protocol;
	}

	public SJServerIdentifier getServerIdentifier()
	{
		return si;
	}
	
	public String toString()
	{
		return getProtocol().toString() + "@" + getServerIdentifier();
	}
}
