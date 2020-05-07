package View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysucotruncu.R;

public class LoginScreen extends AppCompatActivity {
    Button btnRegister,btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        AddEvents();
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
                startActivity(new Intent(LoginScreen.this,NavHost.class));
            }
        });
    }

    private void AddControls() {
        btnRegister=findViewById(R.id.btnRegister);
        btnLogin=findViewById(R.id.btnLogin);
    }
}
