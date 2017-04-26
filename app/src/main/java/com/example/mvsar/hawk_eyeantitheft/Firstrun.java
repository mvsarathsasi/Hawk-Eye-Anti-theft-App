package com.example.mvsar.hawk_eyeantitheft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mvsar on 22-Nov-16.
 */
public class Firstrun extends AppCompatActivity {
    SharedPreferences password_pref = null,status_pref_obj;
    String input1 = null;
    String input2 = null;
    EditText e1 = null;
    EditText e2 = null;
    Button b1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_first_pass);
        password_pref = getSharedPreferences("com.example.mvsar.antitheftapp", MODE_PRIVATE);
        status_pref_obj = getSharedPreferences("status preference", Context.MODE_PRIVATE);
        status_pref_obj.edit().putString("app_key","false").commit();
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input1 = e1.getText().toString();
                input2 = e2.getText().toString();
                if (input1.equals(input2) && (!input1.equals("")) && (!input2.equals(""))) {
                    password_pref.edit().putString("password", input1).commit();
                    password_pref.edit().putBoolean("firstrun", false).commit();
                    Toast.makeText(getApplicationContext(), "Password set", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();


                } else
                    Toast.makeText(getApplicationContext(), "Type a valid password", Toast.LENGTH_SHORT).show();
            }
        });



        //finish();*/
    }
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(0, returnIntent);
        finish();
    }
}


