package gojek.com.gogekweather.network.request;

import android.content.Context;
import android.util.Log;


import gojek.com.gogekweather.network.apis.ApiClient;
import gojek.com.gogekweather.network.apis.ApiConstant;
import gojek.com.gogekweather.utils.NetworkUtils;
import gojek.com.gogekweather.utils.StringUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class BaseRequest implements Callback<ResponseBody> {

    private Context mContext;
    private int mApiRequestCode;
    protected ApiClient client;

    protected void initialise(int apiRequestCode, Context context) {
        this.mContext = context;
        this.mApiRequestCode = apiRequestCode;
        client = new ApiClient(mContext);
    }

    protected void execute() {
        if (NetworkUtils.isNetworkAvailable(mContext)) {
            doRequest(mApiRequestCode);
        } else {
            onErrorResponse(mApiRequestCode, ApiConstant.NO_NETWORK_ERROR_CODE, "No Internet");
        }
    }

    protected abstract void doRequest(int apiRequestCode);

    protected abstract void onErrorResponse(int apiRequestCode, String code, String message);

    protected abstract void onSuccessResponse(int apiRequestCode, Object response);

    protected abstract void doProcessResponse(int apiRequestCode, String response);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        Log.i("onResponse = ", "header" + response.body() + "header" + response.headers());

        if (response.code() != ApiConstant.EVENT_SUCCESS_CODE) {
            onErrorResponse(mApiRequestCode, String.valueOf(response.code()), response.message());
        } else {
            try {
                String responseBody = response.body().string();
                Log.i("onResponse=", "Api Response : " + responseBody);

                if (StringUtils.isNullOrEmpty(responseBody)) {
                    throwGeneralError();
                } else {
                    //NetworkUtils.checkJSessionId(response);

                    responseBody = responseBody.replaceAll("\n", "");
                    responseBody = responseBody.replaceAll("\t", "");
                    doProcessResponse(mApiRequestCode, responseBody);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throwGeneralError();
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        throwGeneralError();
    }

    private void throwGeneralError() {
        onErrorResponse(mApiRequestCode, ApiConstant.GENERAL_ERROR_CODE, "Something went wrong. Please try again later");
    }

}
