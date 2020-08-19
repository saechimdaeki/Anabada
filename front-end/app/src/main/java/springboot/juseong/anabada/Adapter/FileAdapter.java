package springboot.juseong.anabada.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import springboot.juseong.anabada.DataModel.FileModel;
import springboot.juseong.anabada.R;
public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ItemViewHolder> {
    private ArrayList<FileModel> listData=new ArrayList<>();
    @NonNull
    @Override
    public FileAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.file_recycler_item,parent,false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull FileAdapter.ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                final Intent intent;
                //TODO
            }
        });
    }
    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(FileModel data) {
        listData.add(data);
    }
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private View mview;
       private CircleImageView image;
        ItemViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            image=itemView.findViewById(R.id.post_image);
        }
        @SuppressLint("SetTextI18n")
        void onBind(FileModel data) {
            Glide.with(itemView.getContext()).load(data.getData())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .fitCenter()
                    .crossFade()
                    .into(image);
        }
    }

}
