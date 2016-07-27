package app.graze.demoapiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by graze on 27/07/2016.
 */
public class BaseResponse {
    public static final int STATUS_SUCCESS = 200;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("status")
    @Expose
    int status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
