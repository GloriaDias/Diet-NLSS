package com.example.venussteve.notify_me;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Venus Steve on 21-03-2017.
 */

public class notify_me_dinner extends Activity {

    private EditText edtTime;
    private EditText edtTime1;
    /*   private EditText edtTime2;
       private EditText edtTime3;
       private EditText edtTime4;*/
    Calendar c = Calendar.getInstance();
    Calendar c1 = Calendar.getInstance();

    /*   Calendar c2 = Calendar.getInstance();
       Calendar c3 = Calendar.getInstance();
       Calendar c4 = Calendar.getInstance();
   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dinner);
        initializeView();

        findViewById(R.id.dinner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String res = edtTime.getText().toString();
                String[] ser = res.split(":");
                int ser1= Integer.parseInt(ser[0]);
                int ser2= Integer.parseInt(ser[1]);
                c.set(Calendar.HOUR_OF_DAY, ser1);
                c.set(Calendar.MINUTE, ser2);
                */


                c.set(Calendar.HOUR_OF_DAY, 00);
                c.set(Calendar.MINUTE, 00);

                Intent intent = new Intent(getApplicationContext(), Notification_receiver_dinner.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        });

        Button bt = (Button) findViewById(R.id.next4);
        bt.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent i = new Intent(getApplicationContext(),notify_me.class);
                                      startActivity(i);
                                  }
                              }
        );



    }
    private void initializeView(){
        edtTime = (EditText) findViewById(R.id.dinn);
        setCurrentDateOnView();
    }

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int min) {
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, min);
            setCurrentDateOnView();
        }
    };
    public void timeOnClick(View view){
        new TimePickerDialog(notify_me_dinner.this,time, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    public void setCurrentDateOnView(){
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
        edtTime.setText(stf.format(c.getTime()));
    }



}
