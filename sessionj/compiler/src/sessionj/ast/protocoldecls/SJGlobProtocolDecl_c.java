package sessionj.ast.protocoldecls;

import java.util.*;

import polyglot.ast.*;
import polyglot.types.Flags;
import polyglot.util.Position;
import sessionj.ast.typenodes.SJTypeNode;

public class SJGlobProtocolDecl_c extends ClassDecl_c implements SJGlobProtocolDecl 
{	
	private SJTypeNode glob_session;

	public SJGlobProtocolDecl_c (Position pos, Flags flags, Id name, SJTypeNode glob_session) 
	{
		super(pos, flags, name, null, new LinkedList<Object>(), new ClassBody_c(glob_session.position(), new LinkedList<Object>()));
		System.out.println("name: " + name);
		this.glob_session = glob_session;
	}
	
	public SJTypeNode sessionType()
	{
		return glob_session;
	}
	
	public SJGlobProtocolDecl sessionType(SJTypeNode tn)
	{		
		this.glob_session = tn;
		
		return this;
	}
}
