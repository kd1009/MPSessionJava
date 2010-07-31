package sessionj.ast.protocoldecls;

import java.util.*;

import polyglot.ast.*;
import polyglot.types.Flags;
import polyglot.util.Position;

import static sessionj.SJConstants.*;
import sessionj.ast.typenodes.SJGlobTypeNode;
import sessionj.ast.typenodes.SJGlobElementPrefixNode;

public class SJGlobProtocolDecl_c extends ClassDecl_c implements SJGlobProtocolDecl 
{	
	private SJGlobTypeNode glob_session;
	private LinkedList<Id> participants;

	public SJGlobProtocolDecl_c (Position pos, Flags flags, Id name, SJGlobTypeNode glob_session) 
	{	
		super(pos, flags, name, null, new LinkedList<Object>(), new ClassBody_c(pos, new LinkedList<Object>()));
		this.glob_session = glob_session;
		this.participants = createParticipantList(glob_session);

		System.out.println("Protocol class name: " + this.name);
		System.out.println("Protocol Participants: " + this.participants);
		
	}
	
	public SJGlobTypeNode sessionType()
	{
		return glob_session;
	}
	
	public SJGlobProtocolDecl sessionType(SJGlobTypeNode tn)
	{		
		this.glob_session = tn;
		
		return this;
	}
	
	private LinkedList<Id> createParticipantList(SJGlobTypeNode glob_session) 
	{

		LinkedList<Id> participants = new LinkedList<Id>();
		LinkedList<String> names = new LinkedList<String>();
		
		//add the participants from the parent element of the session type
		participants.add(glob_session.getPrefix().getPrincipal());
		participants.add(glob_session.getPrefix().getPartner());
		
		//add their names to the unique name list 
		names.add(glob_session.getPrefix().getPrincipal().toString());
		names.add(glob_session.getPrefix().getPartner().toString());
		
		//check children elements for participants not yet listed and add if necessary
		SJGlobTypeNode child = glob_session.globChild();
		while(child != null) {
			
			if(!(names.contains(child.getPrefix().getPrincipal().toString())))
			{
				participants.add(child.getPrefix().getPrincipal());
				names.add(child.getPrefix().getPrincipal().toString());
			}
			if(!(names.contains((child.getPrefix().getPartner().toString()))))
			{
				participants.add(child.getPrefix().getPartner());
				names.add(child.getPrefix().getPartner().toString());
			}
			child = child.globChild();
			
		}
		
		return participants;
	}
	
	public LinkedList<Id> getParticipantList() 
	{
		return this.participants;
	}
	
}
