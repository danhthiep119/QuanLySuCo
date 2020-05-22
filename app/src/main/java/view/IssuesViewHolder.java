package view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysucotruncu.R;

public class IssuesViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTitle,txtAddress,txtTime,txtDate;

    public IssuesViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTitle=itemView.findViewById(R.id.txtTitle);
        txtAddress=itemView.findViewById(R.id.txtAddress);
        txtTime=itemView.findViewById(R.id.txtTime);
        txtDate=itemView.findViewById(R.id.txtDate);
    }
}
