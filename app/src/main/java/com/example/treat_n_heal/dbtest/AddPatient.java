package com.example.treat_n_heal.dbtest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class AddPatient extends ActionBarActivity {

    EditText fnamebox;
    EditText lnamebox;
    EditText addressbox;
    EditText emailbox;
    EditText nicbox;
    EditText pnumbox;
    EditText mnumbox;
    EditText allergiesbox;
    Button savepatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        fnamebox = (EditText) findViewById(R.id.fname);
        lnamebox = (EditText) findViewById(R.id.lname);
        addressbox = (EditText) findViewById(R.id.address);
        emailbox = (EditText) findViewById(R.id.email);
        nicbox = (EditText) findViewById(R.id.nic);
        pnumbox = (EditText) findViewById(R.id.pnum);
        mnumbox = (EditText) findViewById(R.id.mnum);
        allergiesbox = (EditText) findViewById(R.id.allergies);
        savepatient = (Button) findViewById(R.id.addpatient);

        savepatient.setOnClickListener(new View.OnClickListener() {

            InputStream is = null;
            @Override
            public void onClick(View v) {
                int doctorid = 1;
                String firstname = fnamebox.getText().toString();
                String lastname = lnamebox.getText().toString();
                String address = addressbox.getText().toString();
                String email = emailbox.getText().toString();
                String nic = nicbox.getText().toString();
                String phonenum = pnumbox.getText().toString();
                String mobilenum = mnumbox.getText().toString();
                String allergies = allergiesbox.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("doctorid",Integer.toString(doctorid)));
                nameValuePairs.add(new BasicNameValuePair("firstname",firstname));
                nameValuePairs.add(new BasicNameValuePair("lastname",lastname));
                nameValuePairs.add(new BasicNameValuePair("address",address));
                nameValuePairs.add(new BasicNameValuePair("email",email));
                nameValuePairs.add(new BasicNameValuePair("nic",nic));
                nameValuePairs.add(new BasicNameValuePair("phonenum",phonenum));
                nameValuePairs.add(new BasicNameValuePair("mobilenum",mobilenum));
                nameValuePairs.add(new BasicNameValuePair("allergies",allergies));

                try{
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://tnh2014.5gbfree.com/appconnection/insertPatient.php");

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    String msg = "Patient info entered successfully";

                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                    Intent in = new Intent(AddPatient.this,PatientMenu.class); // Should go to scanImage screen
                    startActivity(in);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_patient, menu);
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
