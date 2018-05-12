package gojek.com.gogekweather.network.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import gojek.com.gogekweather.network.listeners.RequestListener;
import gojek.com.gogekweather.network.response.WeatherResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Ravichandra on 5/12/2018.
 */

public class WeatherRequest extends BaseRequest {

    private RequestListener<WeatherResponse> mListener;
    private Map mBody;
    private int mApiRequestcode;
    private String mMyString;
    private Context mContext;
    private String id;

    public WeatherRequest(int apiRequestcode, Context context, RequestListener<WeatherResponse> listener) {
        super.initialise(apiRequestcode, context);
        mApiRequestcode = apiRequestcode;
        mListener = listener;
        mContext = context;

        mBody = new HashMap<>();
        mBody.put("id", id);

        /*mBody.put("source", "android");
        mBody.put("source_token", deviceRegistrationToken );*/
    }



    public void execute() {

        mListener.onStart(mApiRequestcode);
        super.execute();
    }

    @Override
    protected void doRequest(int apiRequestcode) {
        Log.i("client", "client== " + client);
        Log.i("client", "=====body " + mBody);

        Call<ResponseBody> apiCall = client.getApiService().getWeather();
        apiCall.enqueue(this);
    }

    @Override
    protected void onErrorResponse(int apiRequestcode, String code, String message) {
        mListener.onFinish(apiRequestcode);
        mListener.onError(apiRequestcode, code, message);
    }

    @Override
    protected void onSuccessResponse(int apiRequestcode, Object response) {
        mListener.onFinish(apiRequestcode);
        if (response instanceof WeatherResponse) {
            mListener.onSuccess(apiRequestcode, (WeatherResponse) response);
        }
    }

    @Override
    protected void doProcessResponse(int apiRequestcode, String responseString) {
        WeatherResponse apiaiResponse = new Gson().fromJson(responseString, WeatherResponse.class);

        if (apiaiResponse != null) {
            onSuccessResponse(apiRequestcode, apiaiResponse);
        } else {
            onErrorResponse(apiRequestcode, String.valueOf(apiaiResponse), String.valueOf(apiaiResponse));
        }
    }
}
