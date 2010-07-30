package sessionj.ast;

import sessionj.ast.typenodes.SJTypeNode;
import sessionj.ast.typenodes.SJGlobElementPrefixNode;

public interface SJGlobTypeNode extends SJTypeNode
{
	public SJGlobElementPrefixNode getPrefix();
}