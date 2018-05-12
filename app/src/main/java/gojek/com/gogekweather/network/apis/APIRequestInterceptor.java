package gojek.com.gogekweather.network.apis;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class APIRequestInterceptor implements Interceptor {

    private Context mContext;

    public APIRequestInterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Accept", "application/json")
                .method(original.method(), original.body());

        //requestBuilder.addHeader("User-Agent", "PA");

        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}