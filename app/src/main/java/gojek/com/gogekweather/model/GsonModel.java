package gojek.com.gogekweather.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class GsonModel {

    private static Gson _gson;
    public static Gson gson() {
        if (_gson == null) {
            _gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
        }
        return _gson;
    }

}
