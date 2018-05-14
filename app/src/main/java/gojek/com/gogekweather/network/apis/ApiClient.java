package gojek.com.gogekweather.network.apis;

import android.content.Context;
import android.util.Log;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private APIService apiService;

    public ApiClient(Context context) {
        try {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
            httpClient.addInterceptor(logging);

            Retrofit retrofit;

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstant.BASE_URL4)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.i("retrofit", "===" + retrofit);

            apiService = retrofit.create(APIService.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public APIService getApiService() {
        return apiService;
    }
}
