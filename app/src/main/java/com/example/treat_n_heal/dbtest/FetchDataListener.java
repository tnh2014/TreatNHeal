package com.example.treat_n_heal.dbtest;

/**
 * Created by Vimal on 1/4/2015.
 */
import java.util.List;

public interface FetchDataListener {
    public void onFetchComplete(List<Doctors> data);
    public void onFetchFailure(String msg);
}
