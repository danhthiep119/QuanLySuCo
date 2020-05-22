package view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.quanlysucotruncu.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class EditProfile extends AppCompatActivity {
    EditText txtEditName,txtEditPhone,txtEditAddress;
    Button btnImageChange,btnChange;
    ImageView imgAvatar;
    String name,image,key;
    final int CAMERA_RESULT=101;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        try {
        getDataFromIntent();
        }
        catch (Exception e){

        }
        addControls();
        addEvents();
    }

    private void addEvents() {
        txtEditName.setText(name);
        try {
            convertBase64ToBitmap(image);
        }
        catch (Exception e){

        }
        btnImageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });
    }

    private void editProfile() {
        String name = txtEditName.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/username");
        myRef.child(key).child("name").setValue(name);
        myRef.child(key).child("image").setValue(image);
        Toast.makeText(this,"Cập nhật thông tin thành công!",Toast.LENGTH_SHORT).show();
        finish();
    }

    private void convertBase64ToBitmap(String image) {
        byte[] decodedString = Base64.decode(image,Base64.DEFAULT);
        Bitmap bitmap=BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        imgAvatar.setImageBitmap(bitmap);

    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            openCamera();
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){

            }
            requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_RESULT);
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
                imgAvatar.setImageBitmap(bitmap);
                convertBimapToBase64String(bitmap);
            }
        }
        else super.onActivityResult(requestCode,resultCode,data);
    }

    private void convertBimapToBase64String(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte [] b=baos.toByteArray();
        image = Base64.encodeToString(b,Base64.DEFAULT);
    }


    private void getDataFromIntent() {
        name = getIntent().getExtras().getString("NAME");
        image = getIntent().getExtras().getString("IMAGE");
        key = getIntent().getExtras().getString("KEY");
    }

    private void addControls() {
        txtEditName=findViewById(R.id.txtEditName);
        txtEditPhone=findViewById(R.id.txtPhone);
        btnChange=findViewById(R.id.btnChange);
        btnImageChange=findViewById(R.id.btnImageChange);
        imgAvatar=findViewById(R.id.imgAvata);
        txtEditAddress=findViewById(R.id.txtEditAddress);
    }
}
