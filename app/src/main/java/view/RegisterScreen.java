package view;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysucotruncu.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity {
    Button btnLogin,btnRegister;
    TextInputEditText txtInputName,txtInputPhone,txtInputPass,txtInputRepass;
    TextInputLayout viewName,viewPhone,viewPass,viewRepass;
    boolean check,checkPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register_view);
        AddControls();
        AddEvents();
    }

    void CheckInput(){
        check = false;
        checkPass=false;
        if (txtInputName.getText().toString().isEmpty()){
            viewName.setErrorEnabled(true);
            viewName.setError("Bạn Chưa nhập tên");
            txtInputName.setError("Bạn Chưa nhập tên");
            viewName.requestFocus();
        }
        else viewName.setErrorEnabled(false);
        if (txtInputPhone.getText().toString().isEmpty()){
            viewPhone.setErrorEnabled(true);
            viewPhone.setError("Bạn chưa nhập số điện thoại");
            txtInputPhone.setError("Bạn chưa nhập số điện thoại");
            viewPhone.requestFocus();
        }
        else viewPhone.setErrorEnabled(false);
        if (txtInputPass.getText().toString().isEmpty()){
            viewPass.setErrorEnabled(true);
            viewPass.setError("Bạn chưa nhập mật khẩu");
            txtInputPass.setError("Bạn chưa nhập mật khẩu");
            viewPass.requestFocus();
        }
        else viewPass.setErrorEnabled(false);
        if(txtInputRepass.getText().toString().isEmpty()){
            viewRepass.setErrorEnabled(true);
            viewRepass.setError("Bạn chưa nhập lại mật khẩu");
            txtInputRepass.setError("Bạn chưa nhập lại mật khẩu");
            viewRepass.requestFocus();
        }
        else if(!txtInputPass.getText().toString().equals(txtInputRepass.getText().toString())){
            viewRepass.setErrorEnabled(true);
            viewRepass.setError("Nhập lại mật khẩu không đúng");
            txtInputRepass.setError("Nhập lại mật khẩu không đúng");
            viewRepass.requestFocus();
        }
        else checkPass=true;
        check=  !txtInputName.getText().toString().isEmpty() &&
                !txtInputPhone.getText().toString().isEmpty() &&
                !txtInputPass.getText().toString().isEmpty() &&
                !txtInputRepass.getText().toString().isEmpty() &&
                checkPass? true:false;

        if(check){
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference myRef=database.getReference("users/username");
            String key=myRef.push().getKey();
            myRef.child(key).child("name").setValue(txtInputName.getText().toString());
            myRef.child(key).child("phone").setValue(txtInputPhone.getText().toString());
            myRef.child(key).child("password").setValue(txtInputPass.getText().toString());
            Toast.makeText(RegisterScreen.this,"Bạn đã đăng ký thanh công",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void AddEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInput();
            }
        });
    }

    private void AddControls() {
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);

        txtInputName=findViewById(R.id.txtInputName);
        txtInputPhone=findViewById(R.id.txtInputPhone);
        txtInputPass=findViewById(R.id.txtInputPassword);
        txtInputRepass=findViewById(R.id.txtInputRepass);

        viewName=findViewById(R.id.view_name);
        viewPhone=findViewById(R.id.view_rePhone);
        viewPass=findViewById(R.id.view_rePassword);
        viewRepass=findViewById(R.id.view_rePass);

    }
}
