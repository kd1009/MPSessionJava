package sessionj.types;

import polyglot.frontend.Source;
import polyglot.types.*;
import sessionj.types.noalias.*;
import sessionj.types.typeobjects.*;
import sessionj.types.sesstypes.*;
import sessionj.util.SJLabel;

import java.util.List;

public interface SJTypeSystem extends TypeSystem 
{
	SJCBeginType SJCBeginType();
	SJSBeginType SJSBeginType();
	SJSendType SJSendType(Type messageType) throws SemanticException;
	SJReceiveType SJReceiveType(Type messageType) throws SemanticException;
	SJOutbranchType SJOutbranchType(); // FIXME: finish refactoring these branch types.
	SJOutbranchType SJOutbranchType(boolean isDependentlyTyped);
	SJInbranchType SJInbranchType();
	SJInbranchType SJInbranchType(boolean isDependentlyTyped);
	SJOutwhileType SJOutwhileType();
    SJSessionType SJOutwhileType(SJSessionType body);
	SJInwhileType SJInwhileType();
    SJInwhileType SJInwhileType(SJSessionType body);
	SJRecursionType SJRecursionType(SJLabel lab);
	SJRecurseType SJRecurseType(SJLabel lab);
	SJUnknownType SJUnknownType();
	SJDelegatedType SJDelegatedType(SJSessionType st);
    //SJSetType SJSetType(List<SJSessionType_c> members);
	SJSetType SJSetType(List<SJSessionType> members);
	
	SJParsedClassType SJParsedClassType(LazyClassInitializer init, Source fromSource);
	SJFieldInstance SJFieldInstance(FieldInstance fi, boolean isNoAlias, boolean isFinal);
	SJConstructorInstance SJConstructorInstance(ConstructorInstance ci);
	SJMethodInstance SJMethodInstance(MethodInstance mi);
	SJLocalInstance SJLocalInstance(LocalInstance li, boolean isNoAlias, boolean isFinal);
		
	SJFieldProtocolInstance SJFieldProtocolInstance(SJFieldInstance fi, SJSessionType st, String sjname);
	SJLocalProtocolInstance SJLocalProtocolInstance(SJLocalInstance li, SJSessionType st, String sjname);
	SJLocalChannelInstance SJLocalChannelInstance(SJLocalInstance ci, SJSessionType st, String sjname);
	SJLocalSocketInstance SJLocalSocketInstance(SJLocalInstance si, SJSessionType st, String sjname);
	SJLocalServerInstance SJLocalServerInstance(SJLocalInstance ci, SJSessionType st, String sjname);
	SJLocalSelectorInstance SJLocalSelectorInstance(SJLocalInstance ci, SJSessionType st, String sjname);
	
	SJNoAliasReferenceType SJNoAliasReferenceType(ReferenceType rt);
	SJNoAliasReferenceType SJNoAliasFinalReferenceType(ReferenceType rt, boolean isFinal);
	
	boolean wellFormedRecursions(SJSessionType st);
}
