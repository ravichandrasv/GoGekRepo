package gojek.com.gogekweather.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import gojek.com.gogekweather.R;
;
import gojek.com.gogekweather.adpters.ForeCastAdapter;
import gojek.com.gogekweather.network.apis.ApiConstant;
import gojek.com.gogekweather.network.listeners.RequestListener;
import gojek.com.gogekweather.network.request.WeatherRequest;
import gojek.com.gogekweather.network.response.WeatherResponse;
import gojek.com.gogekweather.utils.NetworkUtils;
import gojek.com.gogekweather.utils.UIUtils;
;

public class MainActivity extends BaseActivity implements RequestListener, View.OnClickListener {
    private TextView mTempTextView, mPlaceTextView;
    private RecyclerView mForecastRecyclerView;
    private DividerItemDecoration mDividerItemDecoration;
    private ForeCastAdapter mForeCastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callApi();
        registerView();

    }

    private void registerView() {
        mTempTextView = (TextView) findViewById(R.id.temp_text_view);
        mPlaceTextView = (TextView) findViewById(R.id.place_text_view);
        mForecastRecyclerView = (RecyclerView) findViewById(R.id.fore_cast_recycler_view);
        UIUtils.regularThinTextView(new TextView[]{mPlaceTextView});
        UIUtils.regularBoldTextView(new TextView[]{mTempTextView});
    }

    private void callApi() {
        //check network
        if (NetworkUtils.isNetworkAvailable(this)) {

        }
        WeatherRequest request = new WeatherRequest(ApiConstant.API_EVENT_REQUEST_URL, this, this);
        request.execute();
    }

    @Override
    public void onStart(int apiRequestCode) {
        mProgressDialog.show();
    }

    @Override
    public void onFinish(int apiRequestCode) {
        mProgressDialog.dismiss();

    }

    @Override
    public void onError(int apiRequestCode, String code, String message) {
        //  Log.v("Messaage Eror", "Error mesaage::" + message + apiRequestCode);
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSuccess(int apiRequestCode, Object response) {
        switch (apiRequestCode) {
            case ApiConstant.API_EVENT_REQUEST_URL:
                WeatherResponse weatherResponse = (WeatherResponse) response;
                mPlaceTextView.setText(weatherResponse.getLocation().getName().split("\\s+")[0]);
                String temp = String.valueOf(weatherResponse.getCurrent().getTempC());
                String[] tempSplit = temp.split("\\.");
                mTempTextView.setText(tempSplit[0] + "\u00B0");
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                mForecastRecyclerView.setHasFixedSize(true);
                mForecastRecyclerView.setLayoutManager(layoutManager);
                mForecastRecyclerView.setLayoutManager(layoutManager);
                mForeCastAdapter = new ForeCastAdapter(getApplicationContext(), weatherResponse.getForecast().getForecastday());
                mDividerItemDecoration = new DividerItemDecoration(mForecastRecyclerView.getContext(), layoutManager.getOrientation());
                mForecastRecyclerView.addItemDecoration(mDividerItemDecoration);
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top);
                mForecastRecyclerView.startAnimation(hyperspaceJumpAnimation);
                mForecastRecyclerView.setAdapter(mForeCastAdapter);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
