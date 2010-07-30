package sessionj.ast;

import sessionj.ast.typenodes.SJTypeNode;
import sessionj.ast.typenodes.SJGlobElementPrefixNode;

public interface SJGlobalTypeNode extends SJTypeNode
{
	public SJGlobElementPrefixNode getPrefix();
}