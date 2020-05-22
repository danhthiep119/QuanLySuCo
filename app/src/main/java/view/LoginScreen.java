package view;

import android.content.Intent;
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
    List<User> listUser=new ArrayList<>();
    public String phone,password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        AddEvents();
    }
    public void ReadDataFromFireBase(){
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("users/username");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,User> map = new HashMap<String,User>();
                for(DataSnapshot  data :dataSnapshot.getChildren()){
                    String key = data.getKey();
                    User user = data.getValue(User.class);
                    map.put(key,user);
                }

                Set<String> key = map.keySet();
                for(String keyNode:key){
                        if(phone.equals(map.get(keyNode).getPhone())){
                            if(password.equals(map.get(keyNode).getPassword())){
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
                txtPass.setError("Bạn Chưa nhập sđt");
                viewPhone.requestFocus();
            } else if (txtPass.getText().toString().isEmpty()) {
                viewPhone.setErrorEnabled(false);
                viewPass.setErrorEnabled(true);
                viewPass.setError("Bạn chưa nhập mật khẩu");
                txtPhone.setError("Bạn chưa nhập mật khẩu");
                viewPass.requestFocus();
            }
            else if(!txtPhone.getText().toString().isEmpty() &&
                    !txtPass.getText().toString().isEmpty())
            {
                phone = txtPhone.getText().toString();
                password = txtPass.getText().toString();
                ReadDataFromFireBase();
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
