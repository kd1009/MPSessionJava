//$ bin/sessionjc -cp tests/classes/ tests/src/oopsla/chat/common/events/UserJoinedEvent.sj -d tests/classes/

package oopsla.chat.common.events;

import java.util.*;

public class UserJoinedEvent extends ChatEvent
{	
	private String userName;
	
	public UserJoinedEvent(Integer userId, String userName)
	{
		super(userId);
		
		this.userName = userName;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String toString()
	{
		return "<User joined event: " + getUserId() + ", " + getUserName() + ">";
	}
}
