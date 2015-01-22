package com.example.treat_n_heal.dbtest;

/**
 * Created by user on 17/01/2015.
 */

import java.util.List;

public interface FetchDiseaseListener {
    public void onFetchComplete(List<Diseases> data);
    public void onFetchFailure(String msg);
}
