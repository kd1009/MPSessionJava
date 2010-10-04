package songexch;

import sessionj.runtime.net.*;

global_protocol SongExch {

	|mob,ser| !<String>.
	|mob,ser| ?(Song).
	|mob,com| !<Song>.
	|com,mob| !<bool>.
	|com,ser| !<bool>.
	|com,ehd| !<Song>
}
