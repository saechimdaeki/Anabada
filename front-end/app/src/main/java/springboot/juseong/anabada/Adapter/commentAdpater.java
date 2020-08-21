package springboot.juseong.anabada.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.DataModel.CommentModel;

import springboot.juseong.anabada.MainActivity;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;
import springboot.juseong.anabada.screen.getPostAcitivty;

public class commentAdpater extends RecyclerView.Adapter<commentAdpater.ItemViewHolder>{
    private ArrayList<CommentModel> listData=new ArrayList<>();
    @NonNull
    @Override
    public commentAdpater.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item,parent,false);
        return new commentAdpater.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final commentAdpater.ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                final Intent intent;
            }
        });
        holder.mview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Context context=view.getContext();
                final Intent intent;
                if(holder.writer.getText().toString().equals(MainActivity.globalUsername))
                {
                    new FancyGifDialog.Builder((Activity)context)
                            .setTitle("해당 댓글을 삭제할까요?")
                            .setNegativeBtnText("아니요")
                            .setPositiveBtnBackground("#FF4081")
                            .setPositiveBtnText("네 삭제해주세요")
                            .setNegativeBtnBackground("#FFA9A7A8")
                            .setGifResource(R.drawable.sad)   //Pass your Gif here
                            .isCancellable(true)
                            .OnPositiveClicked(new FancyGifDialogListener() {
                                @Override
                                public void OnClick() {
                                    RetrofitService retrofitService= RetrofitFactory.create();
                                    retrofitService.deleteComment(Long.parseLong(holder.postid.getText().toString()),Long.parseLong(holder.id.getText().toString())).enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            //Toast.makeText(context, "댓글 삭제 성공", Toast.LENGTH_SHORT).show();
                                            ((getPostAcitivty)context).getcommentlist();
                                        }
                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            ((getPostAcitivty)context).getcommentlist();
                                        }
                                    });
                                }
                            })
                            .OnNegativeClicked(new FancyGifDialogListener() {
                                @Override
                                public void OnClick() {
                                }
                            })
                            .build();
                }
                return true;
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
