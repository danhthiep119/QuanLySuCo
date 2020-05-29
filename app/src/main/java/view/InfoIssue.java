package view;

import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysucotruncu.R;

public class InfoIssue extends AppCompatActivity {
    TextView txtInfoStatus,txtInfoTitle,txtInfoAddress,txtInfoDescription;
    Button btnBack,btnReInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_issue);
        addControls();
        addEvents();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        int status = getIntent().getExtras().getInt("STATUS");
        txtInfoStatus.setText(status==0?"Chưa xử lý":"Đã xử lý");
        txtInfoTitle.setText(getIntent().getExtras().getString("TITLE"));
        txtInfoAddress.setText(getIntent().getExtras().getString("ADDRESS"));
        txtInfoDescription.setText(getIntent().getExtras().getString("DESCRIPTION"));
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnReInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void addControls() {
        txtInfoStatus=findViewById(R.id.txtInfoStatus);
        txtInfoTitle=findViewById(R.id.txtInfoTitle);
        txtInfoAddress=findViewById(R.id.txtInfoAddress);
        txtInfoDescription=findViewById(R.id.txtInfoDecription);
        btnReInfo=findViewById(R.id.btnReInfo);
        btnBack=findViewById(R.id.btnBack);
    }
}
