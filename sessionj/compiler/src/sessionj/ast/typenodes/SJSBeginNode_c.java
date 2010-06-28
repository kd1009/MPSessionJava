package sessionj.ast.typenodes;

import polyglot.util.Position;
import polyglot.visit.ContextVisitor;

import static sessionj.SJConstants.*;
import sessionj.types.SJTypeSystem;

public class SJSBeginNode_c extends SJBeginNode_c implements SJSBeginNode
{
	public SJSBeginNode_c(Position pos)
	{
		super(pos);
	}

	// Duplicated from corresponding SJSessionType implementations. But may be needed before types have been built.
	public String nodeToString()
	{
		return SJ_STRING_SBEGIN;
	}

    public SJBeginNode disambiguateSJTypeNode(ContextVisitor cv, SJTypeSystem sjts) {
        return (SJBeginNode) type(sjts.SJSBeginType());
    }
}
