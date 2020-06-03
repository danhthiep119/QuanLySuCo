package view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysucotruncu.R;

import java.util.ArrayList;
import java.util.List;

import viewmodel.CameraImageAdapter;
import viewmodel.IssueAdapter;
import viewmodel.ListImageAdapter;

public class InfoIssue extends AppCompatActivity {
    TextView txtInfoStatus,txtInfoTitle,txtInfoAddress,txtInfoDescription;
    Button btnBack,btnReInfo;
    GridView gvImage;
    List<String> imageList=new ArrayList<>();
    List<Bitmap> afterConverBase64toBitmap=new ArrayList<>();
    ListImageAdapter adapter;
    final String TAG="InfoIssue";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        convertBase64ToBitmap(imageList);

    }

    private void convertBase64ToBitmap(List<String> imageList) {
        for(String image:imageList) {
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            afterConverBase64toBitmap.add(bitmap);
        }
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
        gvImage=findViewById(R.id.gvImage);
    }
}
