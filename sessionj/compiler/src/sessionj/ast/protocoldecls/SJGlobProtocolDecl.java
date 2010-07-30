package sessionj.ast.protocoldecls;

import polyglot.ast.ClassDecl;
import polyglot.ast.Id;
import sessionj.ast.typenodes.SJGlobTypeNode;

public interface SJGlobProtocolDecl extends ClassDecl 
{
	//public SJGlobalTypeNode sessionType();
	public String globProtocolName();
}