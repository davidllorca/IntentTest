package com.davidllorca.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Visit web
        Button webBtn = (Button) findViewById(R.id.webBtn);
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWeb();
            }
        });
        // Send SMS
        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSmsByURI();
                //launchSmsByType();
            }
        });
        // Call phone
        Button callBtn = (Button) findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCall();
            }
        });

    }

    private void launchWeb() {
        EditText url = (EditText) findViewById(R.id.url);
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url.getText().toString()));
        startActivity(i);
    }

    private void launchCall() {
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        Intent i = new Intent();
        i.setAction(Intent.ACTION_CALL);
        // URI type : tel:phone_number
        i.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
        startActivity(i);
    }

    // Setting URI
    private void launchSmsByURI() {
        EditText url = (EditText) findViewById(R.id.url);
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        // URI type -> sms:phone_number
        i.setData(Uri.parse("sms:" + phoneNumber.getText().toString()));
        i.putExtra("sms_body", url.getText().toString());
        startActivity(i);
    }

    // Setting type
    private void launchSmsByType(){
        EditText url = (EditText) findViewById(R.id.url);
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.putExtra("adress", phoneNumber.getText().toString());
        i.setType("vnd.android-dir/mms-sms");
        i.putExtra("sms_body", url.getText().toString());
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
