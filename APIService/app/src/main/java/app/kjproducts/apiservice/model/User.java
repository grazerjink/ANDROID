package app.kjproducts.apiservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KJ on 13/08/2016.
 */
public class User {
    @SerializedName("APIKey")
    @Expose
    String api;
    @SerializedName("user_name")
    @Expose
    String userName;
    @SerializedName("pass")
    @Expose(deserialize = false)
    String pass;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
