package sessionj.ast.typenodes;

public interface SJGlobSendNode extends SJSendNode, SJGlobTypeNode
{
	public SJGlobElementPrefixNode getPrefix();
}