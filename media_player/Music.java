import java.util.ArrayList;
import java.util.Scanner;

public class Music extends Media{
    private String artist;
    private int numberOfSongs;



    public Music(String type, String title, String location, int year, String category, String artist, int numberOfSongs) {
        super(type, title, location, year, category);
        this.artist = artist;
        this.numberOfSongs = numberOfSongs;
    }



    void playMedia() {
        System.out.println("************************");
        System.out.println("Now Playing: "+ getTitle());
        System.out.println("************************");

    }

    @Override
    public String toString() {
        return (
                        "************************ \n"+
                        super.toString() +"\n"+
                        "Artist: " +getArtist()  +"\n"+
                        "Number of Songs: " + getNumberOfSongs()+ "\n"+
                        "************************"
                );
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }
}
