package sessionj.types.sesstypes;

import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import static sessionj.SJConstants.SJ_VERSION;

public class SJGlobElementPrefixType_c extends SJSessionType_c implements SJGlobElementPrefixType
{
	public static final long serialVersionUID = SJ_VERSION;

	public SJGlobElementPrefixType_c(TypeSystem ts)
	{
		super(ts);
	}

	protected boolean eligibleForEquals(SJSessionType st)
	{
		return st instanceof SJGlobElementPrefixType;
	}
	
	protected boolean eligibleForSubtype(SJSessionType st)
	{
		return false;
	}
	
	protected boolean eligibleForDualtype(SJSessionType st)
	{
		return false;
	}
	
	protected boolean compareNode(NodeComparison op, SJSessionType st)
	{
		return true; // Checking eligibleFor... is already enough.	
	}
	
	// Could refine the return types for this and nodeClone, but not very useful.
	public SJSessionType nodeSubsume(SJSessionType st) throws SemanticException
	{
		if (!(st instanceof SJGlobElementPrefixType))
		{
			throw new SemanticException("[SJGlobElementPrefixType_c] Not subsumable: " + this + ", " + st);
		}

		return typeSystem().SJGlobElementPrefixType();
	}

	public SJSessionType nodeClone()
	{
		return typeSystem().SJGlobElementPrefixType();
	}

	public String nodeToString()
	{
		return new String("SJGlobElementPrefixType");
	}

    public SJSessionType nodeDual() {
        return typeSystem().SJGlobElementPrefixType();
    }
    
	public boolean nodeWellFormed() 
	{
		return false;
	}
}
