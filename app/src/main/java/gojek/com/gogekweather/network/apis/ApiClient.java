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

          /*  OkHttpClient.Builder okHttpClientBuilder = getClientBuilder();
            okHttpClientBuilder.addInterceptor(new APIRequestInterceptor(context));

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();


            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClientBuilder.addInterceptor(loggingInterceptor)
                    .readTimeout(ApiConstant.MAX_TIMEOUT_DURATION, TimeUnit.MILLISECONDS);*/

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

    private OkHttpClient.Builder getClientBuilder() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder;
        } catch (NoSuchAlgorithmException e) {
            return new OkHttpClient.Builder();
        } catch (KeyManagementException e) {
            return new OkHttpClient.Builder();
        }
    }

    public APIService getApiService() {
        return apiService;
    }
}
