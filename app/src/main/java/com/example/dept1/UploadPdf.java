package com.example.dept1;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.telephony.CellSignalStrength;
import android.text.TextUtils;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;




public class UploadPdf extends AppCompatActivity {

    String pdfname;
    Uri uri;
    ActivityResultLauncher mStartGallery = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @SuppressLint("Range")
                @Override
                public void onActivityResult(Uri result) {
                     uri = result;
                    if(uri.toString().startsWith("content://")){
                        Cursor cursor = null;
                        try {
                            cursor = UploadPdf.this.getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                pdfname = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                TextView textView1 = findViewById(R.id.pdftextview);
                                textView1.setText(pdfname);
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            }
                        }
                    else if( uri.toString().startsWith("file://")){
                        pdfname = new File(uri.toString()).getName();
                        TextView textView1 = findViewById(R.id.pdftextview);
                        textView1.setText(pdfname);

                    }


                }
            });
    private  String title , Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditText  tname, titlePdf;
        StorageReference storageReference;
        DatabaseReference databaseReference;
        ProgressDialog pd;


        pd = new ProgressDialog(this);

        Intent intent = getIntent();

        setContentView(R.layout.activity_upload_pdf);
        boolean flag;


        TextView pdfNametext;
        pdfNametext = findViewById(R.id.pdftextview);
       // tname = findViewById(R.id.Tname);
        titlePdf = findViewById(R.id.titlepdf);

        Button btn = findViewById(R.id.uploadpdf);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        CardView cardView = findViewById(R.id.carduploadpdf);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mStartGallery.launch("application/pdf");
            }



        });

        String subname = intent.getStringExtra("subname");
        //String subname = "cn";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             title = titlePdf.getText().toString();


             if (TextUtils.isEmpty(title)) {
                Toast.makeText(UploadPdf.this, "Title is required", Toast.LENGTH_SHORT).show();

                titlePdf.setError("title is required");
                titlePdf.requestFocus();
            }else if(uri == null){
                Toast.makeText(UploadPdf.this, "Please select the pdf", Toast.LENGTH_SHORT).show();
                titlePdf.setError("Please Select the Pdf file");
                titlePdf.requestFocus();
            }else{
                uploadPdf();
            }
            }


            private void uploadPdf() {
                pd.setTitle("please wait...");
                pd.setMessage("uploading pdf");
                pd.show();
                StorageReference reference = storageReference.child("pdf/"+pdfname+"-"+ System.currentTimeMillis()+".pdf");
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while(!uriTask.isComplete());
                    Uri uri1 = uriTask.getResult();
                    uploadData(subname,title,String.valueOf(uri1));
                    }




                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(UploadPdf.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }



            private void uploadData(String subname, String title, String s) {
                String uniquekey = databaseReference.child("pdf").push().getKey();

                ReadWritePdfDetails readWritePdfDetails = new ReadWritePdfDetails(subname,title,s);

                /*
                HashMap data = new HashMap();

                data.put("pdfTitle",title);
                data.put("PdfUrl",s);

                data.put("subjectName",subname);*/
                databaseReference.child("pdf").child(uniquekey).setValue(readWritePdfDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(UploadPdf.this, "pdf uploaded successfully", Toast.LENGTH_SHORT).show();
                        TextView textView = findViewById(R.id.tview);
                        pdfNametext.setText("");
                        textView.setText("");
                        //tname.setText("");
                        titlePdf.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override

                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();;
                        Toast.makeText(UploadPdf.this, "Failed to upload pdf", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}