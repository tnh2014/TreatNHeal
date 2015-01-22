package com.example.treat_n_heal.dbtest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class AppMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_menu);

        final ListView lv = (ListView) findViewById(R.id.menuListView);

        String[] values = new String[] { "Consult Patient","Drugs & Treatment",
                "Track A Doctor","Track A Pharmacy","Appointments","Exit The Application" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                if(position == 0) {
                    Intent in = new Intent(AppMenu.this, PatientMenu.class);
                    startActivity(in);

                }else if(position==1){

                    Intent in = new Intent(AppMenu.this, DrugsNTreatment.class);
                    startActivity(in);


                }else if(position==2){

                    Intent in = new Intent(AppMenu.this, doctorsList.class);
                    startActivity(in);

                }else if(position==3){

                    Intent in = new Intent(AppMenu.this, ListPharmacy.class);
                    startActivity(in);

                }else if(position==4){

                    Intent in = new Intent(AppMenu.this, AppointmentList.class);
                    startActivity(in);

                }else {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_menu, menu);
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
