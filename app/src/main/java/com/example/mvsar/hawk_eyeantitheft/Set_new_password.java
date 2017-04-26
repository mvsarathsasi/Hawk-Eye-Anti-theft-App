package com.example.mvsar.hawk_eyeantitheft;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mvsar on 23-Nov-16.
 */
public class Set_new_password extends AppCompatActivity {
    SharedPreferences password_pref = null;
    String input1 = null;
    String input2 = null;
    String input3 = null;
    String Cur_pass=null;
    EditText e1 = null;
    EditText e2 = null;
    EditText e3 = null;
    Button b1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pass);
        password_pref = getSharedPreferences("com.example.mvsar.antitheftapp", MODE_PRIVATE);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.button);
        //getSupportActionBar().setTitle("Set new password");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input1 = e1.getText().toString();
                input2 = e2.getText().toString();
                input3 = e3.getText().toString();
                Cur_pass=password_pref.getString("password", null);
                if(!input1.equals(Cur_pass))
                {
                    Toast.makeText(getApplicationContext(), "Incorrect current password!", Toast.LENGTH_SHORT).show();
                }
                else if (input2.equals(input3) && (!input2.equals("")) && (!input3.equals(""))) {
                    password_pref.edit().putString("password", input2).commit();
                    Toast.makeText(getApplicationContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "Type a valid password", Toast.LENGTH_SHORT).show();
            }
        });



        //finish();*/
    }
    /*@Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(0, returnIntent);
        finish();
    }*/
}
