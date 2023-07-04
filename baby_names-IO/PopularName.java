
public class PopularName {
    private String ranking;
    private String name;
    private String number;

    //Constructor
    public PopularName(String ranking, String name, String number) {
        this.ranking = ranking;
        this.name = name;
        this.number = number;

    }

    //Getters and setters
    public String getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = String.valueOf(ranking);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    //toString method
    @Override
    public String toString() {
        return
                "\nRanking:" + getRanking() + "\n" +
                "Name: " + getName() + "\n" +
                "Number of named babies: " + getNumber();

    }
}
