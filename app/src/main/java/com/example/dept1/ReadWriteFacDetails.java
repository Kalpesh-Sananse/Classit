package com.example.dept1;

import javax.xml.transform.sax.SAXResult;

public class ReadWriteFacDetails {
    public ReadWriteFacDetails() {
    }

    public String name, mobile,regno,pass;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ReadWriteFacDetails(String facname, String facphone, String facregno , String facpass) {
        this.name = facname;
        this.pass = facpass;
        this.mobile = facphone;
        this.regno = facregno;
    }
}
