package app.graze.demoapiservice.network;

import app.graze.demoapiservice.model.User;
import app.graze.demoapiservice.model.response.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by graze on 27/07/2016.
 */
public interface ListAPIService {
    @POST("login")
    Observable<LoginResponse> login(@Body User user);

    //Muốn sử dụng Interface phải xây dựng 1 Builder để chạy
}
