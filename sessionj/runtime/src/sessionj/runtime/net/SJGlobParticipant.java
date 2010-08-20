package sessionj.runtime.net;

public class SJGlobParticipant 
{
	private String name;
	
	public void setRemote(String host, int port) {}
	public void setLocal() {}
	public String getHostname() { return "localhost"; }
	public int getPort() { return 42; }
	
	public SJGlobParticipant (String name)
	{
		this.name = name;
	}

}