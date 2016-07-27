package app.graze.demoapiservice.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by graze on 27/07/2016.
 */
public class BaseRequest {
    @SerializedName("token")
    @Expose
    String token;
}
