package sessionj.ast.typenodes;

import java.io.Serializable;

import polyglot.util.*;
import polyglot.visit.ContextVisitor;

import sessionj.types.SJTypeSystem;

public class SJGlobTypeNode_c extends SJTypeNode_c implements SJGlobTypeNode, SJTypeNode
{
	private SJGlobElementPrefixNode prefix;
	private SJTypeNode operationNode;
	
	public SJGlobTypeNode_c (Position pos, SJGlobElementPrefixNode prefix, SJTypeNode operationNode) 
	{
		super(pos);
		this.prefix = prefix;
		this.operationNode = operationNode;
	}
	
	public SJGlobTypeNode child()
	{
		return (SJGlobTypeNode) child;
	}

	public SJGlobTypeNode child(SJTypeNode child)
	{
		this.child = child;

		return (SJGlobTypeNode) this;
	}
	
	public SJGlobElementPrefixNode getPrefix() 
	{
		return prefix;
	}
	
	public SJTypeNode getOperationNode()
	{
		return operationNode;
	}
	
	public SJTypeNode disambiguateSJTypeNode(ContextVisitor context, SJTypeSystem sjts)
	{
		return null;
	}
	
	public String nodeToString()
	{
		return prefix.nodeToString() + operationNode.nodeToString();
	}
	
	

}