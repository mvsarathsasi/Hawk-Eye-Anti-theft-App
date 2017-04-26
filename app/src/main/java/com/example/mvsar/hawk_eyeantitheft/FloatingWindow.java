package com.example.mvsar.hawk_eyeantitheft;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatingWindow extends Service {


    WindowManager wm;
    LinearLayout ll;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        
        return null;

    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        ll = new LinearLayout(this);
        TextView t1=new TextView(this);
        ViewGroup.LayoutParams textviewParameters = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        t1.setText("This phone is locked by Hawk Eye Anti-Theft");
        t1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        t1.setTextColor(Color.YELLOW);
        t1.setLayoutParams(textviewParameters);
        ll.setBackgroundColor(Color.RED);
        LinearLayout.LayoutParams layoutParameteres = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,400);
        //ll.setBackgroundColor(Color.argb(66,255,0,0));
        ll.setLayoutParams(layoutParameteres);
        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.OPAQUE);
        ll.addView(t1);
        wm.addView(ll, parameters);

}

    @Override
    public void onDestroy() {
        super.onDestroy();
        wm.removeView(ll);
        stopSelf();

    }

}
