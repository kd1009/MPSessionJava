package sessionj.ast.typenodes;

import polyglot.ast.Id;

public interface SJGlobElementPrefixNode extends SJTypeNode
{
	public Id getPrincipal();
	public Id getPartner();
}