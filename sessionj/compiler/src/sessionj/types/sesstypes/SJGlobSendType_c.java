package sessionj.types.sesstypes;

import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;

public class SJGlobSendType_c extends SJSendType_c implements SJGlobSendType
{
	private Type prefixType;
	
	protected SJGlobSendType_c(TypeSystem ts, Type prefixType)
	{
		super(ts);
		this.prefixType = prefixType;
	}
	
	public SJGlobSendType_c(TypeSystem ts, Type messageType, Type prefixType) throws SemanticException
	{
		super(ts, messageType);
		this.prefixType = prefixType;
	}
	
	protected boolean eligibleForEquals(SJSessionType st)
	{
		return st instanceof SJGlobSendType;
	}

	protected boolean eligibleForSubtype(SJSessionType st)
	{
		return st instanceof SJGlobSendType;
	}
	
	protected boolean eligibleForDualtype(SJSessionType st)
	{
		return false;
	}
/*	
	protected boolean compareNode(NodeComparison op, SJSessionType st)
	{	
		switch (op)
		{
			case EQUALS: return messageType().equals(((SJGlobSendType) st).messageType()); 
			case SUBTYPE: return ((SJSendType) st).messageType().isSubtype(messageType()); // Contravariant.  
			case DUALTYPE: return messageType().isSubtype(((SJReceiveType) st).messageType());
		}
		
		throw new RuntimeException("[SJSendType_c] Shouldn't get here: " + op);
	}
*/	
	public SJSessionType nodeSubsume(SJSessionType st) throws SemanticException
	{
		if (!(st instanceof SJGlobSendType))
		{
			throw new SemanticException("[SJGlobSendType_c] Not subsumable: " + this + ", " + st);
		}

		return typeSystem().SJGlobSendType(subsumeSendTypes(messageType(), ((SJGlobSendType) st).messageType()), prefixType);
	}
/*
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
*/
    public SJSessionType nodeDual() throws SemanticException {
        
    	if(this instanceof SJGlobSendType)
    	{
    		throw new SemanticException("[SJGlobSendType_c] No dual type for globals: " + this);
    	}
		
    	return null; // should never get here anyway!
    }
/*
    public static Type subsumeSendTypes(Type t1, Type t2) throws SemanticException
    {
        return SJCompilerUtils.subsumeMessageTypes(t1, t2, true);
    }

    @Override
    public boolean startsWithOutput() {
        return true;
    }*/
}