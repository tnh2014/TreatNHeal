package com.example.treat_n_heal.dbtest;

/**
 * Created by user on 18/01/2015.
 */
import java.util.List;

public interface FetchPatientListener {
    public void onFetchComplete(List<Patient> data);
    public void onFetchFailure(String msg);
}
