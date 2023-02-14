import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Song {
	// fields
	private int songID;
	private String artist;
	private String title;
	private double duration;
	private String songType;
	private String link;
	private enum genres {rock, pop, jazz};
	
	// constructor 1 with songID
	public Song(int SongID, String artist, String title, double duration, String songType, String link) {
		this.songID = songID;
		this.artist = artist;
		this.title = title;
		this.duration = duration;
		this.songType = songType;
		this.link = link;
	}
	
	// constructor 2 without songID
	public Song(String artist, String title, double duration, String songType, String link) {
		this.artist = artist;
		this.title = title;
		this.duration = duration;
		this.songType = songType;
		this.link = link;
	}
	
	// Getters and setters
	public int getSongID() {
		return songID;
	}
	public void setSongID(int songID) {
		this.songID = songID;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getSongType() {
		return songType;
	}
	public void setSongType(String songType) {
		this.songType = songType;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Song: " + "Artist: " + artist + ", Denumire: " + title + ", Durata: " + duration + ", Gen: " + songType
				+ ", link: " + link;
	}
	
	// Validation functions
	public boolean verifyArtist(String s) {
		if(s.length() >= 10) {
			return true;
		} 
			return false;
	}
	
	public  boolean verifySongTitle(String s) {
		if(s.split(" ").length >= 2) {
			return true;
		}
			return false;
	}
	
	public boolean verifyDuration(Double d) {
		if(d > 0) {
			return true;
		}
			return false;
	}
	
	public boolean verifySongType(String s) {
		for(genres g : genres.values()) {
			if(g.name().equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyYouTubeLink(String s) {
		String linkPattern = "^http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?‌​[\\w\\?‌​=]*)?$";
		Pattern pattern = Pattern.compile(linkPattern);
		Matcher matcher = pattern.matcher(s);
		boolean matchFound = matcher.find();
		if(matchFound)
			return true;
		else
			return false;
	}
	
	public boolean validateSong() {

		if(verifyArtist(this.getArtist()) && verifySongTitle(this.getTitle()) && verifyDuration(this.getDuration()) && verifySongType(this.getSongType()) && verifyYouTubeLink(this.link)) {
			return true;
		}
		return false;
	}
	
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Artist: " + artist);
		System.out.println("Title: " + title);
		System.out.println("Duration: " + duration);
		System.out.println("Type: " + songType);
		System.out.println("Link: " + link);
	}
}
