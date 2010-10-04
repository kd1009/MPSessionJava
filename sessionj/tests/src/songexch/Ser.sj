package songexch;

import sessionj.runtime.net.*;

public class Ser {
	
	public static void main (String[] args) {
	
		try {
			SongExch sgex = new SongExch();

			sgex.ser.setLocal();
			sgex.mob.setRemote("fusion09", 1200, 1300);
			sgex.com.setRemote("fusion11", 1900, 1800);
			sgex.ehd.setRemote("fusion12", 2100, 2000);

			sgex.acceptInvite();

			Song sg = new Song((String) sgex.mob.receive());
			System.out.println("Song " + sg.getName() + " created.");

			sgex.mob.send(sg); 
			System.out.println("Song sent to mobile.");		

			if(sgex.com.receiveBoolean()) {
				System.out.println("Song received by Com successfully");
			}

		} catch (Exception e) {e.printStackTrace();}

	}
}
