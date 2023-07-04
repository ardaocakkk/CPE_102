abstract class Media {
    private String type;
    private String title;
    private String location;
    private int year;
    private String category;

    public Media(String type, String title, String location, int year, String category) {
        this.type = type;
        this.title = title;
        this.location = location;
        this.year = year;
        this.category = category;
    }
    abstract void playMedia();

    public String toString() {
        return (
                "Type: "+ getType() +"\n"+
                "Title: "+ getTitle()+ "\n"+
                "Location: "+ getLocation() +"\n"+
                "Year: "+ getYear() + "\n" +
                "Category: "+ getCategory());

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
