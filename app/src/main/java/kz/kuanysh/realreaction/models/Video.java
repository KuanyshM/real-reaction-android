package kz.kuanysh.realreaction.models;

public class Video {
    private String id;
    private String title;
    private String imageURL;
    private String videoURL;

    public Video(String id, String title, String imageURL, String videoURL) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getVideoURL() {
        return videoURL;
    }
}
