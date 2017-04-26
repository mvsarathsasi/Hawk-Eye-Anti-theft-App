package com.example.mvsar.hawk_eyeantitheft;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class App_Permissions extends Activity {
    Intent intent;
    Boolean got_permission=true;
    String [] PermissionSet=new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.ACCESS_FINE_LOCATION};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_permissions_layout);
        Button b1=(Button)findViewById(R.id.app_perm_enable);
        intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,0);

            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==0)
        {

                for(String Permission:PermissionSet)
                {
                    if (ActivityCompat.checkSelfPermission(App_Permissions.this, Permission)!= PackageManager.PERMISSION_GRANTED)
                    {
                       got_permission=false;
                       break;
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


