package view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysucotruncu.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Issue;
import viewmodel.IssueAdapter;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton btnMenu,btnSearch,btnMore;
    TextView txtProfileName,txtProfilePhone;
    ImageView imgProfileUser;
    RecyclerView recyclerView;
    List<Issue> issueList=new ArrayList<>();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String name, phone, image, key;
    MyCustomExitDialog myCustomExitDialog;
    final String TAG="Main";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_view);
        getDataFromIntent();
        readDataFromFireBase();
        AddControls();
        NavigationDrawer();
        AddEvents();
    }


    private void getDataFromIntent() {
        try {
            name=getIntent().getExtras().getString("NAME");
            phone=getIntent().getExtras().getString("PHONE");
            key=getIntent().getExtras().getString("KEY");
            image=getIntent().getExtras().getString("IMAGE");
            txtProfileName.setText(name);
            txtProfilePhone.setText(phone);
            displayUserProfile(image);
        }
        catch (Exception e){

        }
    }

    private void displayUserProfile(String image){
        byte[] decode = Base64.decode(image,Base64.DEFAULT);
        Bitmap bimap = BitmapFactory.decodeByteArray(decode,0,decode.length);
        imgProfileUser.setImageBitmap(bimap);
    }

    private void NavigationDrawer() {
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            showDialog();
        }
    }

    private void showDialog(){
        myCustomExitDialog = new MyCustomExitDialog(Main.this);
        myCustomExitDialog.show();
    }

    private void readDataFromFireBase() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference issues = database.getReference("users/issues");
        issues.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                issueList.clear();
                for (DataSnapshot key:dataSnapshot.getChildren()){
                    Issue issue = key.getValue(Issue.class);
                    issueList.add(issue);
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new IssueAdapter(issueList, Main.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void AddEvents() {
    }

    private void AddControls() {
        btnMenu=findViewById(R.id.btnMenu);
        btnMore=findViewById(R.id.btnMore);
        btnSearch=findViewById(R.id.btnSearch);
        drawerLayout=findViewById(R.id.drawner_layout);
        recyclerView=findViewById(R.id.recycleView);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        txtProfileName=findViewById(R.id.txtProfileUser);
        txtProfilePhone=findViewById(R.id.txtProfilePhone);
        imgProfileUser=findViewById(R.id.imgProfileUser);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.error:
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                startActivity(new Intent(Main.this,DetailIssue.class));
                break;
            case R.id.list_error:
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.profile:
                Intent intent = new Intent(this,EditProfile.class);
                intent.putExtra("NAME",name);
                intent.putExtra("PHONE",phone);
                intent.putExtra("KEY",key);
                intent.putExtra("IMAGE",image);
                startActivity(intent);
                break;
            case R.id.setting:
                break;
            case R.id.logout:
                showDialog();
                break;
        }
        return true;
    }
}
