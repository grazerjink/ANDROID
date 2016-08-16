package app.kjproducts.badmintontrainer.model;

/**
 * Created by KJ on 16/08/2016.
 */
public class Comment {
    public static final String TABLE_NAME = "index_comment";
    public static final String COL_ID = "id";
    public static final String COL_USER = "user_name";
    public static final String COL_COMMENT = "comment";

    long id;
    String user;
    String comment;

    public Comment(String comment, long id, String user) {
        this.comment = comment;
        this.id = id;
        this.user = user;
    }

    public Comment(String user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public Comment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
