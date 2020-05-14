package View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.issue.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {
    Button btnRegister,btnLogin;
    TextInputEditText txtPhone,txtPass;
    TextInputLayout viewPhone,viewPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        AddEvents();
    }

    void CheckInput(){
        if(txtPhone.getText().toString().isEmpty()){
            viewPhone.setErrorEnabled(true);
            viewPhone.setError("Bạn Chưa nhập sđt");
        }
        else if(txtPass.getText().toString().isEmpty()){
            viewPhone.setErrorEnabled(false);
            viewPass.setErrorEnabled(true);
            viewPass.setError("Bạn chưa nhập mật khẩu");
        }
        else startActivity(new Intent(LoginScreen.this,NavHost.class));
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
