package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLEncoder;

public class viewpdf extends AppCompatActivity {

    WebView pdfview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdf);

        pdfview = findViewById(R.id.webview);
        pdfview.getSettings().setJavaScriptEnabled(true);

        String filename = getIntent().getStringExtra("filename");
        String filerul = getIntent().getStringExtra("fileurl");

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(filename);
        pd.setMessage("opening...");

        pdfview.setWebViewClient(new WebViewClient(){

                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);
                                         pd.show();
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         pd.dismiss();
                                     }
                                 }
        );

        String url = "" ;
        try {
            url = URLEncoder.encode(filerul,"UTF-8");
        }catch (Exception e){

        }
        pdfview.loadUrl("http://docs.google.com/gview?embedded=true&url="+url);
    }

}