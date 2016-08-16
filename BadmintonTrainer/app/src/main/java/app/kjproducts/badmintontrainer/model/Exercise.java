package app.kjproducts.badmintontrainer.model;

/**
 * Created by KJ on 14/08/2016.
 */
public class Exercise {
    public static final String TABLE_NAME = "index_exercise";
    public static final String COL_ID_INDEX = "id_index";
    public static final String COL_TITLE = "title";

    //Cau truc cau lenh de tao table
    public static final String CREATE_TABLE = "create table if not exists "
            + TABLE_NAME + " ("
            + COL_ID_INDEX + " integer auto increment primary key, "
            + COL_TITLE + " text"
            + ")";

    public final static String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    private long id_index;
    private String titleName;

    public Exercise() {
    }

    public Exercise(String titleName) {
        this.titleName = titleName;
    }

    public Exercise(long id_index, String title) {
        this.id_index = id_index;
        this.titleName = title;
    }

    public long getId_index() {
        return id_index;
    }

    public void setId_index(long id_index) {
        this.id_index = id_index;
    }

    public String getTitle() {
        return titleName;
    }

    public void setTitle(String title) {
        this.titleName = title;
    }
}
