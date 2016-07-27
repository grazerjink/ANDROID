package app.graze.demoapiservice.network;

import android.content.Context;

import com.google.gson.GsonBuilder;

import app.graze.demoapiservice.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by graze on 27/07/2016.
 */
public class APIServiceBuilder {
    //Bước này là cấu hình API
    //Tại sao lại có Context
    public static ListAPIService buildAPIService(Context context, String domain) {
        //View Log retrofit
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging).build();

        //Cau hinh GSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(domain) //Nên kết thúc bằng dấu /....
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .setVersion((double) BuildConfig.VERSION_CODE).create())
                //Thêm 1 converterFactory để chuyển đổi ngôn ngữ GSON trong phương
                        // ta phải new 1 Builder().///.....
                        //Trong GSON có method
                        // .excludeFieldsWithoutExposeAnnotation() xử lý theo các Annotation để đặt trong class User
                        //.setVersion((double) BuildConfig.VERSION_CODE) đang bí :)))
                )
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient) //okhttp
                .build();

        return retrofit.create(ListAPIService.class);
    }
}
