package songexch;

import sessionj.runtime.net.*;

public class Com {
	
	public static void main (String[] args) {
	
		try {
			SongExch sgex = new SongExch();

			sgex.com.setLocal();
			sgex.mob.setRemote("fusion09", 1400, 1500);
			sgex.ser.setRemote("fusion10", 1800, 1900);
			sgex.ehd.setRemote("fusion12", 2300, 2200);

			sgex.acceptInvite();

			Song sg = (Song) sgex.mob.receive();
			System.out.println("Song " + sg.getName() + " received from Mob.");

			if(sg != null) {
				sgex.mob.send(true);
				sgex.ser.send(true); 
				System.out.println("Confirmations sent out.");		
			}

			sgex.ehd.send(sg);
			System.out.println("Song passed on to EHD.");

		} catch (Exception e) {e.printStackTrace();}

	}
}
