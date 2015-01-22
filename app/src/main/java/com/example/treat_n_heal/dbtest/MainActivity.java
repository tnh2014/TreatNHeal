package com.example.treat_n_heal.dbtest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    EditText usernameBox;
    EditText passBox;
    Button loginButton;
    // TextView status;
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
            setContentView(R.layout.activity_main);

            usernameBox = (EditText) findViewById(R.id.username);
            passBox = (EditText) findViewById(R.id.password);
            loginButton = (Button) findViewById(R.id.login);
            // Added a TextView below to test value send back to mobile
            // status=(TextView) findViewById(R.id.status);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = ProgressDialog.show(MainActivity.this, "",
                            "Validating user...", true);
                    new Thread(new Runnable() {
                        public void run() {
                            login();
                        }
                    }).start();
                }
            });

    }

    void login(){
        try{

            httpclient = new DefaultHttpClient();
            httppost= new HttpPost("http://tnh2014.5gbfree.com/appconnection/login.php"); // make sure the url is correct.

            //Entering your data
            nameValuePairs = new ArrayList<NameValuePair>(1);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            nameValuePairs.add(new BasicNameValuePair("username",usernameBox.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("password",passBox.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            //Execute HTTP Post Request
            response = httpclient.execute(httppost);
            // execute query to get value
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                    // Value that will be send back to mobile
                    // status.setText("Response from PHP : " + response);
                    dialog.dismiss();
                }
            });

            // Login process
            String check=null;

            // Since it is hosted on a free hosting server, it has always a watermark text attached,
            // so has to split to get real value and remove watermark
            if(response.contains("<")) {
                check = response.substring(0, response.indexOf("<"));

                // Use Value above to compare for Login
                // If login successful
                if (check.equalsIgnoreCase("User Found")) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent in = new Intent(MainActivity.this,AppMenu.class);
                    startActivity(in);

                } else {
                    // If login is unsuccessful
                    showAlert();
                }

            }

        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    // If login is unsuccessful
    public void showAlert(){
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Login Error.");
                builder.setMessage("User not Found.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
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
