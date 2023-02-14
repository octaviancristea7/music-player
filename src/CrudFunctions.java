import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudFunctions implements ICrudFunctions {
	dbConnection db = new dbConnection();

	public void addSong(Song s) throws SQLException {
		Connection connection = db.connect();
		PreparedStatement statement;
		String query = "INSERT INTO songs (artist, title, duration, type, link) values(?,?,?,?,?)";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, s.getArtist());
			statement.setString(2, s.getTitle());
			statement.setDouble(3, s.getDuration());
			statement.setString(4, s.getSongType());
			statement.setString(5, s.getLink());
			statement.executeUpdate();
			System.out.println("Insert-ul e corect");
		} catch (SQLException e) {
			System.out.println("Insert-ul nu a functionat!");
			e.printStackTrace();
		}

	}
	
	public void deleteSong(int songID) throws SQLException {
		Connection connection = db.connect();
		PreparedStatement statement;
		String query = "DELETE FROM songs WHERE songID = ?";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, songID);
			statement.executeUpdate();
			statement.close();
			connection.close();
			System.out.println("Delete-ul este corect");
			
		} catch (SQLException e) {
			System.out.println("Delete-ul nu a functionat!");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Song> getAllSongs() {
		Connection connection = db.connect();
	    ArrayList<Song> allSongs = new ArrayList<Song>();
	    PreparedStatement statement;
	    ResultSet resultSet;
	    try {
	    	String query = "SELECT * FROM songs";
	        statement = connection.prepareStatement(query);
	        resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int songID = resultSet.getInt(1);
	            String artist = resultSet.getString(2);
	            String title = resultSet.getString(3);
	            Double duration = resultSet.getDouble(4);
	            String genre = resultSet.getString(5);
	            String link = resultSet.getString(6);
	            Song song = new Song(songID, artist, title, duration, genre, link);
	            allSongs.add(song);
	        }
	    } catch (SQLException e) {
	        System.out.println("Aducerea melodiilor nu a functionat!");
	    	e.printStackTrace();
	    }
	    return allSongs;
	}

	@Override
	public void updateSong(Song s) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchSongByArtistAndTitle(String artist, String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Song> searchSongsByArtist(String artist) {
		// TODO Auto-generated method stub
		return null;
	}
}
