package view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysucotruncu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Issue;
import viewmodel.IssueAdapter;

public class NavHost extends AppCompatActivity {
    ImageButton btnMenu,btnSearch,btnMore;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    List<Issue> issueList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_host);
        readDataFromFireBase();
        AddControls();
        AddEvents();
    }

    private void readDataFromFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference issues = database.getReference("users/issues");
        issues.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot key:dataSnapshot.getChildren()){
                    Issue issue = key.getValue(Issue.class);
                    issueList.add(issue);
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NavHost.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new IssueAdapter(issueList,NavHost.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void AddEvents() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbar toolbar=findViewById(R.id.toolbar);
//                setSupportActionBar(toolbar);
                startActivity(new Intent(NavHost.this,Menu.class));
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavHost.this,DetailIssue.class));
            }
        });
    }

    private void AddControls() {
        btnMenu=findViewById(R.id.btnMenu);
        btnMore=findViewById(R.id.btnMore);
        btnSearch=findViewById(R.id.btnSearch);
        drawerLayout=findViewById(R.id.drawner_layout);
        recyclerView=findViewById(R.id.recycleView);
    }
}
