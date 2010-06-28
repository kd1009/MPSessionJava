package sessionj.types.sesstypes;

import polyglot.types.*;
import sessionj.types.sesstypes.SJSessionType_c.NodeComparison;

import static sessionj.SJConstants.*;

public class SJSBeginType_c extends SJBeginType_c implements SJSBeginType
{
	public static final long serialVersionUID = SJ_VERSION;

	public SJSBeginType_c(TypeSystem ts)
	{
		super(ts);
	}

	protected boolean eligibleForEquals(SJSessionType st)
	{
		return st instanceof SJSBeginType;
	}
	
	protected boolean eligibleForSubtype(SJSessionType st)
	{
		return st instanceof SJSBeginType;
	}
	
	protected boolean eligibleForDualtype(SJSessionType st)
	{
		return st instanceof SJCBeginType;
	}
	
	protected boolean compareNode(NodeComparison op, SJSessionType st)
	{
		return true; // Checking eligibleFor... is already enough.	
	}

	// Could refine the return types for this and nodeClone, but not very useful.
	public SJSessionType nodeSubsume(SJSessionType st) throws SemanticException
	{
		if (!(st instanceof SJSBeginType))
		{
			throw new SemanticException("[SJSBeginType_c] Not subsumable: " + this + ", " + st);
		}

		return typeSystem().SJSBeginType();
	}
	
	public SJSessionType nodeClone()
	{
		return typeSystem().SJSBeginType();
	}

	public String nodeToString()
	{
		return SJ_STRING_SBEGIN;
	}

    public SJSessionType nodeDual() {
        return typeSystem().SJCBeginType();
    }
}
