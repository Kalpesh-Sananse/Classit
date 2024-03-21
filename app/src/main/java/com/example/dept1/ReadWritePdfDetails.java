package com.example.dept1;

public class ReadWritePdfDetails {

    private String subjectName,PdfUrl ,pdfTitle;

    public ReadWritePdfDetails() {
    }

    public ReadWritePdfDetails(String subname, String title, String s) {
        this.subjectName = subname;
        this.pdfTitle = title;
        this.PdfUrl = s;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }

    public String getPdfTitle() {
        return pdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }
}