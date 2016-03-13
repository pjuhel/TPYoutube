package s8.fr.esilv.tpyoutube.Objects;

/**
 * Created by juhel on 11/03/2016.
 */
public class Snippet {
    private String publishedAt;
    private String chanelid;
    private String title;
    private String description;
    private Thumbnails thumbnails;
    private String chanelTitle;
    private String liveBroadcastContent;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChanelid() {
        return chanelid;
    }

    public void setChanelid(String chanelid) {
        this.chanelid = chanelid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChanelTitle() {
        return chanelTitle;
    }

    public void setChanelTitle(String chanelTitle) {
        this.chanelTitle = chanelTitle;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }
}
