package sessionj.runtime.net;

import java.lang.reflect.Field;
import java.util.LinkedList;
import sessionj.runtime.SJIOException;
import sessionj.runtime.transport.SJTransportUtils;

public abstract class SJGlobSession 
{
	
	public void invite() throws SecurityException, NoSuchMethodException, 
				IllegalArgumentException, IllegalAccessException, SJIOException, 
				SJSessionParametersException, SJIncompatibleSessionException {
	
		SJSessionParameters params = SJTransportUtils.createSJSessionParameters("d", "d");
		LinkedList<SJGlobParticipant> participants = new LinkedList<SJGlobParticipant>();
		
		// put all participants apart from yourself in a LinkedList
		for(Field f: getClass().getDeclaredFields()) {
			
			if (f.getType().equals(SJGlobParticipant.class)) {
				SJGlobParticipant p = (SJGlobParticipant) f.get(this);
				if(!p.isLocal())
				participants.add(p);
			}
		}
		
		for(SJGlobParticipant p: participants) {		
				
				// send request to first on the list
				String hostname = p.getHostname();
				int port = p.getPort();
				final SJService serv = SJService.create(null, hostname, port);
				p.setDel(serv.request(params));
		
				// remove the participant we just requested a session with
				participants.removeFirst();
				
				// send the list of participants that have to be invited by the participant
				p.send(participants);
		}	
	}
	
	public void acceptInvite() throws SJIOException, SJSessionParametersException,
				SJIncompatibleSessionException, IllegalArgumentException, IllegalAccessException, 
				ClassNotFoundException {

		SJSessionParameters params = SJTransportUtils.createSJSessionParameters("d", "d");
		LinkedList<SJGlobParticipant> participants = new LinkedList<SJGlobParticipant>();
		
		// put all participants apart from yourself in a LinkedList
		for(Field f: getClass().getDeclaredFields()) {
			
			if (f.getType().equals(SJGlobParticipant.class)) {
				SJGlobParticipant p = (SJGlobParticipant) f.get(this);
				if(!p.isLocal())
				participants.add(p);
			}
		}
		
		for(SJGlobParticipant p: participants) {		
				
			final SJServerSocket servsocket = SJServerSocketImpl.create(null, 1050, params);
			p.setDel(servsocket.accept());
			
			// receive list of participants to whom a connection should be initiated
			p.receive();
			
		}	
		
	}
}