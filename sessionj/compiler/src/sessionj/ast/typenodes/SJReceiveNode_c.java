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

public class SJReceiveNode_c extends SJMessageCommunicationNode_c implements SJReceiveNode
{
	public SJReceiveNode_c(Position pos, TypeNode messageType)
	{
		super(pos, messageType);
	}

    protected SJMessageCommunicationType createType(SJTypeSystem ts, Type messageType) throws SemanticException {
        return ts.SJReceiveType(messageType);
    }

    public String nodeToString()
	{
		String message = messageType().toString(); // toString enough for messageType? or need to manually get full name?

		return SJ_STRING_RECEIVE_OPEN + message + SJ_STRING_RECEIVE_CLOSE;
	}
}
