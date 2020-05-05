package View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quanlysucotruncu.R;

public class NavHost extends AppCompatActivity {
    ImageButton btnMenu,btnSearch,btnMore;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_host);

        AddControls();
        AddEvents();
    }

    private void AddEvents() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbar toolbar=findViewById(R.id.toolbar);
//                setSupportActionBar(toolbar);
            }
        });
    }

    private void AddControls() {
        btnMenu=findViewById(R.id.btnMenu);
        btnMore=findViewById(R.id.btnMore);
        btnSearch=findViewById(R.id.btnSearch);
        drawerLayout=findViewById(R.id.drawner_layout);
    }
}