package com.example.treat_n_heal.dbtest;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ExistingPatient extends ListActivity implements FetchPatientListener {
    private ProgressDialog dialog;
    EditText searchbox;
    Button searchbtn;
    Button exitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_patient);
        searchbox = (EditText) findViewById(R.id.searchPatient);
        searchbtn = (Button) findViewById(R.id.searchPatientBtn);
        exitbtn = (Button) findViewById(R.id.exitPatientBtn);

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ExistingPatient.this,PatientMenu.class);
                startActivity(in);
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = searchbox.getText().toString();
                if(firstname.contains(" ")) {
                    String check = firstname.substring(0, firstname.indexOf(" "));
                    searchPatient(check);
                }
                else{
                    searchPatient(firstname);
                }
            }
        });

        initView();
    }

    private void initView() {
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Loading...");

        String url = "http://tnh2014.5gbfree.com/appconnection/displayPatient.php";
        FetchPatients task = new FetchPatients(this);
        task.execute(url);
    }

    private void searchPatient(String fname){
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Searching patient...");
        String url = "http://tnh2014.5gbfree.com/appconnection/searchPatient.php?firstname="+fname;
        FetchPatients task = new FetchPatients(this);
        task.execute(url);
    }

    @Override
    public void onFetchComplete(List<Patient> data) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // create new adapter
        ApplicationAdapter adapter = new ApplicationAdapter(this, data);
        // set the adapter to list
        setListAdapter(adapter);
    }

    @Override
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // show failure message
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public class ApplicationAdapter extends ArrayAdapter<Patient> {


        //protected static final Context context = null;
        private List<Patient> items;

        public ApplicationAdapter(Context context, List<Patient> items) {
            super(context, R.layout.custom_patient_list, items);
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if(row == null) {
                LayoutInflater li = LayoutInflater.from(getContext());
                row = li.inflate(R.layout.custom_patient_list, null);
            }

            final Patient app = items.get(position);

            if(app != null) {

                final TextView titleText = (TextView)row.findViewById(R.id.patientTxt);


                if(titleText != null) {
                    titleText.setText(app.getFirstName()+" "+app.getLastName());

                    row.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            Intent in = new Intent(ExistingPatient.this,PatientInfo.class);
                            in.putExtra("patientid", app.getID());
                            in.putExtra("doctorid", app.getDID());
                            in.putExtra("firstname", app.getFirstName());
                            in.putExtra("lastname", app.getLastName());
                            in.putExtra("address", app.getAddress());
                            in.putExtra("email", app.getEmail());
                            in.putExtra("nic", app.getNic());
                            in.putExtra("phonenum", app.getPhonenum());
                            in.putExtra("mobilenum", app.getMobnum());
                            in.putExtra("allergies", app.getAllergies());
                            startActivity(in);
                        }
                    });

                    titleText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(ExistingPatient.this,PatientInfo.class);
                            in.putExtra("patientid", app.getID());
                            in.putExtra("doctorid", app.getDID());
                            in.putExtra("firstname", app.getFirstName());
                            in.putExtra("lastname", app.getLastName());
                            in.putExtra("address", app.getAddress());
                            in.putExtra("email", app.getEmail());
                            in.putExtra("nic", app.getNic());
                            in.putExtra("phonenum", app.getPhonenum());
                            in.putExtra("mobilenum", app.getMobnum());
                            in.putExtra("allergies", app.getAllergies());
                            startActivity(in);
                        }
                    });
                }
            }
            return row;
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_existing_patient, menu);
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
