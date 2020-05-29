package view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysucotruncu.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import viewmodel.CameraImageAdapter;

public class DetailIssue extends AppCompatActivity {
    final String TAG = "DETAILSISSUE";
    Button btnSend;
    ImageView btnCamera;
    TextView txtInputTitle,txtInputAddress,txtInputDescription,txtStatus;
    List<Bitmap> bitmapList = new ArrayList<>();
    List<String> base64Bitmap = new ArrayList<>();
    RecyclerView rvCamera;
    public CameraImageAdapter mAdapter;
    final int CAMERA_RESULT=101;
    int status;
    private String title,address,description;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_issue);
        AddControls();
        AddEvents();
        try {
            TransferIntent();
        }
        catch (Exception e){
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void TransferIntent() {
        title = getIntent().getExtras().getString("TITLE");
        description = getIntent().getExtras().getString("DESCRIPTION");
        address = getIntent().getExtras().getString("ADDRESS");
//        status=Integer.parseInt( getIntent().getExtras().getString("STATUS"));
//        if (status == 0) {
//            txtStatus.setText("Trạng thái: Chưa xử lý");
//        } else {
//            txtStatus.setText("Trạng thái: Đã xử lý");
//        }

        txtInputTitle.setText(title);
        txtInputDescription.setText(description);
        txtInputAddress.setText(address);
    }

    private void convertBimapToBase64String(List<Bitmap> bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (Bitmap bm1 : bitmap){
            bm1.compress(Bitmap.CompressFormat.PNG,100,baos);
            byte [] b=baos.toByteArray();
            base64Bitmap.add(Base64.encodeToString(b,Base64.DEFAULT));
        }
    }

    private void AddEvents() {
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                    DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
                    LocalDateTime now = LocalDateTime.now();
                    convertBimapToBase64String(bitmapList);
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference issues = database.getReference("users/issues");
                    String key = issues.push().getKey();
                    issues.child(key).child("title").setValue(txtInputTitle.getText().toString());
                    issues.child(key).child("status").setValue(status);
                    issues.child(key).child("address").setValue(txtInputAddress.getText().toString());
                    issues.child(key).child("description").setValue(txtInputDescription.getText().toString());
                    issues.child(key).child("time").setValue(time.format(now));
                    issues.child(key).child("date").setValue(date.format(now));
                    for(int i=0;i<base64Bitmap.size();i++){
                        issues.child(key).child("image").child(String.valueOf(i)).setValue(base64Bitmap.get(i));
                    }
                    finish();
            }
        });
    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_RESULT){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openCamera();
            }
            else {

            }
        }
        else super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    private void openCamera() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,CAMERA_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode==CAMERA_RESULT){
                Bundle extras=data.getExtras();
                Bitmap bitmap=(Bitmap) extras.get("data");
                bitmapList.add(bitmap);
                Log.w(TAG,"The List Has"+bitmapList.size());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailIssue.this,LinearLayoutManager.HORIZONTAL,false);
                rvCamera.setLayoutManager(linearLayoutManager);
                rvCamera.setHasFixedSize(true);
                rvCamera.setAdapter(new CameraImageAdapter(bitmapList,DetailIssue.this));
            }
        }
        else super.onActivityResult(requestCode,resultCode,data);
    }

    private void AddControls() {
        btnSend=findViewById(R.id.btnSend);
        txtInputTitle=findViewById(R.id.txtInputTitle);
        txtInputAddress=findViewById(R.id.txtInputAddress);
        txtInputDescription=findViewById(R.id.txtInputDetail);
        txtStatus=findViewById(R.id.txtStatus);
        btnCamera=findViewById(R.id.btnCamera);
        rvCamera=findViewById(R.id.rvCamera);
    }


}
