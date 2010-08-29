package sessionj.runtime.net;

import java.lang.reflect.Field;
import java.util.LinkedList;
import sessionj.runtime.SJIOException;
import sessionj.runtime.transport.SJTransportUtils;

public abstract class SJGlobSession 
{
	
	public void invite() 
	
	//throws SecurityException, NoSuchMethodException, 
	//			IllegalArgumentException, IllegalAccessException, SJIOException, 
	//			SJSessionParametersException, SJIncompatibleSessionException 
		{
		try {
		
		SJSessionParameters params = SJTransportUtils.createSJSessionParameters("d", "d");
		
		for(Field f: getClass().getDeclaredFields()) {
			
			if (f.getType().equals(SJGlobParticipant.class) && (!((SJGlobParticipant) f.get(this)).isLocal())) {
				SJGlobParticipant p = (SJGlobParticipant) f.get(this);;	
				
				String hostname = p.getHostname();
				int port = p.getPort();
				final SJService serv = SJService.create(null, hostname, port);
				p.setDel(serv.request(params));
				
				f.set(this, p);
				((SJGlobParticipant) f.get(this)).sendInt(10);
				
			}
		}
		
		} catch (Exception e) {e.printStackTrace();} 
	}
	
	public void acceptInvite() {
//		throws SJIOException, SJSessionParametersException,
//		SJIncompatibleSessionException, IllegalArgumentException, IllegalAccessException, 
//		ClassNotFoundException 
		
		int c = 0;
		
		try {

		SJSessionParameters params = SJTransportUtils.createSJSessionParameters("d", "d");
		
		for(Field f: getClass().getDeclaredFields()) {
			
			if (f.getType().equals(SJGlobParticipant.class)&& (!((SJGlobParticipant) f.get(this)).isLocal())) {
				SJGlobParticipant p = (SJGlobParticipant) f.get(this);
				final SJServerSocket servsocket = SJServerSocketImpl.create(null, 1050+c, params);
				p.setDel(servsocket.accept());
				
				f.set(this, p);
				System.out.println("Receiving integer: " + ((SJGlobParticipant) f.get(this)).receiveInt()
						+ " from " + ((SJGlobParticipant) f.get(this)).getName());
				c++;
			}
		}
		
		}
		catch (Exception e) {e.printStackTrace();}
		
	}
}