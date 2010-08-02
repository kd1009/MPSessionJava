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
		List<ClassMember> members = new LinkedList<ClassMember>();
		QQ qq = new QQ(sjts.extensionInfo(), pos);
		
		String translation = "new sessionj.runtime.net.SJGlobParticipant(%E)";
		
		for (Id id: pd.getParticipantList())
		{
			List<Object> mapping = new LinkedList<Object>();
			mapping.add(sjnf.StringLit(pos, id.id()));
			
			ClassMember mb = new FieldDecl_c(
					pos, 
					sjts.Public(), 
					new CanonicalTypeNode_c(pos, SJ_GLOB_PARTICIPANT_TYPE), 
					id, 
					qq.parseExpr(translation, mapping));
			members.add(mb);
		}
		
			
		ClassBody bd = new ClassBody_c(pos, members);
			
		System.out.println("MemberList: " + bd.members());
			
		return pd.body(bd);	
	}
}
