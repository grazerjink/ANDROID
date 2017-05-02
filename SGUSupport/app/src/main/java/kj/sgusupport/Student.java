package app.kj.sgu;

/**
 * Created by KJ on 29/09/2016.
 */

public class Student {
    String masv;
    String hoten;

    public Student(String hoten, String masv) {
        this.hoten = hoten;
        this.masv = masv;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
