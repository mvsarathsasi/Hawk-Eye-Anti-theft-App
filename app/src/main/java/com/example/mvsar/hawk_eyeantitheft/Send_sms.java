package com.example.mvsar.hawk_eyeantitheft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Send_sms extends Activity {

    Button sendSMSBtn;
    EditText PhoneNumber;
    EditText Password;
    String to_do=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms_layout);
        sendSMSBtn = (Button) findViewById(R.id.button);
        PhoneNumber = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);
        to_do=getIntent().getStringExtra("task");
        sendSMSBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS();
            }
        });
    }

    protected void sendSMS() {
        String toPhoneNumber = PhoneNumber.getText().toString();
        String tar_pass=Password.getText().toString();
        String smsMessage = "MSG_LOCK"+tar_pass;
        byte[]lockmsg=smsMessage.getBytes();
        String smsUnlock ="MSG_UNLOCK"+tar_pass;
        String smsTrack="MSG_TRACK"+tar_pass;
        byte[]unlockmsg=smsUnlock.getBytes();
        byte[]trackmsg=smsTrack.getBytes();
        short port=5444;
        try {
            SmsManager smsManager = SmsManager.getDefault();
            if((toPhoneNumber.equals(""))||(toPhoneNumber.length()!=10))
            {
                Toast.makeText(getApplicationContext(), "Enter a valid phone number",
                        Toast.LENGTH_LONG).show();
            }
            else if (tar_pass.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Enter a valid password",
                        Toast.LENGTH_LONG).show();
            }
            else{
                if (to_do.equals("LOCK")) {
                    //smsManager.sendTextMessage("+91" + toPhoneNumber, null, smsMessage, null, null);
                    smsManager.sendDataMessage("+91" + toPhoneNumber, null,port, lockmsg, null, null);

                }
                else if(to_do.equals("UNLOCK"))
                {
                    //smsManager.sendTextMessage("+91" + toPhoneNumber, null, smsUnlock, null, null);
                    smsManager.sendDataMessage("+91" + toPhoneNumber, null,port, unlockmsg, null, null);
                }
                else if(to_do.equals("TRACK"))
                {
                    smsManager.sendDataMessage("+91" + toPhoneNumber, null,port, trackmsg, null, null);
                }


                Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                finish();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Sending SMS failed! Try again later.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

}