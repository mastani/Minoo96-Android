package ir.minoo96.Items;

public class Post {

    int id, candidateId;
    String content, image, time;
    int likes, comments;
    boolean userLiked = false;

    public Post() {
    }

    public Post(int id, int candidateId, String content, String image, String time, int likes, int comments, boolean userLiked) {
        this.id = id;
        this.candidateId = candidateId;
        this.content = content;
        this.image = image;
        this.time = time;
        this.likes = likes;
        this.comments = comments;
        this.userLiked = userLiked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public boolean isUserLiked() {
        return userLiked;
    }

    public void setUserLiked(boolean userLiked) {
        this.userLiked = userLiked;
    }
}
