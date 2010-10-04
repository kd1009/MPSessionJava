package songexch;

import java.io.*;

public class Song implements Serializable {

	String name;

	Song(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}
}
