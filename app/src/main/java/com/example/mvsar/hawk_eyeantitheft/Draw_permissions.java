package com.example.mvsar.hawk_eyeantitheft;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Draw_permissions extends AppCompatActivity {
    Intent intent;
    Boolean got_permission=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_permissions_layout);
        Button b1=(Button)findViewById(R.id.draw_perm_enable);
        intent = new Intent();
        intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,1);

            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==1)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if(!Settings.canDrawOverlays(getApplicationContext()))
                    {
                        got_permission=false;
                    }
                    else
                        got_permission=true;
                }
                if(got_permission)
                {
                    setResult(RESULT_OK);
                    finish();
                }
            }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent exit_intent=new Intent(Intent.ACTION_MAIN);
        exit_intent.addCategory(Intent.CATEGORY_HOME);
        exit_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit_intent);
        System.exit(0);
    }
       /* @Override
    public void onResume(){
            super.onResume();
            for(String Permission:PermissionSet)
            {
                if (ActivityCompat.checkSelfPermission(App_Permissions.this, Permission)!= PackageManager.PERMISSION_GRANTED)

                {
                    Toast.makeText(getApplicationContext(),"one",Toast.LENGTH_LONG).show();
                    finish();}
            }


        }*/
}
