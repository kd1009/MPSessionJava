package sessionj.ast.typenodes;

import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import sessionj.util.SJLabel;
import sessionj.types.SJTypeSystem;

public class SJGlobElementPrefixNode_c extends SJTypeNode_c implements SJGlobElementPrefixNode
{
	private SJLabel main_participant;
	private SJLabel partner;
	
	public SJGlobElementPrefixNode_c (Position pos, SJLabel a, SJLabel b)
	{
		super(pos);
		main_participant = a;
		partner = b;
	}
	
	// Duplicated from corresponding SJSessionType implementations. But may be needed before types have been built.
	public String nodeToString()
	{
		return new String("|" + main_participant.toString() + "," + partner.toString() + "|");
	}

    public SJTypeNode disambiguateSJTypeNode(ContextVisitor cv, SJTypeSystem sjts) {
        return type(sjts.SJGlobElementPrefixType());
    }
	
}