package gojek.com.gogekweather.api;

import android.util.Log;

import com.google.gson.JsonElement;


import gojek.com.gogekweather.model.GsonModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AL1444 on 25-05-2017.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    private static Callback<ResponseBody> jsonCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            int statusCode = response.code();
            ResponseBody body = (response.isSuccessful()) ? response.body() : response.errorBody();
            JsonElement json = null;
            String bodyString = null;
            try {  // and get the raw json that comes back
                bodyString = body.string();
                Log.i("Api.java", "json " + bodyString);
                json = GsonModel.gson().fromJson(bodyString, JsonElement.class);
            } catch (Exception e) {
                Log.e("Api.java", "Api error", e);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("Api.java", "Api error", t);

        }
    };
}
