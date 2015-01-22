package com.example.treat_n_heal.dbtest;

/**
 * Created by Vimal on 1/9/2015.
 */
public class Patient {
    private int _id;
    private int _doctorid;
    private String _firstname;
    private String _lastname;
    private String _address;
    private String _email;
    private String _nic;
    private int _phonenum;
    private int _mobnum;
    private String _allergies;


    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setDID(int did) {
        this._doctorid = did;
    }

    public int getDID() {
        return this._doctorid;
    }

    public void setPhonenum(int phonenum) {
        this._phonenum = phonenum;
    }

    public int getPhonenum() {
        return this._phonenum;
    }

    public void setMobnum(int mobnum) {
        this._mobnum = mobnum;
    }

    public int getMobnum() {
        return this._mobnum;
    }

    public void setFirstName(String firstname) {
        this._firstname = firstname;
    }

    public String getFirstName() {
        return this._firstname;
    }

    public void setLastName(String lastname) {
        this._lastname = lastname;
    }

    public String getLastName() {
        return this._lastname;
    }

    public void setAdress(String address) {
        this._address = address;
    }

    public String getAddress() {
        return this._address;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getEmail() {
        return this._email;
    }

    public void setNic(String nic) {
        this._nic = nic;
    }

    public String getNic() {
        return this._nic;
    }

    public void setAllergies(String allergies) {
        this._allergies = allergies;
    }

    public String getAllergies() {
        return this._allergies;
    }
}
