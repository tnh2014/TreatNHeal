package com.example.treat_n_heal.dbtest;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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


public class doctorsList extends ListActivity implements FetchDataListener {
    private ProgressDialog dialog;
    EditText searchBox;
    Button searchbtn;
    Button exitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        searchBox = (EditText) findViewById(R.id.searchDoctor);
        searchbtn = (Button) findViewById(R.id.searchButton);
        exitbtn = (Button) findViewById(R.id.ExitButton);

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(doctorsList.this,AppMenu.class);
                startActivity(in);
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = searchBox.getText().toString();
                if(firstname.contains(" ")) {
                    String check = firstname.substring(0, firstname.indexOf(" "));
                    searchDoc(check);
                }
                else{
                    searchDoc(firstname);
                }
            }
        });

        initView();
    }

    private void initView() {
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Loading...");

        String url = "http://tnh2014.5gbfree.com/appconnection/displayDoctor.php";
        FetchDoctors task = new FetchDoctors(this);
        task.execute(url);
    }

    private void searchDoc(String fname){
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Searching doctor...");
        String url = "http://tnh2014.5gbfree.com/appconnection/searchDoctor.php?firstname="+fname;
        FetchDoctors task = new FetchDoctors(this);
        task.execute(url);
    }

    @Override
    public void onFetchComplete(List<Doctors> data) {
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

    public class ApplicationAdapter extends ArrayAdapter<Doctors> {


        //protected static final Context context = null;
        private List<Doctors> items;

        public ApplicationAdapter(Context context, List<Doctors> items) {
            super(context, R.layout.custom_list, items);
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
                row = li.inflate(R.layout.custom_list, null);
            }

            final Doctors app = items.get(position);

            if(app != null) {

                final TextView titleText = (TextView)row.findViewById(R.id.titleTxt);

                if(titleText != null) {
                    titleText.setText(app.getFname()+" "+app.getLname());
                    row.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            Intent in = new Intent(doctorsList.this,DoctorInfo.class);
                            in.putExtra("doctorid", app.getDoctorid());
                            in.putExtra("firstname", app.getFname());
                            in.putExtra("lastname", app.getLname());
                            in.putExtra("address", app.getAddress());
                            in.putExtra("email", app.getEmail());
                            in.putExtra("phonenum", app.getPhonenum());
                            in.putExtra("mobilenum", app.getMobnum());
                            in.putExtra("yearsOfService", app.getYears());
                            startActivity(in);
                        }
                    });

                    titleText.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(doctorsList.this,DoctorInfo.class);
                            in.putExtra("doctorid", app.getDoctorid());
                            in.putExtra("firstname", app.getFname());
                            in.putExtra("lastname", app.getLname());
                            in.putExtra("address", app.getAddress());
                            in.putExtra("email", app.getEmail());
                            in.putExtra("phonenum", app.getPhonenum());
                            in.putExtra("mobilenum", app.getMobnum());
                            in.putExtra("yearsOfService", app.getYears());
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
        getMenuInflater().inflate(R.menu.menu_doctors_list, menu);
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
