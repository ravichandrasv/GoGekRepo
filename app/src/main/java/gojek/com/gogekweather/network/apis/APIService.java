package gojek.com.gogekweather.network.apis;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIService {

    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("forecast.json?key=0c5ac43cc8af483bb08105031181105&days=5&q=12.9716,77.5946")
    Call<ResponseBody> getWeather();



}