package gojek.com.gogekweather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import gojek.com.gogekweather.R;
;
import gojek.com.gogekweather.network.apis.ApiConstant;
import gojek.com.gogekweather.network.listeners.RequestListener;
import gojek.com.gogekweather.network.request.WeatherRequest;
import gojek.com.gogekweather.network.response.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RequestListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherRequest request = new WeatherRequest(ApiConstant.API_EVENT_REQUEST_URL, this, this);
        request.execute();

    }

    @Override
    public void onStart(int apiRequestCode) {

    }

    @Override
    public void onFinish(int apiRequestCode) {

    }

    @Override
    public void onError(int apiRequestCode, String code, String message) {
        Log.v("Messaage Eror","Error mesaage::"+message+apiRequestCode);
    }

    @Override
    public void onSuccess(int apiRequestCode, Object response) {
        switch (apiRequestCode) {
            case ApiConstant.API_EVENT_REQUEST_URL:
                  Log.v("User Profile", "UserPr::" + response.toString());
                WeatherResponse userProfileResponse = (WeatherResponse) response;
                Log.v("Response get::","Obtained::"+userProfileResponse.getCurrent().getLastUpdated());


                break;
        }
    }
}
