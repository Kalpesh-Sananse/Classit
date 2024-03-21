package com.example.dept1;

public class ReadWriteUserDetails {
    public String fullname,mobile,regno;

    public ReadWriteUserDetails() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public  ReadWriteUserDetails(String stdname, String stdphone, String regino){
        this.fullname = stdname;
        this.mobile = stdphone  ;
        this.regno = regino;
    }
}
