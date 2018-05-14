package gojek.com.gogekweather.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gojek.com.gogekweather.R;


public class DialogUtils {
    private DialogUtils() {

    }


    public static Dialog getLoadingProgressDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.progressbar_dailog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCancelable(false);
        ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progress_bar1);
        Animation rotate = AnimationUtils.loadAnimation(context,
                R.anim.rotate);
        progressBar.startAnimation(rotate);


        return dialog;
    }


}
