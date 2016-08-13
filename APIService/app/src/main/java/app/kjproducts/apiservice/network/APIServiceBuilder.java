package app.kjproducts.apiservice.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import app.kjproducts.apiservice.BuildConfig;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KJ on 13/08/2016.
 */
public class APIServiceBuilder {
    public static DemoAPIService buildAPIService(Context context, String domain) {
        //view log retrofit
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                ConnectivityManager cm =
                        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (!isConnected)
                    throw new IOException("NO_INTERNET");
                else {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            }
        }).build();

        //cau hinh gson:
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(domain)//lưu ý: domain nên kết thúc = dấu /
                // Data converter --> gson, su dung expose va version de quan ly qua trinh serialize va deseialize
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient) //okhttp
                .build();

        return retrofit.create(DemoAPIService.class);
    }
}
