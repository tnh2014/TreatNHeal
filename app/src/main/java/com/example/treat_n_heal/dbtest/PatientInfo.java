package com.example.treat_n_heal.dbtest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class PatientInfo extends ActionBarActivity {
    EditText fname;
    EditText lname;
    EditText addr;
    EditText eml;
    EditText nc;
    EditText pnum;
    EditText mnum;
    EditText allgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        fname = (EditText) findViewById(R.id.pFname);
        lname = (EditText) findViewById(R.id.pLname);
        addr = (EditText) findViewById(R.id.pAddress);
        eml = (EditText) findViewById(R.id.pEmail);
        nc = (EditText) findViewById(R.id.pNic);
        pnum = (EditText) findViewById(R.id.pPnum);
        mnum = (EditText) findViewById(R.id.pMnum);
        allgs = (EditText) findViewById(R.id.pAllergies);

        Intent in = getIntent();
        String firstname = in.getStringExtra("firstname");
        String lastname = in.getStringExtra("lastname");
        String address = in.getStringExtra("address");
        String email = in.getStringExtra("email");
        String nic = in.getStringExtra("nic");
        int phonenum = in.getIntExtra("phonenum",1);
        int mobilenum = in.getIntExtra("mobilenum",1);
        String allergies = in.getStringExtra("allergies");

        fname.setText("Firstname: "+firstname);
        lname.setText("Lastname: "+lastname);
        addr.setText("Address: "+address);
        eml.setText("Email: "+email);
        nc.setText("Nic: "+nic);
        pnum.setText("Phone no: "+String.valueOf(phonenum));
        mnum.setText("Mob no: "+String.valueOf(mobilenum));
        allgs.setText("Allergies: "+allergies);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient_info, menu);
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
