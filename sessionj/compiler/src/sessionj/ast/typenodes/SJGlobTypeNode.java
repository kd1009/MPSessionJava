package sessionj.ast.typenodes;

import polyglot.visit.ContextVisitor;

import sessionj.types.SJTypeSystem;

public interface SJGlobTypeNode extends SJTypeNode
{
	public SJGlobElementPrefixNode getPrefix();
	public SJTypeNode getOperationNode();
	
	public SJGlobTypeNode child();
	public SJGlobTypeNode child(SJTypeNode child);
	
	public String nodeToString();
	public SJTypeNode disambiguateSJTypeNode(ContextVisitor context, SJTypeSystem sjts);
}