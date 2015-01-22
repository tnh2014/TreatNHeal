package com.example.treat_n_heal.dbtest;

/**
 * Created by Vimal on 1/4/2015.
 */
public class Doctors {
    private int doctorid;
    private String fname;
    private String lname;
    private String address;
    private String email;
    private int pnum;
    private int mnum;
    private int yos;

    public int getDoctorid() {
        return doctorid;
    }
    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) { this.fname = fname; }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String addr) { this.address = addr; }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getPhonenum() {
        return pnum;
    }
    public void setPhonenum(int pnum) {
        this.pnum= pnum;
    }
    public int getMobnum() {
        return mnum;
    }
    public void setMobnum(int mnum) {
        this.mnum = mnum;
    }
    public int getYears() {
        return yos;
    }
    public void setYears(int yos) {
        this.yos = yos;
    }
}
