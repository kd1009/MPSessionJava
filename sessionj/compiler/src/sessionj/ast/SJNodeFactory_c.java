package sessionj.ast;

import polyglot.ast.*;
import polyglot.frontend.ExtensionInfo;
import polyglot.qq.QQ;
import polyglot.types.Flags;
import polyglot.util.Position;
import polyglot.parse.Name;
import static sessionj.SJConstants.*;
import sessionj.ast.chanops.SJRequest;
import sessionj.ast.chanops.SJRequest_c;
import sessionj.ast.createops.*;
import sessionj.ast.noalias.*;
import sessionj.ast.protocoldecls.*;
import sessionj.ast.servops.*;
import sessionj.ast.selectorops.*;
import sessionj.ast.sesscasts.*;
import sessionj.ast.sessformals.SJFormal_c;
import sessionj.ast.sessformals.SJFormal;
import sessionj.ast.sessops.basicops.*;
import sessionj.ast.sessops.compoundops.*;
import sessionj.ast.sesstry.*;
import sessionj.ast.sessvars.*;
import sessionj.ast.typenodes.*;
import sessionj.extension.SJExtFactory;
import sessionj.extension.SJExtFactory_c;
import static sessionj.util.SJCompilerUtils.setSJNoAliasFinalExt;
import sessionj.util.SJLabel;
import sessionj.util.SJCompilerUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * NodeFactory for sessionj extension.
 */
public class SJNodeFactory_c extends NodeFactory_c implements SJNodeFactory 
{
    // TODO:  Implement factory methods for new AST nodes.
    // TODO:  Override factory methods for overriden AST nodes.
    // TODO:  Override factory methods for AST nodes with new extension nodes.
	
	private ExtensionInfo extInfo; 
	
	private final SJExtFactory sjef = (SJExtFactory) super.extFactory();
	//private SJDelFactory sjdf = (SJDelFactory) super.delFactory();
	
	public SJNodeFactory_c()
	{
		super(new SJExtFactory_c());
		//super(new SJExtFactory_c(), new SJDelFactory_c());		
	}

	public SJExtFactory extFactory()
	{
		return sjef;
	}
	
	/*public SJDelFactory delFactory()
	{
		return sjdf;
	}*/
	
	/*
	 * public SJDelFactory delFactory() { return (SJDelFactory)
	 * super.delFactory(); }
	 */

	public void setExtensionInfo(ExtensionInfo extInfo)
	{
		this.extInfo = extInfo;
	}
	
	public SJAmbNoAliasTypeNode SJAmbNoAliasTypeNode(Position pos, AmbTypeNode atn)
	{
        // n = (SJAmbNoAliasTypeNode) n.ext(extFactory().SJNoAliasExt()); // No
		// point: is discarded by disambiguation pass, and is not actually needed
		// before then. So is now made and attached to the (new unambiguous) node by
		// the disambiguation pass.

		return new SJAmbNoAliasTypeNode_c(pos, atn);
	}

	public SJNoAliasArrayTypeNode SJNoAliasArrayTypeNode(Position pos, ArrayTypeNode atn)
	{
        return new SJNoAliasArrayTypeNode_c(pos, atn);
	}

	public SJNoAliasCanonicalTypeNode SJNoAliasCanonicalTypeNode(Position pos, CanonicalTypeNode ctn)
	{
        return new SJNoAliasCanonicalTypeNode_c(pos, ctn);
	}
	
	public SJFieldProtocolDecl SJFieldProtocolDecl(Position pos, Flags flags, Id name, SJTypeNode tn)
	{
		assert pos != null;
		SJFieldProtocolDecl n = new SJFieldProtocolDecl_c
            (pos, flags.Final(), CanonicalTypeNode(pos, SJ_PROTOCOL_TYPE), name, NullLit(pos), tn);
        // Null initialization overwritten by protocol declaration translation pass (dummy init.
        // needed to satisfy base type checking).		

		n = (SJFieldProtocolDecl) n.type(convertToNoAliasTypeNode(n.type(), true));
        // Makes the object type: noalias SJProtocol. Also adds the extension objects for
        // the session type (later disambiguated, etc. by SJProtocolDeclTypeBuilder).
		return n;
	}

    public SJLocalProtocolDecl SJLocalProtocolDecl(Position pos, Id name, SJTypeNode tn)
	{
		SJLocalProtocolDecl n = new SJLocalProtocolDecl_c
            (pos, Flags.FINAL, CanonicalTypeNode(pos, SJ_PROTOCOL_TYPE), name, NullLit(pos), tn);
		
		n = (SJLocalProtocolDecl) n.type(convertToNoAliasTypeNode(n.type(), true));

        return n;
	}
	
	public SJCBeginNode SJCBeginNode(Position pos)
	{
		return new SJCBeginNode_c(pos);
	}
	
	public SJSBeginNode SJSBeginNode(Position pos)
	{
		return new SJSBeginNode_c(pos);
	}
	
	public SJSendNode SJSendNode(Position pos, TypeNode messageType)
	{
		return new SJSendNode_c(pos, messageType);
	}

	public SJOutbranchNode SJOutbranchNode(Position pos, List<SJBranchCaseNode> branchCases)
	{
		return new SJOutbranchNode_c(pos, branchCases);
	}
	
	/*public SJOutbranchNode SJOutbranchNode(Position pos, List<SJBranchCaseNode> branchCases, boolean isDependentlyTyped)
	{
		return new SJOutbranchNode_c(pos, branchCases, isDependentlyTyped);
	}*/
	
	public SJInbranchNode SJInbranchNode(Position pos, List<SJBranchCaseNode> branchCases)
	{
		return new SJInbranchNode_c(pos, branchCases);
	}
	
	/*public SJInbranchNode SJInbranchNode(Position pos, List<SJBranchCaseNode> branchCases, boolean isDependentlyTyped)
	{
		return new SJInbranchNode_c(pos, branchCases, isDependentlyTyped);
	}*/
	
	public SJBranchCaseNode SJBranchCaseNode(Position pos, SJLabel lab, SJTypeNode body)
	{
		return new SJBranchCaseNode_c(pos, lab, body);
	}
	
	public SJReceiveNode SJReceiveNode(Position pos, TypeNode messageType)
	{
		return new SJReceiveNode_c(pos, messageType);
	}

	public SJOutwhileNode SJOutwhileNode(Position pos, SJTypeNode body)
	{
		return new SJOutwhileNode_c(pos, body);
	}
	
	public SJInwhileNode SJInwhileNode(Position pos, SJTypeNode body)
	{
		return new SJInwhileNode_c(pos, body);
	}

	public SJRecursionNode SJRecursionNode(Position pos, SJLabel lab, SJTypeNode body)
	{
		return new SJRecursionNode_c(pos, lab, body);
	}
	
	public SJRecurseNode SJRecurseNode(Position pos, SJLabel lab)
	{
		return new SJRecurseNode_c(pos, lab);
	}
	
	public SJProtocolRefNode SJProtocolRefNode(Position pos, Receiver target)
	{
		return new SJProtocolRefNode_c(pos, target);
	}
	
	public SJProtocolDualNode SJProtocolDualNode(Position pos, Receiver target)
	{
		return new SJProtocolDualNode_c(pos, target);
	}
	
	public SJChannelCreate SJChannelCreate(Position pos, List arguments)
	{
		CanonicalTypeNode target = CanonicalTypeNode(pos, SJ_CHANNEL_TYPE);
		Id name = Id(pos, SJ_CHANNEL_CREATE);

        //return (SJChannelCreate) n.ext(extFactory().SJCreateOperationExt());
		
		return new SJChannelCreate_c(pos, target, name, arguments);
	}

	public SJSocketCreate SJSocketCreate(Position pos, List arguments)
	{
		CanonicalTypeNode target = CanonicalTypeNode(pos, SJ_SOCKET_INTERFACE_TYPE);
		Id name = Id(pos, SJ_SOCKET_CREATE);

        //return (SJSocketCreate) n.ext(extFactory().SJCreateOperationExt());
		
		return new SJSocketCreate_c(pos, target, name, arguments);
	}	
	
	public SJServerCreate SJServerCreate(Position pos, List arguments)
	{
		CanonicalTypeNode target = CanonicalTypeNode(pos, SJ_SERVER_TYPE);
		Id name = Id(pos, SJ_SERVER_CREATE);

        //return (SJServerCreate) n.ext(extFactory().SJCreateOperationExt());
		
		return new SJServerCreate_c(pos, target, name, arguments);
	}
	
	public SJSelectorCreate SJSelectorCreate(Position pos, List arguments)
	{
		CanonicalTypeNode target = CanonicalTypeNode(pos, SJ_RUNTIME_TYPE);
		Id name = Id(pos, SJ_SELECTOR_SELECTORFOR);
		
		return new SJSelectorCreate_c(pos, target, name, arguments);
	}	
	
	public SJLocalChannel SJLocalChannel(Position pos, Id name)
	{
        return new SJLocalChannel_c(pos, name);
	}
	
	public SJLocalSocket SJLocalSocket(Position pos, Id name)
	{
        return new SJLocalSocket_c(pos, name);
	}

	public SJLocalServer SJLocalServer(Position pos, Id name)
	{
        return new SJLocalServer_c(pos, name);
	}

	public SJLocalSelector SJLocalSelector(Position pos, Id name)
	{
		return new SJLocalSelector_c(pos, name);
	}	
	
	public SJAmbiguousTry SJAmbiguousTry(Position pos, Block tryBlock, List catchBlocks, Block finallyBlock, List targets)
	{
        return new SJAmbiguousTry_c(pos, tryBlock, catchBlocks, finallyBlock, targets);
	}
	
	public SJSessionTry SJSessionTry(Position pos, Block tryBlock, List catchBlocks, Block finallyBlock, List targets)
	{
        return new SJSessionTry_c(pos, tryBlock, catchBlocks, finallyBlock, targets);
	}
	
	public SJServerTry SJServerTry(Position pos, Block tryBlock, List catchBlocks, Block finallyBlock, List targets)
	{
        return new SJServerTry_c(pos, tryBlock, catchBlocks, finallyBlock, targets);
	}

	public SJSelectorTry SJSelectorTry(Position pos, Block tryBlock, List catchBlocks, Block finallyBlock, List targets)
	{
		return new SJSelectorTry_c(pos, tryBlock, catchBlocks, finallyBlock, targets);
	}
	
	public SJRequest SJRequest(Position pos, Receiver target, List arguments)
	{	
		Id name = Id(pos, SJ_CHANNEL_REQUEST);

        return new SJRequest_c(pos, target, name, arguments);
	}
	
	public SJSend SJSend(Position pos, List arguments, List targets)
	{	 
        // FIXME: the name shouldn't associated with the socket but rather the runtime.
		return new SJSend_c(pos, this, arguments, targets);
	}
	
	public SJPass SJPass(Position pos, List arguments, List targets)
	{	
		return new SJPass_c(pos, this, SJ_SOCKET_PASS, arguments, targets);
	}
	
	public SJCopy SJCopy(Position pos, List arguments, List targets)
	{	
		return new SJCopy_c(pos, this, arguments, targets);
	}
	
	// This is called by SJSessionOperationParser, not the parser, so the target has already been disambiguated and the ArrayInit could be created directly here.
	public SJReceive SJReceive(Position pos, List<Expr> arguments, List targets)
	{	
		return new SJReceive_c(pos, this, SJ_SOCKET_RECEIVE, arguments, targets);
	}	

	public SJReceive SJReceiveInt(Position pos, List<Expr> arguments, List targets)
    // FIXME: a bit hacky when it comes to type building - need to explicitly distinguish primitive
    // and object type usage of the single SJReceive node class. 
	{	
		return new SJReceive_c(pos, this, SJ_SOCKET_RECEIVEINT, arguments, targets);
	}	
	
	public SJReceive SJReceiveBoolean(Position pos, List<Expr> arguments, List targets)
	{	
		return new SJReceive_c(pos, this, SJ_SOCKET_RECEIVEBOOLEAN, arguments, targets);
	}
	
	public SJReceive SJReceiveDouble(Position pos, List<Expr> arguments, List targets)
	{	
		return new SJReceive_c(pos, this, SJ_SOCKET_RECEIVEDOUBLE, arguments, targets);
	}
	
	public SJRecurse SJRecurse(Position pos, SJLabel lab, List targets)
	{
		return new SJRecurse_c(pos, this, SJCompilerUtils.asLinkedList(StringLit(pos, lab.labelValue())), targets, lab);
	}

	public SJSpawn SJSpawn(Position pos, New w, List targets)
	{	
		Id name = Id(pos, SJ_THREAD_SPAWN);						
				
		List arguments = new LinkedList();
		
		/*for (Iterator i = targets.iterator(); i.hasNext(); )
		{
			arguments.add(i.next());
		}*/

        //n = (SJSpawn) n.del(sjdf.SJSpawnDel()); // Had some type checking problems (because new arguments are inserted) due to a previously missing but needed barrier between SJThreadParsing (generate the target spawn method in the SJThread and build types for the class) and SJSessionOperationParsing (translate the spawn call and type check against the target method).
		
		return new SJSpawn_c(pos, w, name, arguments, targets);
	}

	// Can generalise this operation to support arbitrary objects as labels.
  public SJOutlabel SJOutlabel(Position pos, SJLabel lab, List targets)
	{	
		return new SJOutlabel_c(pos, this, SJCompilerUtils.asLinkedList(StringLit(pos, lab.labelValue())), targets);
	}
	
	public SJInlabel SJInlabel(Position pos, List arguments, List targets)
	{	
		return new SJInlabel_c(pos, this, arguments, targets);
	}
	
	public SJOutsync SJOutsync(Position pos, Expr condition, List targets)
	{	
		return new SJOutsync_c(this, pos, condition, targets);
	}

  /*public SJRecursionEnter SJRecursionEnter(Position pos, List targets)
	{	
		return new SJRecursionEnter_c(pos, this, SJ_SOCKET_RECURSIONENTER, targets);
	}*/
	
	public SJRecursionEnter SJRecursionEnter(Position pos, List args, List targets)
	{	
		return new SJRecursionEnter_c(pos, this, SJ_SOCKET_RECURSIONENTER, args, targets);
	}
	
	public SJRecursionExit SJRecursionExit(Position pos, List targets)
	{	
		return new SJRecursionExit_c(pos, this, SJ_SOCKET_RECURSIONEXIT, targets);
	}
		
	public SJOutbranch SJOutbranch(final Position pos, final List<Stmt> stmts, SJLabel lab, List<Receiver> targets)
	{
		final SJOutlabel os = SJOutlabel(pos, lab, targets);
		
		List<Stmt> stmtList = new LinkedList<Stmt>() {{
            add(Eval(pos, os));
            addAll(stmts);                    
        }};
		
		return new SJOutbranch_c(pos, stmtList, lab, targets);
	}
	
	/*public SJOutbranch SJOutbranch(final Position pos, final List<Stmt> stmts, SJLabel lab, List<Receiver> targets, boolean isDependentlyTyped)
	{
		//final SJOutlabel os = SJOutlabel(pos, lab, targets); // SJOutlabel is implicitly based on Strings as labels. For the dependently-typed branches, we need to do extra work in type-building, checking and translation.
		
		List<Stmt> stmtList = new LinkedList<Stmt>() {{
            //add(Eval(pos, os));
            addAll(stmts);                    
        }};
		
		return new SJOutbranch_c(pos, stmtList, lab, targets, isDependentlyTyped);
	}*/
	
	public SJInbranch SJInbranch(Position pos, List arguments, List<SJInbranchCase> branchCases, List targets)
	{
		SJInlabel il = SJInlabel(pos, arguments, targets);

        return new SJInbranch_c(pos, branchCases, il);
	}
	
	/*public SJInbranch SJInbranch(Position pos, List arguments, List<SJInbranchCase> branchCases, List targets, boolean isDependentlyTyped)
	{
		SJInlabel il = SJInlabel(pos, arguments, targets);

        return new SJInbranch_c(pos, branchCases, il, isDependentlyTyped);
	}*/
	
	public SJInbranchCase SJInbranchCase(Position pos, List stmts, SJLabel lab)
	{
        return new SJInbranchCase_c(pos, stmts, lab);
	}
	
	public SJOutwhile SJOutwhile(Position pos, Expr condition, Stmt body, List targets)
	{
		return new SJOutwhile_c(pos, condition, body, targets, false);
	}

    public SJOutwhile SJNewOutwhile(Position pos, Expr condition, Stmt body, List targets) {
        return new SJOutwhile_c(pos, condition, body, targets, true);
    }

    public SJOutInwhile SJOutInwhile(Position pos, Stmt body, List<Receiver> sources, List<Receiver> targets, Expr condition)
	{
        return new SJOutInwhile_c(pos, condition, body, targets, sources);
	}
	
	public SJInwhile SJInwhile(Position pos, Stmt body, List targets)
	{
        return new SJInwhile_c(pos, body, targets);
	}

	public SJRecursion SJRecursion(final Position pos, Block body, final SJLabel lab, final List targets) // Inconvenient to ...
	{
		QQ qq = new QQ(extInfo, pos);

        List<Object> mapping = new LinkedList<Object>();

        String translation = "for ( ; new Boolean(false).booleanValue(); ) { }";
        // Dummy condition later replaced by SJCompoundOperationTranslator.
        // Used because we cannot give the intended loop-variable the correct name yet (targets are ambiguous).
		
		For f = (For) qq.parseStmt(translation, mapping.toArray());
		
		List args = new LinkedList()
		{{
			add(StringLit(pos, lab.labelValue()));
		}};
		
		//SJRecursionEnter re = SJRecursionEnter(pos, targets);
		SJRecursionEnter re = SJRecursionEnter(pos, args, targets); // Extended recursion-enter to take the recursion label as an argument. We could have done this in the translation phase, but unlike recursion-exit, recursion-enter seems to be handled here.
		
		translation = "%E;";
		mapping.add(re);
		
		Eval e = (Eval) qq.parseStmt(translation, mapping.toArray());
		
		body = body.prepend(e);

        return new SJRecursion_c(pos, f.inits(), f.cond(), body, lab, targets);
	}

    public SJTypecase SJTypecase(Position pos, Name socket, List<SJWhen> cases) {
        return new SJTypecase_c(pos, socket.toReceiver(), cases, null);
    }

    public SJWhen SJWhen(Position pos, SJTypeNode type, Stmt body) {
        List<Stmt> statements = new LinkedList<Stmt>();
        if (body instanceof Block) {
            statements.addAll(((Block) body).statements());
        } else {
            statements.add(body);
        }
        return new SJWhen_c(pos, type, statements);
    }

    public SJAccept SJAccept(Position pos, Receiver target, List arguments)
	{	
		Id name = Id(pos, SJ_SERVER_ACCEPT);

        return new SJAccept_c(pos, target, name, arguments);
	}	
    
  public SJRegisterAccept SJRegisterAccept(Position pos, Receiver target, List arguments)
  {
  	Id name = Id(pos, SJ_SELECTOR_REGISTERACCEPT);
  	
  	return new SJRegisterAccept_c(pos, target, name, arguments);
  }
  
  public SJRegisterOutput SJRegisterOutput(Position pos, Receiver target, List arguments)
  {
  	Id name = Id(pos, SJ_SELECTOR_REGISTEROUTPUT);
  	
  	return new SJRegisterOutput_c(pos, target, name, arguments);
  }
  
  public SJRegisterInput SJRegisterInput(Position pos, Receiver target, List arguments)
  {
  	Id name = Id(pos, SJ_SELECTOR_REGISTERINPUT);
  	
  	return new SJRegisterInput_c(pos, target, name, arguments);
  }  

  public SJSelectSession SJSelectSession(Position pos, Receiver target, List arguments)
  {
  	Id name = Id(pos, SJ_SELECTOR_SELECTSESSION);
  	
  	return new SJSelectSession_c(pos, target, name, arguments);
  }  
  
	public SJChannelCast SJChannelCast(Position pos, Expr expr, SJTypeNode tn)
	{

        return new SJChannelCast_c(pos, CanonicalTypeNode(pos, SJ_CHANNEL_TYPE), expr, tn);
	}
	
	public SJSessionCast SJSessionCast(Position pos, Expr expr, SJTypeNode tn)
	{

        return new SJSessionCast_c(pos, CanonicalTypeNode(pos, SJ_SOCKET_INTERFACE_TYPE), expr, tn);
	}
	
	public SJAmbiguousCast SJAmbiguousCast(Position pos, Expr expr, SJTypeNode tn)
	{

        return new SJAmbiguousCast_c(pos, CanonicalTypeNode(pos, SJ_CHANNEL_SOCKET_HACK_TYPE), expr, tn);
	}
	
	public SJFormal SJChannelFormal(Position pos, Flags flags, Id name, SJTypeNode tn) // Based on SJSessionFormal.
	{
		SJFormal n = new SJFormal_c(this, SJ_CHANNEL_TYPE, pos, flags, name, tn);

		n = (SJFormal) n.type(convertToNoAliasTypeNode(n.type(), flags.isFinal()));
		return n;
	}
	
	public SJFormal SJSessionFormal(Position pos, Flags flags, Id name, SJTypeNode tn)
    // Based on SJProtocolDecl.
    // // The choice is between modifying the base types to signal noalias, or make a separate
    // (sub)class for noalias session formals. Going with the former, as for SJProtocolDecls.
	{
		SJFormal n = new SJFormal_c(this, SJ_SOCKET_INTERFACE_TYPE, pos, flags, name, tn);
		
		n = (SJFormal) n.type(convertToNoAliasTypeNode(n.type(), flags.isFinal()));
		
		return n;
	}

    public SJFormal SJServerFormal(Position pos, Flags flags, Id name, SJTypeNode tn)
       // Based on SJProtocolDecl.
       // // The choice is between modifying the base types to signal noalias, or make a separate
       // (sub)class for noalias session formals. Going with the former, as for SJProtocolDecls.
       {
           SJFormal n = new SJFormal_c(this, SJ_SERVER_INTERFACE_TYPE, pos, flags, name, tn);

           n = (SJFormal) n.type(convertToNoAliasTypeNode(n.type(), flags.isFinal()));

           return n;
	   }

	private TypeNode convertToNoAliasTypeNode(TypeNode tn, boolean isFinal)
	{		
		if (tn instanceof AmbTypeNode)
		{
			tn = SJAmbNoAliasTypeNode(tn.position(), (AmbTypeNode) tn);
		}
		else if (tn instanceof ArrayTypeNode) 
		{
			tn = SJNoAliasArrayTypeNode(tn.position(), (ArrayTypeNode) tn);
		}
		/*else
		{
			// CanonicalTypeNode won't be replaced by disambigation pass (so extension object won't be lost).
		}*/
		
		return (TypeNode) setSJNoAliasFinalExt(sjef, tn, true, isFinal); // Protocols are na-final.
	}
	
	/*private Flags makeFinal(Flags flags)
	{
		return (!flags.isFinal()) ? flags.Final() : flags;
	}*/
    public NewArray makeSocketsArray(Position pos, int size)
    {
        CanonicalTypeNode base = CanonicalTypeNode(pos, SJ_SOCKET_INTERFACE_TYPE);

        List<Expr> dims = new LinkedList<Expr>();
        dims.add(IntLit(pos, IntLit.INT, size));

        return NewArray(pos, base, dims, 0, null);
    }



/*********************************************************************************************************************/

	public SJGlobElementPrefixNode SJGlobElementPrefixNode(Position pos, SJLabel a, SJLabel b)
	{
		return new SJGlobElementPrefixNode_c(pos, a, b);	
	}
	
	public SJGlobSendNode SJGlobSendNode(Position pos, TypeNode messageType, TypeNode prefixType)
	{
		return new SJGlobSendNode_c(pos, messageType, prefixType);
	}
}
