package graze.demoasynctask;

/**
 * Created by HOCVIEN on 6/24/2016.
 */
public class Country {
    String name;
    String url;

    public Country(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
