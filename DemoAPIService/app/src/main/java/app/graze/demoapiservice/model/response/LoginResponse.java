package app.graze.demoapiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.graze.demoapiservice.model.User;

/**
 * Created by graze on 27/07/2016.
 */
public class LoginResponse extends BaseResponse{
    //Cần có 1 cái phản hồi cơ bản
    //cho dù user nhập sai hoặc đúng
    //Nên ta phải extends từ BaseResponse
    //Để có đt message và status
    @SerializedName("data")
    @Expose
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
