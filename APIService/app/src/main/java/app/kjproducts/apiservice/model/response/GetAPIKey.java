package app.kjproducts.apiservice.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KJ on 13/08/2016.
 */
public class GetAPIKey extends BaseResponse {
    @SerializedName("APIKey")
    @Expose
    String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
