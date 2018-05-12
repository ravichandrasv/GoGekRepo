package gojek.com.gogekweather.api;


import java.util.List;
import gojek.com.gogekweather.model.Astro;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiService {

    @GET("forecast.json?key=0c5ac43cc8af483bb08105031181105&days=5&q=12.9716,77.5946")
    Call<List<Astro>> getIncidents();


}
