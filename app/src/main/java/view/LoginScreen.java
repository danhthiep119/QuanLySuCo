package view;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysucotruncu.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.User;

public class LoginScreen extends AppCompatActivity {
    Button btnRegister,btnLogin;
    TextInputEditText txtPhone,txtPass;
    TextInputLayout viewPhone,viewPass;
    Map<String,User> map = new HashMap<String,User>();
    final int CAMERA_RESULT=101;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        AddControls();
        AddEvents();
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){

            }
            requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_RESULT);
        }
    }
    public void ReadDataFromFireBase(final String phone, final String password){
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("users/username");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot  data :dataSnapshot.getChildren()){
                    String key = data.getKey();
                    User user = data.getValue(User.class);
                    map.put(key,user);
                }

                Set<String> key = map.keySet();
                for(String keyNode:key){
                        if(phone.equals(map.get(keyNode).getPhone())){
                            if(password.equals(map.get(keyNode).getPassword())){
                                txtPhone.setText("");
                                txtPass.setText("");
                                Toast.makeText(LoginScreen.this,"Đăng Nhập Thành Công! Chào mừng:"+map.get(keyNode).getName(),Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginScreen.this,Main.class);
                                intent.putExtra("NAME",map.get(keyNode).getName());
                                intent.putExtra("PHONE",phone);
                                intent.putExtra("IMAGE",map.get(keyNode).getImage());
                                intent.putExtra("KEY",keyNode);
                                startActivity(intent);
                            }
                            else {
                                txtPass.setText("");
                                viewPass.setErrorEnabled(true);
                                viewPass.setError("Bạn nhập sai sđt hoặc mật khẩu");
                                txtPass.setError("Bạn nhập sai sđt hoặc mật khẩu");
                                viewPass.requestFocus();
                            }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    void CheckInput(){
            if (txtPhone.getText().toString().isEmpty()) {
                viewPhone.setErrorEnabled(true);
                viewPhone.setError("Bạn Chưa nhập sđt");
                txtPhone.setError("Bạn Chưa nhập sđt");
                viewPhone.requestFocus();
            } else if (txtPass.getText().toString().isEmpty()) {
                viewPhone.setErrorEnabled(false);
                viewPass.setErrorEnabled(true);
                viewPass.setError("Bạn chưa nhập mật khẩu");
                txtPass.setError("Bạn chưa nhập mật khẩu");
                viewPass.requestFocus();
            }
            else if(!txtPhone.getText().toString().isEmpty() &&
                    !txtPass.getText().toString().isEmpty())
            {
                String phone = txtPhone.getText().toString();
                String password = txtPass.getText().toString();
                ReadDataFromFireBase(phone,password);
            }

    }

    private void AddEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this,RegisterScreen.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInput();
            }
        });
    }

    private void AddControls() {
        btnRegister=findViewById(R.id.btnRegister);
        btnLogin=findViewById(R.id.btnLogin);
        txtPhone=findViewById(R.id.txtPhone);
        txtPass=findViewById(R.id.txtPassword);
        viewPhone=findViewById(R.id.view_phone);
        viewPass=findViewById(R.id.view_password);
    }
}
