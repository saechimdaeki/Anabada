package springboot.juseong.anabada.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import springboot.juseong.anabada.DataModel.FileModel;
import springboot.juseong.anabada.DataModel.getFileModel;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.screen.ZoomPicActivity;
import springboot.juseong.anabada.screen.getPostAcitivty;

public class getFileAdpater extends RecyclerView.Adapter<getFileAdpater.ItemViewHolder> {
    private ArrayList<getFileModel> listData=new ArrayList<>();
    @NonNull
    @Override
    public getFileAdpater.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.get_recycle_file_item,parent,false);
        return new getFileAdpater.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final getFileAdpater.ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                final Intent intent;
                intent=new Intent(context, ZoomPicActivity.class);
                intent.putExtra("data",holder.imagedata.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(getFileModel data) {
        listData.add(data);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private View mview;
        private CircleImageView image;
        private TextView postid,id,imagedata;
        ItemViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            postid=itemView.findViewById(R.id.filepostid);
            id=itemView.findViewById(R.id.fileid);
            image=itemView.findViewById(R.id.file_image);
            imagedata=itemView.findViewById(R.id.imagedata);
        }

        void onBind(getFileModel data) {
            postid.setText(String.valueOf(data.getPostid()));
            id.setText(String.valueOf(data.getId()));
            imagedata.setText(data.getData());
            byte[] imageByte= Base64.decode(data.getData(),Base64.DEFAULT);
            Glide.with(itemView.getContext()).load(imageByte).error(R.drawable.noimage)
                    .fitCenter()
                    .crossFade()
                    .into(image);
        }
    }
}
