package sessionj.types.sesstypes;

import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import static sessionj.SJConstants.*;
import sessionj.util.SJCompilerUtils;

public class SJSendType_c extends SJMessageCommunicationType_c implements SJSendType
{
	public static final long serialVersionUID = SJ_VERSION;

	protected SJSendType_c(TypeSystem ts)
	{
		super(ts);
	}
	
	public SJSendType_c(TypeSystem ts, Type messageType) throws SemanticException
	{
		super(ts, messageType); 
	}
	
	protected boolean eligibleForEquals(SJSessionType st)
	{
		return st instanceof SJSendType;
	}

	protected boolean eligibleForSubtype(SJSessionType st)
	{
		return st instanceof SJSendType;
	}
	
	protected boolean eligibleForDualtype(SJSessionType st)
	{
		return st instanceof SJReceiveType;
	}
	
	protected boolean compareNode(NodeComparison op, SJSessionType st)
	{	
		switch (op)
		{
			case EQUALS: return messageType().equals(((SJSendType) st).messageType()); 
			case SUBTYPE: return ((SJSendType) st).messageType().isSubtype(messageType()); // Contravariant.  
			case DUALTYPE: return messageType().isSubtype(((SJReceiveType) st).messageType());
		}
		
		throw new RuntimeException("[SJSendType_c] Shouldn't get here: " + op);
	}
	
	public SJSessionType nodeSubsume(SJSessionType st) throws SemanticException
	{
		if (!(st instanceof SJSendType))
		{
			throw new SemanticException("[SJSendType_c] Not subsumable: " + this + ", " + st);
		}

		return typeSystem().SJSendType(subsumeSendTypes(messageType(), ((SJSendType) st).messageType()));
	}

	protected SJSendType skeleton()
	{
		return new SJSendType_c(typeSystem());
	}
	
	protected String messageCommunicationOpen()
	{
		return SJ_STRING_SEND_OPEN;
	}
	
	protected String messageCommunicationClose()
	{
		return SJ_STRING_SEND_CLOSE;
	}

    public SJSessionType nodeDual() throws SemanticException {
        return typeSystem().SJReceiveType(messageType());
    }

    public static Type subsumeSendTypes(Type t1, Type t2) throws SemanticException
    {
        return SJCompilerUtils.subsumeMessageTypes(t1, t2, true);
    }

    @Override
    public boolean startsWithOutput() {
        return true;
    }
}
