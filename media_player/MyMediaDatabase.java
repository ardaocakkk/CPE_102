import java.util.ArrayList;

public class MyMediaDatabase implements IMediaDatabase{
    private ArrayList<Media> mediaList = new ArrayList<>();
    public MyMediaDatabase() {

    }

    public ArrayList<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(ArrayList<Media> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public void addEntry(Media newMedia) {
        if(mediaList.contains(newMedia)){
            System.out.println("The Media that you want to add is already exists.");
        }
        mediaList.add(newMedia);
    }

    @Override
    public Media lookUpByTitle(String title) {
        for(Media media : mediaList){
            if(media.getTitle().equals(title)){
                System.out.println("I found your media!");
                return media;
            }
        }
        System.out.println("This media doesn't exists.");
        return null;
    }
    //Print all media's titles and types.
    @Override
    public void listAllMedia() {
        System.out.println("Here is types and titles of every Media objects.");
        System.out.println("------------------------");
        for(Media media: mediaList) {
            System.out.println(media.getType()+ " " + media.getTitle());
        }
        System.out.println("------------------------");

    }

    @Override
    public void listMediaByType(String Type) {
        System.out.println("You're looking for Category: " + Type);
        System.out.println("------------------------");
        if (mediaList.size() == 0) {
            System.out.println("This database is empty!");
        }
        else {
            for(Media media: mediaList) {
                if(media.getType().equalsIgnoreCase(Type)) { //Simply equalsIgnoreCase method only does is make first letter lowercase.
                    System.out.println(media.getTitle() +" at index :" + mediaList.indexOf(media));
                }
            }
        }

        System.out.println("------------------------");
    }
}

