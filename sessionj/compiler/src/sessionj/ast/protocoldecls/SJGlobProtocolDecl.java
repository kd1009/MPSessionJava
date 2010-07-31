package sessionj.ast.protocoldecls;

import java.util.*;

import polyglot.ast.ClassDecl;
import polyglot.ast.Id;
import sessionj.ast.typenodes.SJGlobTypeNode;

public interface SJGlobProtocolDecl extends ClassDecl 
{
	//public SJGlobalTypeNode sessionType();
	public String globProtocolName();
	public LinkedList<String> getParticipantList();
}