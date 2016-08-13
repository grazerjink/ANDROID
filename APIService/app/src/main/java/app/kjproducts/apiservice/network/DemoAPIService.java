package app.kjproducts.apiservice.network;

import app.kjproducts.apiservice.model.User;
import app.kjproducts.apiservice.model.response.BaseResponse;
import app.kjproducts.apiservice.model.response.GetAPIKey;
import app.kjproducts.apiservice.model.response.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by KJ on 13/08/2016.
 */
public interface DemoAPIService {
    @GET("getAPIKey")
    Observable<GetAPIKey> getAPIKey();

    @POST("login")
    Observable<LoginResponse> login(@Header("Content-Type") String contentType,@Body User user);
}
