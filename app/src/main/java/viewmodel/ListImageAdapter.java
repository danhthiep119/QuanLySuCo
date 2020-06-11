package viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;

import com.example.quanlysucotruncu.R;

import java.util.ArrayList;
import java.util.List;

public class ListImageAdapter extends BaseAdapter {
    List<Bitmap> bitmap;
    Context mContext;
    int resource;


    public ListImageAdapter(Context context,  List<Bitmap> objects) {
        this.mContext=context;
        this.bitmap=objects;
    }

    @Override
    public int getCount() {
        return bitmap==null?0:bitmap.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(mContext).inflate(resource,parent,false);
        ImageView imageIssue=view.findViewById(R.id.imgCamera);
        Button btnDel=view.findViewById(R.id.btnDel);
        btnDel.setVisibility(View.INVISIBLE);
        imageIssue.setImageBitmap(bitmap.get(position));
        return view;
    }
}
