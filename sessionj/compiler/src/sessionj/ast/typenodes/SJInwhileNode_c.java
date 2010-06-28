package sessionj.ast.typenodes;

import polyglot.util.Position;

import static sessionj.SJConstants.*;
import sessionj.types.sesstypes.SJSessionType;
import sessionj.types.SJTypeSystem;

public class SJInwhileNode_c extends SJLoopNode_c implements SJInwhileNode
{
	public SJInwhileNode_c(Position pos, SJTypeNode body)
	{
		super(pos, body);
	}

    @Override
    protected SJSessionType createType(SJSessionType bodyType, SJTypeSystem ts) {
        return ts.SJInwhileType(bodyType);
    }

    public String nodeToString()
	{
		String m = SJ_STRING_INWHILE_OPEN;
		
		if (body() != null)
		{
			m += body().toString();
		}
		
		return m + SJ_STRING_INWHILE_CLOSE;
	}
}
