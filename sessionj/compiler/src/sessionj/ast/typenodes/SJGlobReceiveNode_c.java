package sessionj.ast.typenodes;

import polyglot.ast.TypeNode;
import polyglot.util.Position;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.visit.ContextVisitor;
import polyglot.frontend.Job;

import static sessionj.SJConstants.*;
import sessionj.types.SJTypeSystem;
import sessionj.types.sesstypes.SJMessageCommunicationType;
import sessionj.util.SJCompilerUtils;

public class SJGlobReceiveNode_c extends SJReceiveNode_c implements SJGlobReceiveNode
{
	private TypeNode prefix;
	
	public SJGlobReceiveNode_c (Position pos, TypeNode messageType, TypeNode prefixType)
	{
		super(pos, messageType);
		this.prefix = prefixType;
	}
	
    public String nodeToString()
	{
		String message = messageType().toString(); // toString enough for messageType? or need to manually get full name?
		return prefix.toString() + SJ_STRING_RECEIVE_OPEN + message + SJ_STRING_RECEIVE_CLOSE;
	}
}
