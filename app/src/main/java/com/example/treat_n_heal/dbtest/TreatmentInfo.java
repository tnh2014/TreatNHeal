package com.example.treat_n_heal.dbtest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class TreatmentInfo extends ActionBarActivity {
    EditText wname;
    EditText tname;
    EditText tinfo;
    EditText tstage;
    EditText tdrugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_info);

        wname = (EditText) findViewById(R.id.wname);
        tname = (EditText) findViewById(R.id.tname);
        tinfo = (EditText) findViewById(R.id.tinfo);
        tstage = (EditText) findViewById(R.id.tstage);
        tdrugs = (EditText) findViewById(R.id.tdrugs);

        Intent in = getIntent();
        String woundname = in.getStringExtra("woundname");
        String trName = in.getStringExtra("trName");
        String trInfo = in.getStringExtra("trInfo");
        int trStage = in.getIntExtra("trStage",1);
        String trDrugs = in.getStringExtra("trDrugs");

        wname.setText("Disease name: "+woundname);
        tname.setText("Treatment given: "+trName);
        tinfo.setText("Treatment Info: "+trInfo);
        tstage.setText("Disease Stage: "+trStage);
        tdrugs.setText("Drugs prescribed: "+String.valueOf(trDrugs));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_treatment_info, menu);
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
