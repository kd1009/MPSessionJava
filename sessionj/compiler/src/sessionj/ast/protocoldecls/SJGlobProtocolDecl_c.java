package sessionj.ast.protocoldecls;

import java.util.*;

import polyglot.ast.*;
import polyglot.types.Flags;
import polyglot.util.Position;

import static sessionj.SJConstants.*;
import sessionj.ast.typenodes.SJGlobTypeNode;

public class SJGlobProtocolDecl_c extends ClassDecl_c implements SJGlobProtocolDecl 
{	
	private SJGlobTypeNode glob_session;
	private LinkedList<Id> participants;

	public SJGlobProtocolDecl_c (Position pos, Flags flags, Id name, TypeNode superClass, SJGlobTypeNode glob_session) 
	{	
		super(pos, flags.Public(), name, superClass, new LinkedList<Object>(), new ClassBody_c(pos, makeGlobProtMembers (pos, glob_session)));
		this.glob_session = glob_session;
		this.participants = createParticipantList(glob_session);

		System.out.println("Inferred following participants: " + this.participants + "\n");		
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
	
	private static LinkedList<Id> createParticipantList(SJGlobTypeNode glob_session) 
	{
		System.out.println("\nInferring participant list.");
		LinkedList<Id> participants = new LinkedList<Id>();
		LinkedList<String> names = new LinkedList<String>();
		
		//add the participants from the parent element of the session type
		participants.add(glob_session.getPrefix().getPrincipal());
		participants.add(glob_session.getPrefix().getPartner());
		
		//add their names to the unique name list 
		names.add(glob_session.getPrefix().getPrincipal().toString());
		names.add(glob_session.getPrefix().getPartner().toString());
		
		//check children elements for participants not yet listed and add if necessary
		SJGlobTypeNode child = glob_session.child();
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
			child = child.child();
			
		}
		
		return participants;
	}
	
	private static LinkedList<ClassMember> makeGlobProtMembers(Position pos, SJGlobTypeNode glob_session) {
		
		List<ClassMember> members = new LinkedList<ClassMember>();
		
		for (Id id: createParticipantList(glob_session))
		{
			LinkedList<String> args = new LinkedList<String>();
			args.add(id.id());
			
			ClassMember mb = new FieldDecl_c(
					pos, 
					Flags.PUBLIC, 
					new CanonicalTypeNode_c(pos, SJ_GLOB_PARTICIPANT_TYPE), 
					id, 
					new NullLit_c(pos));
			members.add(mb);
			System.out.println("Found and added raw participant member: " + mb);
		}
		
		return (LinkedList<ClassMember>) members; 
	}
	
	public LinkedList<Id> getParticipantList() 
	{
		return this.participants;
	}
	
}
