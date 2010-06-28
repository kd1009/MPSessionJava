package sessionj.ast.typenodes;

import polyglot.ast.TypeNode;
import polyglot.util.Position;
import polyglot.types.SemanticException;
import polyglot.types.Type;

import static sessionj.SJConstants.*;
import sessionj.types.SJTypeSystem;
import sessionj.types.sesstypes.SJMessageCommunicationType;

public class SJSendNode_c extends SJMessageCommunicationNode_c implements SJSendNode
{
	public SJSendNode_c(Position pos, TypeNode messageType)
	{
		super(pos, messageType);
	}

    protected SJMessageCommunicationType createType(SJTypeSystem ts, Type messageType) throws SemanticException {
        return ts.SJSendType(messageType);
    }

    public String nodeToString()
	{
		String message = messageType().toString(); // toString enough for messageType? or need to manually get full name?

		return SJ_STRING_SEND_OPEN + message + SJ_STRING_SEND_CLOSE;
	}
}
