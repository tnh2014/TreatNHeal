package com.example.treat_n_heal.dbtest;

/**
 * Created by user on 17/01/2015.
 */
public class Diseases {
    private int diagnosisid;
    private int doctorid;
    private int treatmentid;
    private String wname;
    private String wimage;
    private String trname;
    private String trinfo;
    private int trstage;
    private String trdrugs;

    public int getDiagnosisid() {
        return diagnosisid;
    }
    public void setDiagnosisid(int did) {
        this.diagnosisid = did;
    }
    public int getDoctorid() {
        return doctorid;
    }
    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }
    public int getTreatmentid() {
        return treatmentid;
    }
    public void setTreatmentid(int tid) {
        this.treatmentid = tid;
    }
    public String getWname() {
        return wname;
    }
    public void setWname(String wname) { this.wname = wname; }
    public String getWimage() {
        return wimage;
    }
    public void setWimage(String wimage) { this.wimage = wimage; }
    public String getTrname() {
        return trname;
    }
    public void setTrname(String trname) { this.trname = trname; }
    public String getTrinfo() {
        return trinfo;
    }
    public void setTrinfo(String trinfo) { this.trinfo = trinfo; }
    public int getTrstage() {
        return trstage;
    }
    public void setTrstage(int trstage) { this.trstage = trstage; }
    public String getTrdrugs() {
        return trdrugs;
    }
    public void setTrdrugs(String trdrugs) { this.trdrugs = trdrugs; }
}
