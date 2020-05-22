package viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysucotruncu.R;

import java.util.List;

import model.Issue;
import view.DetailIssue;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.DataViewHolder>{
    private List<Issue> issue;
    private Context context;
    public IssueAdapter(List<Issue> issue, Context context) {
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
    public void onBindViewHolder(@NonNull final IssueAdapter.DataViewHolder holder, final int position) {
        String name = issue.get(position).getTitle();
        String address = issue.get(position).getAddress();
        String date = issue.get(position).getDate();
        String time = issue.get(position).getTime();
        holder.txtTitle.setText(name);
        holder.txtAddress.setText(address);
        holder.txtDate.setText(date);
        holder.txtTime.setText(time);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Bạn chọn: "+issue.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                String VIEW_TITLE= issue.get(position).getTitle();
                String VIEW_DESCRIPTION= issue.get(position).getDescription();
                String VIEW_ADDRESS= issue.get(position).getAddress();
                int VIEW_STATUS= issue.get(position).getStatus();
                Intent intent = new Intent(context, DetailIssue.class);
                intent.putExtra("TITLE",VIEW_TITLE);
                intent.putExtra("DESCRIPTION",VIEW_DESCRIPTION);
                intent.putExtra("ADDRESS",VIEW_ADDRESS);
                intent.putExtra("STATUS",VIEW_STATUS);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return issue==null?0:issue.size();
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle,txtAddress,txtTime,txtDate;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtAddress=itemView.findViewById(R.id.txtAddress);
            txtTime=itemView.findViewById(R.id.txtTime);
            txtDate=itemView.findViewById(R.id.txtDate);
        }
    }
}

















