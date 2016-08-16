package app.kjproducts.badmintontrainer.model;

/**
 * Created by KJ on 14/08/2016.
 */
public class Detail {
    public static final String TABLE_NAME = "index_detail";
    public static final String COL_ID_DETAIL = "id_detail";
    public static final String COL_ID = "id";
    public static final String COL_IMAGE = "image";
    public static final String COL_CONTENT = "content";

    //Cau truc cau lenh de tao table
    public static final String CREATE_TABLE = "create table if not exists "
            + TABLE_NAME + " ("
            + COL_ID_DETAIL + " integer, "
            + COL_ID + " integer,"
            + COL_IMAGE + " text, "
            + COL_CONTENT + " text, "
            + " primary key(id_detail, id),"
            + " UNIQUE(" + COL_ID + "),"
            + " FOREIGN KEY(" + COL_ID_DETAIL + ") REFERENCES index_child(id_detail)"
            + ")";

    public final static String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    long id_detail;
    long id;
    String image;
    String content;

    public Detail() {
    }

    public Detail(long id_detail, long id, String image, String content) {
        this.id_detail = id_detail;
        this.id = id;
        this.image = image;
        this.content = content;
    }

    public long getId_detail() {
        return id_detail;
    }

    public void setId_detail(long id_detail) {
        this.id_detail = id_detail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
