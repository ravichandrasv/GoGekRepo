package gojek.com.gogekweather.api;

/**
 * Created by AL1444 on 21-08-2017.
 */

public class ApiUtils {
    public static final String SITE_KEY_URL = "https://10.200.75.58/Auth/SignIn";

    public static final String BASE_URL = "http://172.16.14.66:8085/SOCAPI/";

//    public static final String BASE_URL = "http://172.16.15.137:8085/SOCAPI/";
//    public static final String BASE_URL = "http://121.123.229.155:7090/SOCAPI/";
//    public static final String BASE_URL = "http://121.123.229.155:8090/SOCAPI/";

    public static ApiService getApiService() {
        return ApiClient.getClient(BASE_URL).create(ApiService.class);
    }
}
