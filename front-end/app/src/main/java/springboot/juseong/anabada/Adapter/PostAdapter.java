package springboot.juseong.anabada.Adapter;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import springboot.juseong.anabada.DataModel.PostModel;
import springboot.juseong.anabada.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ItemViewHolder> {
    private ArrayList<PostModel> listData=new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_listitem,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    @Override
    public int getItemCount() {
        return listData.size();
    }
    public void addItem(PostModel data) {
        listData.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private View mview;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textViewPrice;
        private ImageView imageView;
        private TextView textView4;


        ItemViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView4=itemView.findViewById(R.id.textViewtype);
            textViewPrice=itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
        void onBind(PostModel data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            textView3.setText(String.valueOf(data.getId()));
            textViewPrice.append(data.getPrice()+"(원)");
            textView4.setText(data.getType());
            byte[] imageByte= Base64.decode(data.getThumbnailImage(),Base64.DEFAULT);
            Glide.with(itemView.getContext()).load(imageByte).thumbnail(Glide.with(itemView.getContext()).load(R.drawable.noimage))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .crossFade()
                    .into(imageView);
        }
    }
}
