package sessionj.types.sesstypes;

import polyglot.types.*;

abstract public class SJMessageCommunicationType_c extends SJSessionType_c implements SJMessageCommunicationType
{
	private Type messageType; 

	//private long svuid = -1; // Factor out constant.

	protected SJMessageCommunicationType_c(TypeSystem ts)
	{
		super(ts);
	}
	
	public SJMessageCommunicationType_c(TypeSystem ts, Type messageType) throws SemanticException
	{
		super(ts);

        this.messageType = messageType;
    }
	
	public Type messageType()
	{
        Type t = messageType;
		
		return t instanceof SJSessionType ? ((SJSessionType) t).copy() : t;
	}

	public SJSessionType messageType(Type messageType) throws SemanticException
	{
		SJMessageCommunicationType mct = skeleton();
		
		if (messageType instanceof SJSessionType)
		{
			messageType = ((SJSessionType) messageType).copy(); // Only session type constructors cloned - pointer equality maintained for ordinary types.
		}

        ((SJMessageCommunicationType_c) mct).messageType = messageType;

        return mct;
	}

	public boolean nodeWellFormed()
	{
        Type type = messageType;
		
		if (type instanceof SJSessionType)
		{
			if (type instanceof SJBeginType)
			{
				return ((SJSessionType) type).isWellFormed();
			}
			
			return ((SJSessionType) type).treeWellFormed();
		}
		else
		{
			return true;
		}
	}

	public SJSessionType nodeClone()
	{
		SJMessageCommunicationType mct = skeleton();
		
		try
		{
            return mct.messageType(messageType); // Higher-order message types are copied by the setter method.
		}
		catch (SemanticException se) // Not possible - any problems would have been raised when this object was orig. created.
		{
			throw new RuntimeException("[SJMessageCommunicationType_c] Shouldn't get in here.", se);
		}
	}
	
	public String nodeToString()
	{
        String message = messageType.toString(); // toString enough for messageType? or need to manually get full name?

		return messageCommunicationOpen() + message + messageCommunicationClose();
	}

    abstract protected SJMessageCommunicationType skeleton(); 
	
	abstract protected String messageCommunicationOpen();
	abstract protected String messageCommunicationClose();
	
  public SJSessionType nodeCanonicalForm()  
  {

      Type mt = messageType();
  	
  	try
  	{
  		return mt instanceof SJSessionType ? messageType(((SJSessionType) mt).getCanonicalForm()) : this;
  	}
  	catch (SemanticException se)
  	{
  		throw new RuntimeException("[SJMessageCommunicationType_c] Shouldn't get in here: " + mt, se);
  	}
  }	
}
