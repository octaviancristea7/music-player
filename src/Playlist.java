import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.awt.Desktop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.desktop.*;
import java.net.URI;
import java.net.URISyntaxException;

public class Playlist {

	private int playlistID;
	private String playlistName;
	private HashSet<Song> songSet;
	private int numberOfTracks;
	private BlockingQueue<String> youtubeLinks = new ArrayBlockingQueue<>(10);
	
	public Playlist(int playlistID, String playlistName, HashSet<Song> songSet, int numberOfTracks) {
		super();
		this.playlistID = playlistID;
		this.playlistName = playlistName;
		this.songSet = songSet;
		this.numberOfTracks = numberOfTracks;
		
	}
	
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
	}
	
	public BlockingQueue<String> getYoutubeLinks() {
		return youtubeLinks;
	}

	public void setYoutubeLinks(BlockingQueue<String> youtubeLinks) {
		this.youtubeLinks = youtubeLinks;
	}

	public Playlist() {
		songSet = new HashSet<Song>();
	}
	

	public int getPlaylistID() {
		return playlistID;
	}

	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public HashSet<Song> getSongSet() {
		return songSet;
	}

	public void setSongSet(HashSet<Song> songSet) {
		this.songSet = songSet;
	}
	
	public void shuffledPlaylist (ArrayList<Song> list, int totalItems) {
		Random random = new Random();
		while(songSet.size() < totalItems) {
			int randomIndex = random.nextInt(list.size());
			songSet.add(list.get(randomIndex));
		}
	}
	
	public void display() {
		for (Song song: songSet) {
			song.display();
		}
	}
	
	public void play() {
		while(!youtubeLinks.isEmpty()) {
			try {
				String youtubeLink = youtubeLinks.take();
				Desktop.getDesktop().browse(new URI(youtubeLink));
			} catch (InterruptedException | IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}	
}
