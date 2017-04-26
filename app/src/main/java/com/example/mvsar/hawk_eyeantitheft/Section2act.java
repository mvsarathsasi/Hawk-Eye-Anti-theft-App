package com.example.mvsar.hawk_eyeantitheft;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mvsar on 16-Apr-17.
 */

public class Section2act extends Fragment


{
   // Button b1;
   Intent i3;
   Short port=5444;
   private SmsManager smsManager;
   CircleMenu circleMenu;
    Intent send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.section2, container, false);
        circleMenu = (CircleMenu) rootView.findViewById(R.id.circle_menu);
        i3=new Intent(getActivity(),MapsActivity.class);
        smsManager=SmsManager.getDefault();
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.icon_gps)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.ic_lock_open_black_24dp)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.ic_share_black_24dp)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.ic_lock_black_24dp);
        send=new Intent(getActivity(),Send_sms.class);



        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                            send.putExtra("task","TRACK");
                                                             new Timer().schedule(new TimerTask() {
                                                                 @Override
                                                                 public void run() {
                                                                     startActivity(send);
                                                                 }
                                                             }, 1000);


                                                             break;
                                                         case 1:
                                                             send.putExtra("task", "UNLOCK");
                                                             new Timer().schedule(new TimerTask() {
                                                                 @Override
                                                                 public void run() {
                                                                     startActivity(send);
                                                                 }
                                                             }, 1000);
                                                              break;
                                                         case 2:
                                                             ApplicationInfo app = getActivity().getApplicationInfo();
                                                             String filePath = app.sourceDir;
                                                             Intent intent = new Intent(Intent.ACTION_SEND);
                                                             intent.setType("*/*");
                                                             intent.setPackage("com.android.bluetooth");
                                                             intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                                                             startActivity(Intent.createChooser(intent, "Share app"));
                                                             circleMenu.closeMenu();
                                                             circleMenu.closeMenu();
                                                             break;
                                                         case 3:
                                                              send.putExtra("task", "LOCK");
                                                             new Timer().schedule(new TimerTask() {
                                                                 @Override
                                                                 public void run() {
                                                                     startActivity(send);
                                                                 }
                                                             }, 1000);

                                                             circleMenu.closeMenu();
                                                             break;

                                                     }
                                                 }
                                             }

        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                                                     @Override
                                                     public void onMenuOpened() {

                                                     }

                                                     @Override
                                                     public void onMenuClosed() {

                                                     }
                                                 }
        );
        return rootView;

    }













        /* b1=(Button)rootView.findViewById(R.id.intentbutton);
        i3=new Intent(getActivity(),MapsActivity.class);
        smsManager=SmsManager.getDefault();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(i3);
                String message="MSG_TRACK1234";
                byte[]body=message.getBytes();
                smsManager.sendDataMessage("7907309763",null,port,body,null,null);

            }
        });*/




}
