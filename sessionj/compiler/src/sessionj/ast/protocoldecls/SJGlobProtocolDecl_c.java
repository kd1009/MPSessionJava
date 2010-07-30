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

	public SJGlobProtocolDecl_c (Position pos, Flags flags, Id name, SJGlobTypeNode glob_session) 
	{	
		super(pos, flags, name.id(name.id()+"AUTO"), null, new LinkedList<Object>(), new ClassBody_c(glob_session.position(), new LinkedList<Object>()));
		System.out.println("name: " + this.name);
		this.glob_session = glob_session;
		this.protocol_name = name;
		
		System.out.println("Protocol Name: " + protocol_name);
		
		//return this;
		
//		SJGlobElementPrefixNode temp = glob_session.getPrefix();
//		System.out.println(glob_session.getPrefix().toString());
//		System.out.println(((SJGlobTypeNode)glob_session.child()).getPrefix().toString());
//		System.out.println(((SJGlobTypeNode)((SJGlobTypeNode)glob_session.child()).child()).getPrefix().toString());
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
}
