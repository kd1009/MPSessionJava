package sessionj.ast.protocoldecls;

import java.util.*;

import polyglot.ast.*;
import polyglot.types.Flags;
import polyglot.util.Position;

import sessionj.ast.typenodes.SJGlobTypeNode;
import sessionj.ast.typenodes.SJGlobElementPrefixNode;

public class SJGlobProtocolDecl_c extends ClassDecl_c implements SJGlobProtocolDecl 
{	
	private SJGlobTypeNode glob_session;
	private Id protocol_name;
	private LinkedList<String> participants;

	public SJGlobProtocolDecl_c (Position pos, Flags flags, Id name, SJGlobTypeNode glob_session) 
	{	
		super(pos, flags, name.id(name.id()+"AUTO"), null, new LinkedList<Object>(), new ClassBody_c(pos, new LinkedList<Object>()));
		this.glob_session = glob_session;
		this.protocol_name = name;
		this.participants = createParticipantList(glob_session);

		System.out.println("Compiler class name: " + this.name);
		System.out.println("Actual Protocol Name: " + this.protocol_name);
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
	
	public String globProtocolName()
	{
		return protocol_name.id();
	}
	
	private LinkedList<String> createParticipantList(SJGlobTypeNode glob_session) 
	{

		LinkedList<String> participants = new LinkedList<String>();
		
		//add the participants from the parent element of the session type
		participants.add(glob_session.getPrefix().getPrincipal().toString());
		participants.add(glob_session.getPrefix().getPartner().toString());
		
		//check children elements for participants not yet listed and add if necessary
		SJGlobTypeNode child = glob_session.globChild();
		while(child != null) {
			
			if(!(participants.contains(child.getPrefix().getPrincipal().toString())))
			{
				participants.add(child.getPrefix().getPrincipal().toString());
			}
			if(!(participants.contains((child.getPrefix().getPartner().toString()))))
			{
				participants.add(child.getPrefix().getPartner().toString());
			}
			child = child.globChild();
		}
		
		return participants;
	}
	
	public LinkedList<String> getParticipantList() 
	{
		return this.participants;
	}
	
}
