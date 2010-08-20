package sessionj.runtime.net;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SJGlobSession 
{
	public void invite() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for(Field f: getClass().getDeclaredFields()) {
			if (f.getType().equals(SJGlobParticipant.class)) {
				SJGlobParticipant p = (SJGlobParticipant) f.get(this);
				String hostname = p.getHostname();
				int port = p.getPort();
			}
		}
		
	}
	
}