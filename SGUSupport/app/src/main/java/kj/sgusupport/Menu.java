package kj.sgusupport;

/**
 * Created by KJ on 05/10/2016.
 */

public class Menu {

    String tenDM;
    String hinhDM;

    public Menu(String tenDM, String hinhDM) {
        this.tenDM = tenDM;
        this.hinhDM = hinhDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getHinhDM() {
        return hinhDM;
    }

    public void setHinhDM(String hinhDM) {
        this.hinhDM = hinhDM;
    }
}
