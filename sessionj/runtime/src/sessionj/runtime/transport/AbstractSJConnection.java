package sessionj.runtime.transport;

public abstract class AbstractSJConnection extends AbstractWithTransport implements SJConnection {
    protected AbstractSJConnection(SJTransport transport) {
	    super(transport);
    }
	
    public boolean supportsBlocking() {
        return true;
    }

	public boolean arrived() {
		throw new UnsupportedOperationException("Does not support non-blocking mode");
	}

}
