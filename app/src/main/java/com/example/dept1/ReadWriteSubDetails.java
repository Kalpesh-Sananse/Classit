package com.example.dept1;

public class ReadWriteSubDetails {
    public String  subname, tname, createdby;

    public String getSubname() {
        return subname;
    }

    public ReadWriteSubDetails() {
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public ReadWriteSubDetails(String subnameU, String tnameU, String createdby) {
        this.subname = subnameU;
        this.tname = tnameU;
        this.createdby = createdby;
    }
}
