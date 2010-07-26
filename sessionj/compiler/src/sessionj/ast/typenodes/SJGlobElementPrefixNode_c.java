package sessionj.ast.typenodes;

import polyglot.ast.Id;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import sessionj.types.SJTypeSystem;

public class SJGlobElementPrefixNode_c extends SJTypeNode_c implements SJGlobElementPrefixNode
{
	private Id principal;
	private Id partner;
	
	public SJGlobElementPrefixNode_c (Position pos, Id a, Id b)
	{
		super(pos);
		principal = a;
		partner = b;
	}
	
	// Duplicated from corresponding SJSessionType implementations. But may be needed before types have been built.
	public String nodeToString()
	{
		return new String("|" + principal.id() + "," + partner.id() + "|");
	}

    public SJTypeNode disambiguateSJTypeNode(ContextVisitor cv, SJTypeSystem sjts) {
        return type(sjts.SJGlobElementPrefixType());
    }
    
    public Id getPrincipal()
    {
    	return principal;
    }
	
    public Id getPartner()
    {
    	return partner;
    }
}