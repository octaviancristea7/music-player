import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public interface IPlaylistFunctions {
	public void sendPlaylist(HashSet<Song> songSet, String name) throws SQLException;
	public HashSet<Playlist> getPlaylist(String name) throws SQLException ;
}
