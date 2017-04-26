package com.example.mvsar.hawk_eyeantitheft;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences prefs=null;

    /////////////////
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    /////////////////

    String m_Text=null;
    Boolean dialog_box_on=false;
    Intent i1=null;
    Intent i2=null;
    Intent send=null;
    Intent perm_intent;
    Intent draw_intent;
    String [] PermissionSet=new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.ACCESS_FINE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        perm_intent=new Intent(this,App_Permissions.class);
        draw_intent=new Intent(this,Draw_permissions.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        i1=new Intent(this,Firstrun.class);
        prefs = getSharedPreferences("com.example.mvsar.antitheftapp", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            startActivityForResult(i1,100);
        }
        setContentView(R.layout.activity_main);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //////////////////////////////

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        ///////////////////////////////////////////////
        for(String Permission:PermissionSet)
        {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Permission)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this, PermissionSet, 2);


            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(getApplicationContext())) {

                startActivity(draw_intent);
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        {
            for(String requestedPermission:permissions)
            {
                    if ((ActivityCompat.checkSelfPermission(MainActivity.this, requestedPermission)!=PackageManager.PERMISSION_GRANTED))
                    {
                        startActivity(perm_intent);
                        break;
                    }
            }

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        i2=new Intent(this,Set_new_password.class);
        send=new Intent(this,Send_sms.class);
        if (id == R.id.change_pass) {

            startActivity(i2);


        } /*else if (id == R.id.lock) {
            send.putExtra("task", "LOCK");//CHANGED VALUE
            startActivity(send);

        } else if (id == R.id.unlock) {
            send.putExtra("task", "UNLOCK");
            startActivity(send);

        }/* else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            if(requestCode==100){
                String str=prefs.getString("password",null);
                //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }

        }
        else
            finish();

    }

    //////////////////////////
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                {
                    Section1act s1=new Section1act();
                    return  s1;
                }
                case 1:
                {
                    Section2act s2=new Section2act();
                    return  s2;
                }
                default:
                    return null;

            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "OPTIONS";
               /* case 2:
                    return "SECTION 3";*/
            }
            return null;
        }

    }
    ////////////////////////////

}




















/**
 * A placeholder fragment containing a simple view.

 /**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
