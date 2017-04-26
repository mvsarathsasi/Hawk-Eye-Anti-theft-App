package com.example.mvsar.hawk_eyeantitheft;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.getIntent;

public class GPSservice extends Service {
    private SmsManager smsManager;
    private TextView textView;
    private GpsTool gpsTool;
    Short port=5444;
    WindowManager wm;
    LinearLayout l1;
    String number;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        number = intent.getStringExtra("sender");

        return START_STICKY;

    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();




        //

        smsManager = SmsManager.getDefault();

        /**/
        if (gpsTool == null) {
            gpsTool = new GpsTool(this) {
                @Override
                public void onGpsLocationChanged(Location location) {
                    super.onGpsLocationChanged(location);
                    //Toast.makeText(getApplicationContext(),"hello", Toast.LENGTH_LONG).show();
                    refreshLocation(location);

                }
            };
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gpsTool.stopGpsUpdate();


    }


   private void refreshLocation(Location location) {
        Double longitude = location.getLongitude();
        Double latitude = location.getLatitude();
        String lat= String.valueOf(latitude);
        String lon=String.valueOf(longitude);
        String msg="CORD "+lat+" "+lon;
        byte[]body=msg.getBytes();
        smsManager.sendDataMessage(number,null,port,body,null,null);
        this.stopSelf();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {



        return null;
    }
}
