package app.kjproducts.badmintontrainer.model;

/**
 * Created by KJ on 14/08/2016.
 */
public class Child {
    public static final String TABLE_NAME = "index_child";
    public static final String COL_ID_CHILD = "id_child";
    public static final String COL_IMAGE = "child_image";
    public static final String COL_CHILD = "child_title";
    public static final String COL_ID_DETAIL = "id_detail";

    //Cau truc cau lenh de tao table
    public static final String CREATE_TABLE = "create table if not exists "
            + TABLE_NAME + " ("
            + COL_ID_CHILD + " integer, "
            + COL_IMAGE + " text, "
            + COL_CHILD + " text, "
            + COL_ID_DETAIL + " integer primary key,"
            + " UNIQUE(" + COL_ID_DETAIL + "),"
            + " FOREIGN KEY(" + COL_ID_CHILD + ") REFERENCES index_exercise(id_index)"
            + ")";

    public final static String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    long id_child;
    String childImage;
    String childName;
    long id_detail;

    public Child() {
    }

    public Child(long id_child, String childImage, String childName, long id_detail) {
        this.id_child = id_child;
        this.childImage = childImage;
        this.childName = childName;
        this.id_detail = id_detail;
    }

    public String getChildImage() {
        return childImage;
    }

    public void setChildImage(String childImage) {
        this.childImage = childImage;
    }

    public long getId_detail() {
        return id_detail;
    }

    public void setId_detail(long id_detail) {
        this.id_detail = id_detail;
    }

    public long getId_child() {
        return id_child;
    }

    public void setId_child(long id_child) {
        this.id_child = id_child;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
