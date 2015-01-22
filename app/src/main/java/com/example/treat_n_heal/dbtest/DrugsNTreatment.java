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


public class DrugsNTreatment extends ListActivity implements FetchDiseaseListener {
    private ProgressDialog dialog;
    EditText searchbox;
    Button searchbtn;
    Button exitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_ntreatment);

        searchbox = (EditText) findViewById(R.id.searchEditText);
        searchbtn = (Button) findViewById(R.id.searchDiseaseBtn);
        exitbtn = (Button) findViewById(R.id.exitButton);

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DrugsNTreatment.this,AppMenu.class);
                startActivity(in);
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String woundname = searchbox.getText().toString();
                if(woundname.contains(" ")) {
                    String check = woundname.substring(0, woundname.indexOf(" "));
                    searchWound(check);
                }
                else{
                    searchWound(woundname);
                }
            }
        });

        initView();
    }

    private void initView() {
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Loading...");

        String url = "http://tnh2014.5gbfree.com/appconnection/displayDiagnosis.php";
        FetchDiseases task = new FetchDiseases(this);
        task.execute(url);
    }

    private void searchWound(String wname){
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Searching disease...");
        String url = "http://tnh2014.5gbfree.com/appconnection/searchDisease.php?woundname="+wname;
        FetchDiseases task = new FetchDiseases(this);
        task.execute(url);
    }

    @Override
    public void onFetchComplete(List<Diseases> data) {
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

    public class ApplicationAdapter extends ArrayAdapter<Diseases> {


        //protected static final Context context = null;
        private List<Diseases> items;

        public ApplicationAdapter(Context context, List<Diseases> items) {
            super(context, R.layout.custom_disease_list, items);
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
                row = li.inflate(R.layout.custom_disease_list, null);
            }

            final Diseases app = items.get(position);

            if(app != null) {

                final TextView titleText = (TextView)row.findViewById(R.id.diseaseTxt);

                if(titleText != null) {
                    titleText.setText(app.getWname());
                    row.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            Intent in = new Intent(DrugsNTreatment.this,TreatmentInfo.class);
                            in.putExtra("diagnosisid", app.getDiagnosisid());
                            in.putExtra("doctorid", app.getDoctorid());
                            in.putExtra("treatmentid", app.getTreatmentid());
                            in.putExtra("woundname", app.getWname());
                            in.putExtra("woundimage", app.getWimage());
                            in.putExtra("trName", app.getTrname());
                            in.putExtra("trInfo", app.getTrinfo());
                            in.putExtra("trStage", app.getTrstage());
                            in.putExtra("trDrugs", app.getTrdrugs());
                            startActivity(in);
                        }
                    });

                    titleText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(DrugsNTreatment.this,TreatmentInfo.class);
                            in.putExtra("diagnosisid", app.getDiagnosisid());
                            in.putExtra("doctorid", app.getDoctorid());
                            in.putExtra("treatmentid", app.getTreatmentid());
                            in.putExtra("woundname", app.getWname());
                            in.putExtra("woundimage", app.getWimage());
                            in.putExtra("trName", app.getTrname());
                            in.putExtra("trInfo", app.getTrinfo());
                            in.putExtra("trStage", app.getTrstage());
                            in.putExtra("trDrugs", app.getTrdrugs());
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
        getMenuInflater().inflate(R.menu.menu_drugs_ntreatment, menu);
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
