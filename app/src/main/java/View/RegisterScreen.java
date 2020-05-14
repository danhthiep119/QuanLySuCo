package View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.issue.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterScreen extends AppCompatActivity {
    Button btnLogin,btnRegister;
    TextInputEditText txtName,txtrePhone,txtrePass,txtRePass;
    TextInputLayout viewName,viewPhone,viewPass,viewRepass;
    boolean check,checkPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
        AddControls();
        AddEvents();
    }

    void CheckInput(){
        check = false;
        checkPass=false;
        if (txtName.getText().toString().isEmpty()){
            viewName.setErrorEnabled(true);
            viewName.setError("Bạn Chưa nhập tên");
        }
        else viewName.setErrorEnabled(false);
        if (txtrePhone.getText().toString().isEmpty()){
            viewPhone.setErrorEnabled(true);
            viewPhone.setError("Bạn chưa nhập số điện thoại");
        }
        else viewPhone.setErrorEnabled(false);
        if (txtrePass.getText().toString().isEmpty()){
            viewPass.setErrorEnabled(true);
            viewPass.setError("Bạn chưa nhập mật khẩu");
        }
        else viewPass.setErrorEnabled(false);
        if(txtRePass.getText().toString().isEmpty()){
            viewRepass.setErrorEnabled(true);
            viewRepass.setError("Bạn chưa nhập lại mật khẩu");
        }
        else if(!txtRePass.getText().toString().equals(txtrePass.getText().toString())){
            viewRepass.setErrorEnabled(true);
            viewRepass.setError("Nhập lại mật khẩu không đúng");
        }
        else checkPass=true;
        check=  !txtName.getText().toString().isEmpty() &&
                !txtrePhone.getText().toString().isEmpty() &&
                !txtrePass.getText().toString().isEmpty() &&
                !txtRePass.getText().toString().isEmpty() &&
                checkPass? true:false;

        if(check){
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

        txtName=findViewById(R.id.txtName);
        txtrePhone=findViewById(R.id.txtRepass);
        txtrePass=findViewById(R.id.txtrePassword);
        txtRePass=findViewById(R.id.txtRepass);

        viewName=findViewById(R.id.view_name);
        viewPhone=findViewById(R.id.view_rePhone);
        viewPass=findViewById(R.id.view_rePassword);
        viewRepass=findViewById(R.id.view_rePass);

    }
}
