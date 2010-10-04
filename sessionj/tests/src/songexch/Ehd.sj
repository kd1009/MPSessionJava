package songexch;

import sessionj.runtime.net.*;

public class Ehd {
	
	public static void main (String[] args) {
	
		try {
			SongExch sgex = new SongExch();

			sgex.ehd.setLocal();
			sgex.mob.setRemote("fusion09", 1600, 1700);
			sgex.ser.setRemote("fusion10", 2000, 2100);
			sgex.com.setRemote("fusion11", 2200, 2300);

			sgex.acceptInvite();

			Song sg = (Song) sgex.com.receive();
			System.out.println("Song " + sg.getName() + " received from Com and stored.");

		} catch (Exception e) {e.printStackTrace();}

	}
}
