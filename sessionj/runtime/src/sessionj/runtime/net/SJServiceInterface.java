package sessionj.runtime.net;

import java.io.*;

import java.net.InetSocketAddress;

import sessionj.runtime.*;
import sessionj.runtime.transport.*;

import static sessionj.SJConstants.*;

public interface SJServiceInterface extends Serializable, SJChannel
{
	public SJRequestingSocket request() throws SJIOException, SJIncompatibleSessionException;
	
	public SJProtocol getProtocol();
	public SJServerIdentifier getServerIdentifier();
}
