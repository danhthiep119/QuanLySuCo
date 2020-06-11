package viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysucotruncu.R;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Issue;
import view.InfoIssue;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.DataViewHolder>{
    private Map<List<String>,List<Issue>> issue;
    private Context context;
    final static String TAG="Issue Adapter";
    public IssueAdapter(Map<List<String>,List<Issue>> issue, Context context) {
        this.issue = issue;
        this.context = context;
    }

    @NonNull
    @Override
    public IssueAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_issue_adapter,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssueAdapter.DataViewHolder holder, final int position) {
        Set<List<String>> key = issue.keySet();
        for(final List<String> keyNode:key)
        {
            String name = issue.get(keyNode).get(position).getTitle();
            String address = issue.get(keyNode).get(position).getAddress();
            String date = issue.get(keyNode).get(position).getDate();
            String time = issue.get(keyNode).get(position).getTime();
            holder.txtTitle.setText(name);
            holder.txtAddress.setText(address);
            holder.txtDate.setText(date);
            holder.txtTime.setText(time);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Toast.makeText(context, "Bạn chọn: " + issue.get(keyNode).get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        String VIEW_TITLE = issue.get(keyNode).get(position).getTitle();
                        String VIEW_DESCRIPTION = issue.get(keyNode).get(position).getDescription();
                        String VIEW_ADDRESS = issue.get(keyNode).get(position).getAddress();
                        int VIEW_STATUS = issue.get(keyNode).get(position).getStatus();
                        Intent intent = new Intent(context, InfoIssue.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra("TITLE", VIEW_TITLE);
                        intent.putExtra("DESCRIPTION", VIEW_DESCRIPTION);
                        intent.putExtra("ADDRESS", VIEW_ADDRESS);
                        intent.putExtra("STATUS", VIEW_STATUS);
                        intent.putExtra("KEY", keyNode.get(position));
                        context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return issue==null?0:issue.size();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle,txtAddress,txtTime,txtDate;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtDate = itemView.findViewById(R.id.txtDate);

        }
    }
}

















