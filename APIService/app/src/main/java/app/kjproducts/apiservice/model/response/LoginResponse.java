package app.kjproducts.apiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KJ on 13/08/2016.
 */
public class LoginResponse extends BaseResponse {
    @SerializedName("access_token")
    @Expose
    String token;
}
