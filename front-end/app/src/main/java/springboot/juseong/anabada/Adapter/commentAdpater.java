package springboot.juseong.anabada.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import springboot.juseong.anabada.DataModel.CommentModel;

import springboot.juseong.anabada.R;

public class commentAdpater extends RecyclerView.Adapter<commentAdpater.ItemViewHolder>{
    private ArrayList<CommentModel> listData=new ArrayList<>();
    @NonNull
    @Override
    public commentAdpater.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item,parent,false);
        return new commentAdpater.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull commentAdpater.ItemViewHolder holder, final int position) {
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
    public void addItem(CommentModel data) {
        listData.add(data);
    }
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private View mview;
        TextView title,id,postid,content,writer;
        ItemViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            title=itemView.findViewById(R.id.commenttitle);
            id=itemView.findViewById(R.id.commentid);
            postid=itemView.findViewById(R.id.commentpostid);
            content=itemView.findViewById(R.id.commentcontent);
            writer=itemView.findViewById(R.id.commentwriter);
        }
        @SuppressLint("SetTextI18n")
        void onBind(CommentModel data) {
            title.setText(data.getTitle());
            id.setText(String.valueOf(data.getId()));
            postid.setText(String.valueOf(data.getPostid()));
            content.setText(data.getContent());
            writer.setText(data.getWriter());
        }
    }
}
