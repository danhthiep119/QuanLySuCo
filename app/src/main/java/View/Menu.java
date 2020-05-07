package View;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysucotruncu.R;
import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_nav);
        NavigationView nv= findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return true;
    }
}
