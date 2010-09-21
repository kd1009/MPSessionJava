package sessionj.ast.sessvars;

import polyglot.ast.Id;
import polyglot.ast.Field_c;
import polyglot.ast.NullLit_c;
import polyglot.ast.Receiver;
import polyglot.util.Position;
import sessionj.visit.noalias.SJNoAliasExprBuilder;

public class SJFieldSocket_c extends Field_c implements SJFieldSocket
{
	public SJFieldSocket_c(Position pos, Receiver target, Id id)
	{
		super(pos, target, id);
	}

	public String sjname()
	{
		return name();
	}

    public boolean isFinal() {
        return flags().isFinal();
    }

    /**
     * equals is needed here because of
     * {@link SJNoAliasExprBuilder#removeNonFinalSocketArguments(polyglot.ast.Call, java.util.List)} 
     */
    @Override
    public boolean equals(Object o) {
//        if (o == this) return true;
//        if (!(o instanceof SJFieldSocket_c)) return false;
//        SJFieldSocket_c that = (SJFieldSocket_c) o;
//
//        return (del == that.del || del != null && del.equals(that.del)) &&
//                (exceptions == that.exceptions || exceptions != null && exceptions.equals(that.exceptions)) &&
//                // Not using the ext field for comparison, as it is either null or not in the above mentioned method
//                (li == that.li || li != null && li.equals(that.li)) &&
//                (name == that.name || name != null && name.equals(that.name)) &&
//                (position == that.position || position != null && position.equals(that.position)) &&
//                (type == that.type || type != null && type.equals(that.type)) &&
//                reachable == that.reachable &&
//                error == that.error;
    	return true;
    }
    
}
