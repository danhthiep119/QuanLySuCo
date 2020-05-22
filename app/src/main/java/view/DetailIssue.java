package view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysucotruncu.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailIssue extends AppCompatActivity {
    Button btnSend;
    TextView txtInputTitle,txtInputAddress,txtInputDescription,txtStatus;
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

    private void AddEvents() {

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference issues = database.getReference("users/issues");
                    String key = issues.push().getKey();
                    issues.child(key).child("title").setValue(txtInputTitle.getText().toString());
                    issues.child(key).child("status").setValue(status);
                    issues.child(key).child("address").setValue(txtInputAddress.getText().toString());
                    issues.child(key).child("description").setValue(txtInputDescription.getText().toString());
                    finish();
            }
        });
    }

    private void AddControls() {
        btnSend=findViewById(R.id.btnSend);
        txtInputTitle=findViewById(R.id.txtInputTitle);
        txtInputAddress=findViewById(R.id.txtInputAddress);
        txtInputDescription=findViewById(R.id.txtInputDetail);
        txtStatus=findViewById(R.id.txtStatus);
    }


}
