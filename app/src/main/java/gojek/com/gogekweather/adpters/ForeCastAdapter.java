package gojek.com.gogekweather.adpters;

/**
 * Created by Ravichandra on 5/12/2018.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import gojek.com.gogekweather.R;
import gojek.com.gogekweather.network.response.WeatherResponse;
import gojek.com.gogekweather.utils.UIUtils;

public class ForeCastAdapter extends RecyclerView.Adapter<ForeCastAdapter.MyViewHolder> {

    private List<WeatherResponse.Forecastday> foreCatList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView, tempTextView;

        public MyViewHolder(View view) {
            super(view);
            dateTextView = (TextView) view.findViewById(R.id.day_text_view);
            tempTextView = (TextView) view.findViewById(R.id.temp_text_view);
            UIUtils.regularTextView(new TextView[]{tempTextView, dateTextView});

        }
    }


    public ForeCastAdapter(Context context, List<WeatherResponse.Forecastday> forecastdays) {
        this.foreCatList = forecastdays;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fore_cat, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherResponse.Forecastday forecastday = foreCatList.get(position);
        Date currentFormate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = forecastday.getDate();
        try {


            SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("ENGLISH", "IN"));
            Date d = formatter.parse(dateInString);
            String dayOfTheWeek = sdf.format(d);
            holder.dateTextView.setText(dayOfTheWeek);

        } catch (ParseException e) {
            e.printStackTrace();
        }
       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("EEE");*/



        String temp = String.valueOf(forecastday.getDay().getMaxtempC());
        String[] tempSplit = temp.split("\\.");
        holder.tempTextView.setText(tempSplit[0] + " " + "C");

    }

    @Override
    public int getItemCount() {
        return foreCatList.size();
    }
}
