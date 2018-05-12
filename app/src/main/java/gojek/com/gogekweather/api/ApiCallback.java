package gojek.com.gogekweather.api;

import com.google.gson.JsonElement;

/**
 * Created by AL1444 on 21-08-2017.
 */

public interface ApiCallback {
    public boolean apiResponse(boolean success, int statusCode, JsonElement json);

}
