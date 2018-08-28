package com.example.king.kidstime.mvp.View.TimeKeeping.Tabs;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by KING on 04.01.2018.
 */

public class TimeDialog implements View.OnClickListener{
    Context context;
    private int hours;
    private int min;
    EditText btn;

    public TimeDialog(Context context, EditText btn){
        this.context = context;
        final Calendar c = Calendar.getInstance();
        hours = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);

        this.btn = btn;
        btn.setText(getTime());

        TimePickerDialog dialog = new TimePickerDialog(context , listener, hours, min, true);
        dialog.show();
    }

    private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hours = hourOfDay;
            min = minute;
            btn.setText(getTime());
            Log.d("TIME", "hour: " + hours + " minute: " + min);
        }
    };


    @Override
    public void onClick(View v) {
    }

    public String getTime(){
        return hours+ ":" + min;
    }
}
