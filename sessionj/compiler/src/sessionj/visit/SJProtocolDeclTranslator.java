package sessionj.visit;

import java.util.*;

import polyglot.ast.*;
import polyglot.frontend.Job;
import polyglot.qq.*;
import polyglot.types.*;
import polyglot.util.*;
import polyglot.visit.*;

import sessionj.ast.*;
import sessionj.ast.typenodes.*;
import sessionj.ast.protocoldecls.*;
import sessionj.types.SJTypeSystem;
import sessionj.types.typeobjects.*;
import sessionj.util.*;

import static sessionj.SJConstants.*;
import static sessionj.util.SJCompilerUtils.*;

public class SJProtocolDeclTranslator extends ContextVisitor // Subsequent ContextVisitor passes are broken because type building not done for new nodes? // Recording a reference to the containing AST node inside typeable instance objects might be useful for translation, maybe no need to manually track context scopes.
{
	private SJTypeSystem sjts = (SJTypeSystem) typeSystem();
	private SJNodeFactory sjnf = (SJNodeFactory) nodeFactory();
	
	private SJTypeEncoder sjte = new SJTypeEncoder(sjts);

	private Type stringType; // Move to SJConstants?

	public SJProtocolDeclTranslator(Job job, TypeSystem ts, NodeFactory nf)
	{
		super(job, ts, nf);
		
		try
		{
			stringType = sjts.typeForName(JAVA_STRING_CLASS);
		}
		catch (SemanticException se)
		{
			throw new RuntimeException(se);
		}
	}

	protected Node leaveCall(Node old, Node n, NodeVisitor v) throws SemanticException
	{
		if (n instanceof SJProtocolDecl)
		{
			n = translateSJProtocolDecl((SJProtocolDecl) n);
		} 
		
		else if (n instanceof SJGlobProtocolDecl)
		{
			n = translateSJGlobProtocolDecl((SJGlobProtocolDecl) n);
		}  

		return n;
	}

	private SJProtocolDecl translateSJProtocolDecl(SJProtocolDecl pd) throws SemanticException
	{	
		Position pos = pd.position();

		QQ qq = new QQ(sjts.extensionInfo(), pos);
		
		String translation = "";
		List<Object> mapping = new LinkedList<Object>();
		
		translation = "new SJProtocol(%E)";
		mapping.add(sjnf.StringLit(pos, sjte.encode(pd.sessionType().type())));
		
		New n = (New) qq.parseExpr(translation, mapping);
		n = (New) buildAndCheckTypes(this, n);
		
		if (pd instanceof SJFieldProtocolDecl)
		{
			pd = ((SJFieldProtocolDecl) pd).init(n);
		}
		else //if (pd instanceof SJLocalProtocolDecl)
		{
			pd = ((SJLocalProtocolDecl) pd).init(n);
		}
		
		return pd;
	}

	private Node translateSJGlobProtocolDecl(SJGlobProtocolDecl pd) throws SemanticException
	{
		Position pos = pd.position();
		LinkedList<ClassMember> members = new LinkedList<ClassMember>();
			
		for (Id id: pd.getParticipantList())
		{
			ClassMember mb = new FieldDecl_c(pos, pd.flags(), new CanonicalTypeNode_c(pos, SJ_GLOB_PROTOCOL_TYPE), id, new NullLit_c(pos));
			members.add(mb);
		}
			
		ClassBody bd = new ClassBody_c(pos, members);
			
		System.out.println("Body: " + bd);
		System.out.println("MemberList: " + bd.members());
			
		return pd.body(bd);
		
		
//		Position pos = pd.position();
//
//		QQ qq = new QQ(sjts.extensionInfo(), pos);
//		List<Object> mapping = new LinkedList<Object>();
//		
//		String translation = "public java.lang.String %s = \"hello\";";
//		mapping.add(pd.getParticipantList().getFirst());
//		
//		ClassMember n = (ClassMember) qq.parseMember(translation, mapping);
//		n = (ClassMember) buildAndCheckTypes(this, n);
//		
//		pd = (SJGlobProtocolDecl) (((SJGlobProtocolDecl) pd).body(pd.body().addMember(n)));
//		
//		return buildAndCheckTypes(this, pd);
		
//		System.out.println(pd.body().members());
//
//		Position pos = pd.position();
//
//		QQ qq = new QQ(sjts.extensionInfo(), pos);
//		List<Object> mapping = new LinkedList<Object>();
//		
//		String translation = "public class %s extends SJGlobProtocol { ";
//		mapping.add(pd.globProtocolName());
//
//		
//		for(String part : pd.getParticipantList() ){
//		translation += "public boolean %s = false;"; 
//		mapping.add(part);
//		}
//		
//		translation += "}";
//		Node n = qq.parseDecl(translation, mapping);
//		
//		ClassBody newBody = ((ClassDecl) n).body().members(pd.body().members());
//		
//		n = ((ClassDecl)n).body(newBody);
//		
//		System.out.println(((ClassDecl)n).body().members());
//		
//		return buildAndCheckTypes(this, n);	
		
	}
}
