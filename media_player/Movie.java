public class Movie extends Media{
    private String director;
    private int playingTime;

    public Movie(String type, String title, String location, int year, String category, String director, int playingTime) {
        super(type, title, location, year, category);
        this.director = director;
        this.playingTime = playingTime;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    @Override
    void playMedia() {

        System.out.println("------------------------");
        System.out.println("Now playing: "+ getTitle());
        System.out.println("------------------------");

    }

    @Override
    public String toString(){
        return (
                "------------------------ \n"+
                super.toString() +"\n"+
                "Director: " + getDirector() +"\n"+
                "Duration: " + getPlayingTime() + "\n" +
                "------------------------"
                );
    }
}
