package sessionj.ast.typenodes;

import polyglot.util.Position;
import polyglot.ast.TypeNode;
import static sessionj.SJConstants.*;

public class SJGlobSendNode_c extends SJSendNode_c implements SJGlobSendNode, SJGlobTypeNode
{
	private SJGlobElementPrefixNode prefix;
	
	public SJGlobSendNode_c (Position pos, TypeNode messageType, SJGlobElementPrefixNode prefix)
	{
		super(pos, messageType);
		this.prefix = prefix; 
	}
	
    public String nodeToString()
	{
		String message = messageType().toString(); // toString enough for messageType? or need to manually get full name?
		return prefix.toString() + SJ_STRING_SEND_OPEN + message + SJ_STRING_SEND_CLOSE;
	}
    
    public SJGlobElementPrefixNode getPrefix() 
    {
    	return this.prefix;
    }
}