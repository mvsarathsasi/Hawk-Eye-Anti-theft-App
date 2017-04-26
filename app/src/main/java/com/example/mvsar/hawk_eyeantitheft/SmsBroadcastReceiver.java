package com.example.mvsar.hawk_eyeantitheft;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;



public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = SmsBroadcastReceiver.class.getSimpleName();

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    SharedPreferences pass2=null,status_pref_obj;
    SharedPreferences trigger_obj;
    String control;
    String stored_pass=null;
    GpsTool gpstool;
    String senderpno;
    Intent i1,i2;
    Boolean bootlock;

    public void onReceive(Context context, Intent intent) {

        i1 = new Intent(context, FloatingWindow.class);
        i2=new Intent(context,GPSservice.class);
        Log.e(TAG, "On Receive");
        pass2=context.getSharedPreferences("com.example.mvsar.antitheftapp",Context.MODE_PRIVATE);
        status_pref_obj = context.getSharedPreferences("status preference", Context.MODE_PRIVATE);
        trigger_obj=context.getSharedPreferences("trigger_pref",Context.MODE_PRIVATE);
        bootlock=trigger_obj.getBoolean("startup_trigger",false);

            {
                context.startService(i1);
            }

        control=status_pref_obj.getString("app_key",null);
        final Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        String str = "";

        try {

           /* Log.e(TAG, "Bundle: " + bundle);

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();*/



/////////////////////////////////////////////////added code///////////////////////////////////////////

            if (bundle != null){
                // Retrieve the Binary SMS data
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];

                // For every SMS message received (although multipart is not supported with binary)
                for (int i=0; i<msgs.length; i++) {
                    byte[] data = null;

                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                   senderpno=msgs[i].getOriginatingAddress();

                   // str += "\nBINARY MESSAGE: ";

                    // Return the User Data section minus the
                    // User Data Header (UDH) (if there is any UDH at all)
                    data = msgs[i].getUserData();

                    // Generally you can do away with this for loop
                    // You'll just need the next for loop
                    /*for (int index=0; index < data.length; index++) {
                        str += Byte.toString(data[index]);
                    }*/

                   // str += "\nTEXT MESSAGE (FROM BINARY): ";

                    for (int index=0; index < data.length; index++) {
                        str += Character.toString((char) data[index]);
                    }

                    if(control.equals("true"))
                    {
                        str="";
                    }
                }

                // Dump the entire message
                // Toast.makeText(context, str, Toast.LENGTH_LONG).show();
                Log.d(TAG, str);
            }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////


































                   // Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                    stored_pass=pass2.getString("password",null);

                    // Show Alert
                    int duration = Toast.LENGTH_LONG;
                    if(str.contains("MSG_LOCK"+stored_pass))
                    {
                        //service_check.edit().putBoolean("service_state",true).commit();
                        SharedPreferences.Editor checkbox=trigger_obj.edit();
                        checkbox.putBoolean("startup_trigger",true);
                        checkbox.commit();
                        Toast.makeText(context,bootlock.toString(),Toast.LENGTH_LONG).show();
                        context.startService(i1);


                    }
                    if (str.contains("MSG_UNLOCK"+stored_pass))
                    {
                        /*if(service_check.getBoolean("service_state",false))*/
                        SharedPreferences.Editor checkbox=trigger_obj.edit();
                        checkbox.putBoolean("startup_trigger",false);
                        checkbox.commit();
                        context.stopService(i1);
                        //service_check.edit().putBoolean("service_state", false).commit();

                    }
            if (str.contains("MSG_TRACK"+stored_pass))
            {
                        /*if(service_check.getBoolean("service_state",false))*/

                //Toast.makeText(context,senderpno,Toast.LENGTH_LONG).show();
                i2.putExtra("sender",senderpno);
                context.startService(i2);

                //service_check.edit().putBoolean("service_state", false).commit();

            }
            if (str.contains("CORD"))
            {
                String[] split=new String[3];
                split=str.split(" ");
                String longitude=split[1];
                String latitude=split[2];
                Intent i3=new Intent(context,MapsActivity.class);
                i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i3.putExtra("longitude",longitude);
                i3.putExtra("latitude",latitude);
                context.startActivity(i3);



            }

                    /*if (str.contains("MSG_TRACK"+stored_pass))
                    {
                        if(gpstool==null)
                        {
                            gpstool=new GpsTool(this)
                            {

                            }
                        }



                    }*/

                    /*NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setSmallIcon(R.drawable.default_profile)
                                    .setContentTitle("New Message")
                                    .setContentText(message);*/

// Sets an ID for the notification
                    /*int mNotificationId = 001;
// Gets an instance of the NotificationManager service
                    NotificationManager mNotifyMgr =
                            (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
                    mNotifyMgr.notify(mNotificationId, mBuilder.build());*/


                 // end for loop
             // bundle is null

        } catch (Exception e) {
            //Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
}