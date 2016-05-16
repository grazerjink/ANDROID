package my.country.flag;

/**
 * Created by graze on 14/05/2016.
 */
public class Country {
    private String enName;
    private String viName;

    public Country(String viName, String enName) {
        this.enName = enName;
        this.viName = viName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getViName() {
        return viName;
    }

    public void setViName(String viName) {
        this.viName = viName;
    }
}
