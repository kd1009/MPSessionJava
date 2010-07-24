package sessionj.ast.typenodes;

import sessionj.util.SJLabel;

public interface SJGlobElementPrefixNode extends SJTypeNode
{
	public SJLabel getPrincipal();
	public SJLabel getPartner();
}