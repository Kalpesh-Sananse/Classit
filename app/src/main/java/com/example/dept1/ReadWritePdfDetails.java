package com.example.dept1;

public class ReadWritePdfDetails {
    public String Sname, Title ,url;

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ReadWritePdfDetails(String sname, String title, String url) {
        Sname = sname;
        Title = title;
        this.url = url;
    }
}

