package com.example.mvsar.hawk_eyeantitheft;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;



public class Section1act extends Fragment


    {
        Fragment fragment;
        FragmentManager fm;
        SharedPreferences status_pref_obj;
        ProgressBar progressBar;
        ObjectAnimator animation;
        View rootView;
        ImageView iv;
        Boolean enabled;
        String control;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.section1, container, false);
            progressBar=(ProgressBar)rootView.findViewById(R.id.progressBar);
            iv=(ImageView)rootView.findViewById(R.id.logo_status);
            status_pref_obj = getActivity().getSharedPreferences("status preference", Context.MODE_PRIVATE);
            control=status_pref_obj.getString("app_key",null);
             if(control.equals("true"))
            {
                iv.setImageResource(R.drawable.red);
                fragment=new Status_dis_fragment();
                fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment,fragment);
                ft.commit();
                enabled=false;

            }

            else
            {
                iv.setImageResource(R.drawable.green);
                fragment=new Status_en_fragment();
                fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment,fragment);
                ft.commit();
                enabled=true;
            }

           iv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    animation=ObjectAnimator.ofInt(progressBar,"progress",0,100);
                    progressBar.setProgress(0);
                    animation.setDuration(400);
                    animation.setInterpolator(new DecelerateInterpolator());
                    animation.start();
                    fm=getFragmentManager();
                    //control_switch=status_pref_obj.getBoolean("app_status_key1",true);
                    status_pref_obj = getActivity().getSharedPreferences("status preference", Context.MODE_PRIVATE);
                   control=status_pref_obj.getString("app_key",null);
                   if(control.equals("true"))
                    {
                        SharedPreferences.Editor checkbox=status_pref_obj.edit();
                        checkbox.putString("app_key","false");
                        checkbox.commit();
                    }
                    else //if(control.equals("false"))
                    // if(!control_switch)
                    {

                        SharedPreferences.Editor checkbox=status_pref_obj.edit();
                        checkbox.putString("app_key","true");
                        checkbox.commit();
                    }
                    if(enabled)
                    {
                        iv.setImageResource(R.drawable.red);
                        fragment=new Status_dis_fragment();
                        fm=getFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.fragment,fragment);
                        ft.commit();
                        enabled=false;
                    }
                    else
                    {
                        iv.setImageResource(R.drawable.green);
                        fragment=new Status_en_fragment();
                        fm=getFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();
                        ft.replace(R.id.fragment,fragment);
                        ft.commit();
                        enabled=true;
                    }

                    control=status_pref_obj.getString("app_key",null);
                    return false;
                }
            });














            /*Button enable=(Button)rootView.findViewById(R.id.enid);
            Button disable=(Button)rootView.findViewById(R.id.disid);
            progressBar=(ProgressBar)rootView.findViewById(R.id.progressBar);
            enable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment=new Status_en_fragment();



                    android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.fragment,fragment);
                    ft.commit();

                }
            });
            disable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment=new Status_dis_fragment();
                    FragmentManager fm=getFragmentManager();
                    android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.fragment,fragment);
                    ft.commit();
                }
            });*/
            return rootView;
        }



    }
