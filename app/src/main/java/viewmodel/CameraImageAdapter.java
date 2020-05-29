package viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysucotruncu.R;

import java.util.ArrayList;
import java.util.List;

import view.DetailIssue;

public class CameraImageAdapter extends RecyclerView.Adapter<CameraImageAdapter.ViewHolder> {

    private List<Bitmap> imageCamera;
    private Context mContext;

    public CameraImageAdapter(List<Bitmap> imageCamera, Context mContext) {
        this.imageCamera = imageCamera;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image_camera,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Bitmap imgBitmap = imageCamera.get(position);
        holder.imgCamera.setImageBitmap(imgBitmap);
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCamera.remove(imageCamera.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageCamera.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgCamera;
        private Button btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCamera=itemView.findViewById(R.id.imgCamera);
            btnDel=itemView.findViewById(R.id.btnDel);
        }
    }
}
