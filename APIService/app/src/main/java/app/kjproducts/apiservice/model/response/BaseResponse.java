package app.kjproducts.apiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KJ on 13/08/2016.
 */
public class BaseResponse {
    @SerializedName("status")
    @Expose
    boolean status;
    @SerializedName("error_message")
    @Expose
    String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
