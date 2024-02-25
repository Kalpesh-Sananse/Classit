package com.example.dept1;

import javax.xml.transform.sax.SAXResult;

public class ReadWriteFacDetails {

    public String name, mobile,regno,pass;


    public ReadWriteFacDetails(String facname, String facphone, String facregno ,String facpass) {
        this.name = facname;
        this.pass = facpass;
        this.mobile = facphone;
        this.regno = facregno;
    }
}
