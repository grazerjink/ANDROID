package app.kjproducts.apiservice.utils;

import android.content.Context;

/**
 * Created by KJ on 13/08/2016.
 */
public class APIKeySharePreference {

    public static void saveAPIKey(Context context, String apikey) {
        //TODO: hoan thanh phuong thuc nay
        SharedPreferenceUtil.putString(context, "apikey", apikey);
    }

    public static void saveToken(Context context, String token) {
        //TODO: hoan thanh phuong thuc nay
        SharedPreferenceUtil.putString(context, "token", token);
    }

    public static String getKey(Context context) {
        return SharedPreferenceUtil.getString(context, "apikey", "");
    }

    public static String getToken(Context context) {
        return SharedPreferenceUtil.getString(context, "token", "");
    }
}
