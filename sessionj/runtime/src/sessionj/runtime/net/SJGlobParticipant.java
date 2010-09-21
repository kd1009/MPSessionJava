package sessionj.runtime.net;

import sessionj.runtime.SJIOException;
import sessionj.runtime.SJProtocol;
import sessionj.runtime.session.SJSerializer;
import sessionj.runtime.session.SJSessionProtocols;
import sessionj.runtime.session.SJStateManager;
import sessionj.runtime.transport.SJConnection;
import sessionj.types.sesstypes.SJSessionType;

public class SJGlobParticipant implements SJSocket {

	private SJAbstractSocket del;
	
	public SJServerSocket servsocket;
	public SJService serv;

	private String name;
	private String hostname;
	private int remotePort;
	private int localPort;
	private boolean local = false;
	
	public SJGlobParticipant(String name) {
		this.name = name;
	}

	public void setRemote(String host, int remotePort, int localPort) {
		this.hostname = host;
		this.remotePort = remotePort;
		this.localPort = localPort;
	}

	public void setLocal() {
		this.local = true;
	}

	public String getHostname() {
		return hostname;
	}

	public int getRemotePort() {
		return remotePort;
	}
	
	public int getLocalPort() {
		return localPort;
	}

	public void setDel(SJAbstractSocket del) {
		this.del = del;
	}
	
	public boolean isLocal() {
		return local;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name=n;
	}
	
	public String toString() {
		return "I am a Global Participant " + name;
	}
	
	/*********DELEGATED METHODS **************************/
	
	public boolean arrived() {
		return del.arrived();
	}

	public void close() {
		del.close();
	}

	public void copy(Object o) throws SJIOException {
		del.copy(o);
	}

	public SJSessionType currentSessionType() {
		return del.currentSessionType();
	}

	public void delegateSession(SJAbstractSocket s, String encoded)
			throws SJIOException {
		del.delegateSession(s, encoded);
	}

	public boolean equals(Object obj) {
		return del.equals(obj);
	}

	public SJConnection getConnection() {
		return del.getConnection();
	}

	public String getHostName() {
		return del.getHostName();
	}

	public SJSessionType getInitialRuntimeType() throws SJIOException {
		return del.getInitialRuntimeType();
	}

	public String getLocalHostName() {
		return del.getLocalHostName();
	}

	//public int getLocalPort() {
	//	return del.getLocalPort();
	//}

	public SJSessionParameters getParameters() {
		return del.getParameters();
	}

	public SJProtocol getProtocol() {
		return del.getProtocol();
	}

	public SJSerializer getSerializer() {
		return del.getSerializer();
	}

	public SJSessionProtocols getSJSessionProtocols() {
		return del.getSJSessionProtocols();
	}

	public SJStateManager getStateManager() {
		return del.getStateManager();
	}

	public int hashCode() {
		return del.hashCode();
	}

	public String inlabel() throws SJIOException {
		return del.inlabel();
	}

	public boolean insync() throws SJIOException {
		return del.insync();
	}

	public boolean interruptibleOutsync(boolean condition) throws SJIOException {
		return del.interruptibleOutsync(condition);
	}

	public boolean interruptingInsync(boolean condition,
			boolean peerInterruptible) throws SJIOException {
		return del.interruptingInsync(condition, peerInterruptible);
	}

	public boolean isActive() {
		return del.isActive();
	}

	public boolean isOriginalRequestor() {
		return del.isOriginalRequestor();
	}

	public boolean isPeerInterruptibleOut(boolean selfInterrupting)
			throws SJIOException {
		return del.isPeerInterruptibleOut(selfInterrupting);
	}

	public boolean isPeerInterruptingIn(boolean selfInterruptible)
			throws SJIOException {
		return del.isPeerInterruptingIn(selfInterruptible);
	}

	public void outlabel(String lab) throws SJIOException {
		del.outlabel(lab);
	}

	public boolean outsync(boolean b) throws SJIOException {
		return del.outsync(b);
	}

	public void pass(Object o) throws SJIOException {
		del.pass(o);
	}

	public Object receive() throws SJIOException, ClassNotFoundException {
		return del.receive();
	}

	public boolean receiveBoolean() throws SJIOException {
		return del.receiveBoolean();
	}

	public SJService receiveChannel(String encoded) throws SJIOException {
		return del.receiveChannel(encoded);
	}

	public double receiveDouble() throws SJIOException {
		return del.receiveDouble();
	}

	public int receiveInt() throws SJIOException {
		return del.receiveInt();
	}

	public SJAbstractSocket receiveSession(String encoded,
			SJSessionParameters params) throws SJIOException {
		return del.receiveSession(encoded, params);
	}

	public void reconnect(SJConnection conn) throws SJIOException {
		del.reconnect(conn);
	}

	public boolean recurse(String lab) throws SJIOException {
		return del.recurse(lab);
	}

	public boolean recursionEnter(String lab) throws SJIOException {
		return del.recursionEnter(lab);
	}

	public boolean recursionExit() throws SJIOException {
		return del.recursionExit();
	}

	public SJSessionType remainingSessionType() {
		return del.remainingSessionType();
	}
 
	public void send(Object o) throws SJIOException {
		del.send(o);
	}

	public void sendBoolean(boolean b) throws SJIOException {
		del.sendBoolean(b);
	}

	public void sendChannel(SJService c, String encoded) throws SJIOException {
		del.sendChannel(c, encoded);
	}

	public void sendDouble(double d) throws SJIOException {
		del.sendDouble(d);
	}

	public void sendInt(int i) throws SJIOException {
		del.sendInt(i);
	}

	public void setHostName(String hostName) {
		del.setHostName(hostName);
	}

	public void setPort(int port) {
		del.setPort(port);
	}

	public void setStateManager(SJStateManager sm) {
		del.setStateManager(sm);
	}

	public boolean supportsBlocking() {
		return del.supportsBlocking();
	}

	//public String toString() {
	//	return del.toString();
	//}

	public TransportSelector transportSelector() {
		return del.transportSelector();
	}

	public boolean typeStartsWithOutput() throws SJIOException {
		return del.typeStartsWithOutput();
	}

	public void updateStaticAndRuntimeTypes(SJSessionType staticType,
			SJSessionType runtimeType) throws SJIOException {
		del.updateStaticAndRuntimeTypes(staticType, runtimeType);
	}

	public int getPort() {
		return 0;
	}
}