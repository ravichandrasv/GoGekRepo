package gojek.com.gogekweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gojek.com.gogekweather.R;
import gojek.com.gogekweather.utils.UIUtils;

/**
 * Created by Ravichandra on 5/13/2018.
 */

public class ErrorActivity extends BaseActivity implements View.OnClickListener {
    private Button mRetryButton;
    private TextView mErrorMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        registerView();
    }

    private void registerView() {
        mRetryButton = (Button) findViewById(R.id.retry_button);
        mErrorMessageTextView = (TextView) findViewById(R.id.something_text_view);
        UIUtils.regularThinTextView(new TextView[]{mErrorMessageTextView});
        mRetryButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.retry_button:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
