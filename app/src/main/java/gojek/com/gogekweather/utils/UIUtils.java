package gojek.com.gogekweather.utils;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Ravichandra on 1/30/2018.
 */

public class UIUtils {
    public static void regularTextView(TextView[] viewFields) {
        for (TextView fields : viewFields) {
            Typeface typeface = Typeface.createFromAsset(fields.getContext().getAssets(), "font/roboto.regular.ttf");
            fields.setTypeface(typeface);
        }
    }
    public static void regularBoldTextView(TextView[] viewFields) {
        for (TextView fields : viewFields) {
            Typeface typeface = Typeface.createFromAsset(fields.getContext().getAssets(), "font/roboto.bold.ttf");
            fields.setTypeface(typeface);
        }
    }
    public static void regularThinTextView(TextView[] viewFields) {
        for (TextView fields : viewFields) {
            Typeface typeface = Typeface.createFromAsset(fields.getContext().getAssets(), "font/roboto.thin.ttf");
            fields.setTypeface(typeface);
        }
    }

}
