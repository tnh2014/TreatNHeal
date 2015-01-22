package com.example.treat_n_heal.dbtest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class DoctorInfo extends ActionBarActivity {

    EditText fname;
    EditText lname;
    EditText addr;
    EditText eml;
    EditText pnum;
    EditText mnum;
    EditText yos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        fname = (EditText) findViewById(R.id.docFname);
        lname = (EditText) findViewById(R.id.docLname);
        addr = (EditText) findViewById(R.id.docAddress);
        eml = (EditText) findViewById(R.id.docEmail);
        pnum = (EditText) findViewById(R.id.docPnum);
        mnum = (EditText) findViewById(R.id.docMnum);
        yos = (EditText) findViewById(R.id.docYos);

        Intent in = getIntent();
        String firstname = in.getStringExtra("firstname");
        String lastname = in.getStringExtra("lastname");
        String address = in.getStringExtra("address");
        String email = in.getStringExtra("email");
        int phonenum = in.getIntExtra("phonenum",1);
        int mobilenum = in.getIntExtra("mobilenum",1);
        int yearsOfService = in.getIntExtra("yearsOfService",1);

        fname.setText("Firstname: "+firstname);
        lname.setText("Lastname: "+lastname);
        addr.setText("Address: "+address);
        eml.setText("Email: "+email);
        pnum.setText("Phone no: "+String.valueOf(phonenum));
        mnum.setText("Mob no: "+String.valueOf(mobilenum));
        yos.setText("Years of service: "+String.valueOf(yearsOfService));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor_info, menu);
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
