import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ICrudFunctions {
	public void addSong(Song s) throws SQLException;
	public void updateSong(Song s) throws SQLException;
	public void deleteSong(int songID) throws SQLException;
	public void searchSongByArtistAndTitle(String artist, String title);
	public ArrayList<Song> getAllSongs();
	public ArrayList<Song> searchSongsByArtist(String artist);
}
