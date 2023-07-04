import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyMediaDatabase database = new MyMediaDatabase();
        Scanner input = new Scanner(System.in);
        String chosenProcess = "234234234";


        while(Integer.parseInt(chosenProcess) != 0) {
            System.out.println("""
                ------------------------
                What do You want to do?
                0) Exit.
                1) Add Media.
                2) Play Media.
                3) List all Media.
                4) Search by Title.
                5) List by Type.
                ------------------------
                """);
            /*

            I used parseInt instead of declaring chosenProcess as an integer data type. Because if I make chosenProcess integer
            then scanner skips all input.Nextline() lines.

             */
            chosenProcess = input.nextLine();
            if(Integer.parseInt(chosenProcess) == 1) {
                database.addEntry(doAddEntry());
            }
            else if(Integer.parseInt(chosenProcess) == 2) {
                database.listAllMedia();
                System.out.println("Which media you want to play?: ");
                String chosenMedia = input.nextLine();
                database.lookUpByTitle(chosenMedia).playMedia();
            }
            else if(Integer.parseInt(chosenProcess) == 3) {
                database.listAllMedia();
            }
            else if(Integer.parseInt(chosenProcess) == 4) {
                System.out.println("Please enter the media's title: ");
                String mediaTitle = input.nextLine();
                database.lookUpByTitle(mediaTitle);
            }
            else if (Integer.parseInt(chosenProcess) == 5) {
                System.out.println("Which type of Media you want to search?: ");
                String mediaType = input.nextLine();
                database.listMediaByType(mediaType);
            }
            else {
                System.out.println("You've just made a typo. Please enter a valid process.");
            }
        }
    }

    public static void testMain(MyMediaDatabase database) {
        Movie movie1 = new Movie("Movie","Shawshank Redemption","./movies/shawshank_redemption.mp4",1994,"Drama","Frank Darabont",142);
        Music music1 = new Music("Music","Bohemian Rhapsody","./music/bohemian_rhapsody.mp3",1975,"Rock","Queen",1);
        Movie movie2 = new Movie("Movie","The Lord of The Rings", "./movies/thelordoftherings.mp4",2003,"Action, Adventure, Drama","Peter Jackson",201);
        Music music2 = new Music("Music", "November Rain", "./music/november_rain.mp3", 1992,"Rock","Guns N' Roses", 1);

        database.addEntry(movie1);
        database.addEntry(music1);
        database.addEntry(music2);
        database.addEntry(movie2);
        database.addEntry(movie2);

        database.lookUpByTitle("Bohemian Rhapsody");
        database.lookUpByTitle("Shawshank Redemption");
        database.lookUpByTitle("The Lord of The Rings");
        database.lookUpByTitle("November Rain");
        database.lookUpByTitle("November rain");

        database.listAllMedia();
        database.listMediaByType("Music");
        database.listMediaByType("Movie");

        database.lookUpByTitle("Bohemian Rhapsody").playMedia();
        database.lookUpByTitle("Shawshank Redemption").playMedia();
        database.lookUpByTitle("The Lord of The Rings").playMedia();
        database.lookUpByTitle("November Rain").playMedia();
    }

    public static Media doAddEntry() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter media type (Music/Movie): ");
        String mediaType = input.nextLine();
        System.out.println("------------------------ \nEnter Title: ");
        String mediaTitle = input.nextLine();
        System.out.println("------------------------ \nEnter Location:");
        String mediaLocation = input.nextLine();
        System.out.println("------------------- \nEnter year: ");
        int mediaReleaseYear = input.nextInt();
        System.out.println("------------------------ \nEnter Category: ");
        String mediaCategory = input.nextLine();
        input.nextLine();


        if(mediaType.equals("Movie") || mediaType.equals("movie")) {
            System.out.println("------------------------ \nEnter Director: ");
            String movieDirector = input.next();
            System.out.println("------------------------ \nEnter Duration : ");
            int movieDuration = input.nextInt();
            return new Movie(mediaType,mediaTitle,mediaLocation,mediaReleaseYear,mediaCategory,movieDirector,movieDuration);
        }
        else if(mediaType.equals("Music") || mediaType.equals("music")) {
            System.out.println("------------------------ \nEnter Artist: ");
            String musicArtist = input.next();
            System.out.println("------------------------ \nEnter number of songs: ");
            int musicNumber = input.nextInt();
            return new Music(mediaType,mediaTitle,mediaLocation,mediaReleaseYear,mediaCategory,musicArtist,musicNumber);
        }

        System.out.println("You've made a typo. Please enter again. \n-------------------------");
        return doAddEntry();
    }
}