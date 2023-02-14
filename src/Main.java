import java.util.Scanner;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main implements ICrudFunctions {
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		
		Scanner input = new Scanner(System.in);
		Scanner option = new Scanner(System.in);
		Scanner playlistScanner = new Scanner(System.in);
		ArrayList<Song> allSongs = new ArrayList<Song>();
		ICrudFunctions crudFunctions = new CrudFunctions();
		IPlaylistFunctions playlistFunctions = new PlaylistFunctions();
		Playlist playlist = new Playlist();
		allSongs = crudFunctions.getAllSongs();
		
		while (true) {
			System.out.println("1. Add song");
			System.out.println("2. Delete song");
			System.out.println("3. Display all songs");
			System.out.println("4. Create random playlist");
//			System.out.println("5. Play songs in playlist");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			int choice = option.nextInt();
			option.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter artist name: ");
				String name = input.nextLine();
				
				System.out.print("Enter title: ");
				String title = input.nextLine();
				
				System.out.print("Enter duration: ");
				String duration = input.nextLine();
				
				System.out.print("Enter type: ");
				String genre = input.nextLine();
				
				System.out.print("Enter link: ");
				String link = input.nextLine();
				
				Song song = new Song(name, title, Double.valueOf(duration), genre, link);
				System.out.println(song);
				
				if(song.validateSong())
					crudFunctions.addSong(song);
				else
					System.out.println("Error: invalid song!");
				break;
			
			case 2:
				System.out.println("Enter the song ID: ");
				int songID = input.nextInt();
				crudFunctions.deleteSong(songID);
				break;
		
			case 3:
				ArrayList<Song> songsList = crudFunctions.getAllSongs();
				for(Song s : songsList) {
					System.out.println(s);
				}
				break;
				
			case 4:
				System.out.println("Insert the name of the playlist: ");
				String playlistName = playlistScanner.nextLine();
				
				System.out.println("Insert the size of playlist: ");
				int numberOfSongs = playlistScanner.nextInt();
				playlist.shuffledPlaylist(allSongs, numberOfSongs);
				playlist.display();
				
				for (Song s : playlist.getSongSet()) {
					playlist.getYoutubeLinks().add(s.getLink());
				}
				playlistFunctions.sendPlaylist(playlist.getSongSet(), playlistName);
				playlist.play();
				break;
				
			case 5:
		        System.out.println("Enter the playlist name: ");
		        String nameOfPlaylist = input.nextLine();
		        HashSet<Playlist> songs = playlistFunctions.getPlaylist(nameOfPlaylist);
		        for (Playlist i : songs) {
		            System.out.println(i);
		        }
		        break;
				
			case 6:
				System.out.println("Exiting application...");
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid choice, please enter a valid choice.");
				System.out.println("Application closed");
				break;
			}
		}
	}

	@Override
	public void addSong(Song s) throws SQLException {
	
	}

	@Override
	public void updateSong(Song s) throws SQLException {
	
	}

	@Override
	public void deleteSong(int songID) {
	
	}

	@Override
	public void searchSongByArtistAndTitle(String artist, String title) {
	
	}

	@Override
	public ArrayList<Song> getAllSongs() {
		return null;
	}

	@Override
	public ArrayList<Song> searchSongsByArtist(String artist) {
		return null;
	}
}
