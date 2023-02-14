import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

public class PlaylistFunctions implements IPlaylistFunctions {
	dbConnection db = new dbConnection();
	PreparedStatement statement;
	Playlist playlist = new Playlist();
	ICrudFunctions crudFunctions = new CrudFunctions();
//	IPlaylistFunctions playlistFunctions = new PlaylistFunctions();

	public void sendPlaylist(HashSet<Song>songSet, String name) {
	    try {
	        Connection connection = db.connect();
	        PreparedStatement statement1 = connection.prepareStatement(
	                "INSERT INTO playlists (playlistName) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
	 
	        PreparedStatement statement2 = connection
	                .prepareStatement("INSERT INTO songPlaylist (playlistID,songID) VALUES (?, ?)");
	        statement1.setString(1, name);
	        statement1.executeUpdate();ResultSet result = statement1.getGeneratedKeys();
	        
	        int playlistID = 0;
	        if (result.next()) {
	            playlistID = result.getInt(1);
	        }

	        for (Song song :songSet) {
	            PreparedStatement statement3 = connection.prepareStatement("SELECT songID FROM songs WHERE artist=? and title=?");
	            statement3.setString(1, song.getArtist());
	            statement3.setString(2,song.getTitle());
	            ResultSet result2 = statement3.executeQuery();
	            if(result2.next()) {
	                int songID = result2.getInt("songID");
	                statement2.setInt(1, playlistID);
	                statement2.setInt(2,songID);
	                statement2.executeUpdate();
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Playlist> getPlaylist() {
	    ArrayList<Playlist> playlists = new ArrayList<>();
	    try {
	        Connection connection = db.connect();
	        PreparedStatement statement = connection.prepareStatement(
	            "SELECT playlists.playlistName, songs.songID, songs.artist, songs.title, songs.duration, songs.type, songs.link FROM songplaylist " +
	            "JOIN playlists ON songplaylist.playlistID = playlists.playlistID " +
	            "JOIN songs ON songplaylist.songID = songs.songID"
	        );
	        ResultSet result = statement.executeQuery();
	        while (result.next()) {
	            String playlistName = result.getString("playlistName");
	            int songID = result.getInt("songID");
	            String artist = result.getString("artist");
	            String title = result.getString("title");
	            double duration = result.getDouble("duration");
	            String songType = result.getString("type");
	            String link = result.getString("link");
	            
	            Song song = new Song(songID, artist, title, duration, songType, link);
	            Playlist playlist = new Playlist(playlistName);
	            if (!playlists.contains(playlist)) {
	                playlists.add(playlist);
	            }
	            crudFunctions.addSong(song);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return playlists;
	}

	@Override
	public HashSet<Playlist> getPlaylist(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}