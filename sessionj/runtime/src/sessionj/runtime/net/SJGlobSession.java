package sessionj.runtime.net;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Collections;
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
		LinkedList<SJGlobParticipant> invitationList = new LinkedList<SJGlobParticipant>();
		LinkedList<String> delegationList = new LinkedList<String>();
		
		for(Field f: getClass().getDeclaredFields()) {
			
			if (f.getType().equals(SJGlobParticipant.class) && (!((SJGlobParticipant) f.get(this)).isLocal())) {
				
				invitationList.add((SJGlobParticipant) f.get(this));
				delegationList.add(((SJGlobParticipant) f.get(this)).getName());
			}
		}
		
		for(SJGlobParticipant gp: invitationList){
			
			final SJService serv = SJService.create(null, gp.getHostname(), gp.getRemotePort());
			gp.setDel(serv.request(params));
			
			gp.send(getClass());
			delegationList.remove(gp.getName());
			gp.send(delegationList);
		}
		
		
		} catch (Exception e) {e.printStackTrace();} 
	}
	
	public void acceptInvite() {
//		throws SJIOException, SJSessionParametersException,
//		SJIncompatibleSessionException, IllegalArgumentException, IllegalAccessException, 
//		ClassNotFoundException 
		
		try {
			
			SJSessionParameters params = SJTransportUtils.createSJSessionParameters("d", "d");
			LinkedList<SJGlobParticipant> acceptanceList = new LinkedList<SJGlobParticipant>();
			
			for(Field f: getClass().getDeclaredFields()) {
				
				if (f.getType().equals(SJGlobParticipant.class) && (!((SJGlobParticipant) f.get(this)).isLocal())) {			
					acceptanceList.add((SJGlobParticipant) f.get(this));
				}
			}	
			
			for(SJGlobParticipant gp: acceptanceList) {
				
				gp.servsocket = SJServerSocketImpl.create(null, gp.getLocalPort(), params);
				System.out.println("(AI) WAITING: " + gp.servsocket.toString() + "\n");
			}
			
			for(SJGlobParticipant gp: acceptanceList) {
				
				if(gp.servsocket.isOpen) {
					gp.setDel(gp.servsocket.accept());	
					System.out.println("(AI) ACTIVE: " + gp.servsocket.toString() + "\n");
					
					if (!getClass().equals(gp.receive())) {
						System.out.println("NO. NOT SAME CLASS");
					}
				
					LinkedList<String> delegatedInvites = (LinkedList<String>) gp.receive();
					System.out.println("Receiving delegationList : " + delegatedInvites + " from " + gp.getName()  + "\n");
					this.inviteOthers(delegatedInvites, acceptanceList);
				}
			}
		}		
		catch (Exception e) {e.printStackTrace();}
	}
	
	private void inviteOthers(LinkedList<String> delegatedInvitations, LinkedList<SJGlobParticipant> acceptanceList) {
			
		try {
			SJSessionParameters params = SJTransportUtils.createSJSessionParameters("d", "d");
			
			LinkedList<SJGlobParticipant> acceptanceList2 = new LinkedList<SJGlobParticipant>(acceptanceList);		
			Collections.reverse(acceptanceList2);
			
			for (SJGlobParticipant gp: acceptanceList2) {
				
				if(delegatedInvitations.contains(gp.getName())) {
					gp.servsocket.close();
					System.out.println("(IO) CLOSED: " + gp.servsocket.toString()  + "\n");
					
					gp.serv = SJService.create(null, gp.getHostname(), gp.getRemotePort());
					gp.setDel(gp.serv.request(params));
					gp.send(getClass());
					gp.send(new LinkedList<String>());
				}	
			}
		}
		catch (Exception e) {e.printStackTrace();}	
	}
}
