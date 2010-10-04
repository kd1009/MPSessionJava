package songexch;

import sessionj.runtime.net.*;

public class Mob {
	
	public static void main (String[] args) {
	
		try {
			SongExch sgex = new SongExch();

			sgex.mob.setLocal();
			sgex.ser.setRemote("fusion10", 1300, 1200);
			sgex.com.setRemote("fusion11", 1500, 1400);
			sgex.ehd.setRemote("fusion12", 1700, 1600);

			sgex.invite();
			

			sgex.ser.send("What a beautiful day!");
			System.out.println("Requesting song: What a beautiful day!");			

			Song sg = (Song) sgex.ser.receive();
			System.out.println("Song " + sg.getName() + " received.");

			sgex.com.send(sg); 
			System.out.println("Song sent off.");		

			if(sgex.com.receiveBoolean()) {
				System.out.println("Song transferred successfully");
			}

		} catch (Exception e) {e.printStackTrace();}

	}
}
