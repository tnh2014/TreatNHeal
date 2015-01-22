package com.example.treat_n_heal.dbtest;

/**
 * Created by user on 12/01/2015.
 */
public class Admin {
    private int loginid;
    private int doctorid;
    private String username;
    private String password;

    public int getLoginid() {
        return loginid;
    }
    public void setLoginid(int loginid) {
        this.loginid = loginid;
    }
    public int getDoctorid() {
        return doctorid;
    }
    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password; }
}
