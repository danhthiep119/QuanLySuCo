package view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.example.quanlysucotruncu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Issue;
import model.OnLoadMoreListener;
import model.User;
import viewmodel.IssueAdapter;

public class ListIssue extends AppCompatActivity {
    ArrayList<Issue> listIssue=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_issue);
        AddControls();
        AddEvents();
    }

    public void AddEvents() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference issues = database.getReference("users/issues");
        issues.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot key:dataSnapshot.getChildren()){
                    Issue issue = key.getValue(Issue.class);
                    listIssue.add(issue);
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListIssue.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new IssueAdapter(listIssue,ListIssue.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void AddControls() {
        recyclerView=findViewById(R.id.recycleView);
    }
}













