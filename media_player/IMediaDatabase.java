public interface IMediaDatabase {
    void addEntry(Media newMedia);
    Media lookUpByTitle(String title);
    void listAllMedia();
    void listMediaByType(String Type);

}
