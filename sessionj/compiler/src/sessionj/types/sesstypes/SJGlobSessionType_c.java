package sessionj.types.sesstypes;

import polyglot.types.*;


public class SJGlobSessionType_c extends SJSessionType_c implements SJGlobSessionType
{
	private SJSessionType sessionType;
	private SJGlobElementPrefixType prefixType;
	
	public SJGlobSessionType_c(TypeSystem ts)
	{
		super(ts);
	}
	
	public SJGlobSessionType_c(TypeSystem ts, SJGlobElementPrefixType prefixType, SJSessionType sessionType)
	{
		super(ts);
		this.sessionType = sessionType;
		this.prefixType = prefixType;
	}
	
	protected boolean eligibleForEquals(SJSessionType st)
	{
		return st instanceof SJGlobSessionType;
	}

	protected boolean eligibleForSubtype(SJSessionType st)
	{
		return false;	//st instanceof SJGlobSessionType;
	}
	
	protected boolean eligibleForDualtype(SJSessionType st)
	{
		return false;
	}
	
	protected boolean compareNode(NodeComparison op, SJSessionType st)
	{	
		switch (op)
		{
			case EQUALS: return prefixType.equals(((SJGlobSessionType) st).prefixType())
								&& sessionType.equals(((SJGlobSessionType) st).sessionType()); 
		}
		
		throw new RuntimeException("[SJGlobSessionType_c] Shouldn't get here: " + op);
	}
	
	public SJGlobElementPrefixType prefixType()
	{
		return prefixType;
	}
	
	public SJSessionType sessionType()
	{
		return sessionType;
	}
	
	public String nodeToString()
	{
		return prefixType.nodeToString() + sessionType.nodeToString();
	}
	
	
	// not sure about the following methods
	
	public SJSessionType nodeClone()
	{
		return typeSystem().SJGlobSessionType(prefixType, sessionType);
	}
	
	public boolean nodeWellFormed()
	{
		return true; // just for now
	}
	
	public SJSessionType nodeSubsume(SJSessionType st) throws SemanticException
	{
		throw new SemanticException("[SJSendType_c] Not subsumable: " + this + ", " + st);
	}

}
