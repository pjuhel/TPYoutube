package s8.fr.esilv.tpyoutube;

/**
 * Created by juhel on 26/02/2016.
 */
public class Video {
    private String description;
    private String title;
    private String thumbnails;
    private String id;

    public Video(String description, String title, String thumbnails, String id) {
        this.description = description;
        this.title = title;
        this.thumbnails = thumbnails;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnails() {
        return thumbnails;
    }
}
