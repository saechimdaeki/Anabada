package springboot.juseong.anabada.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.DataModel.PostModel;
import springboot.juseong.anabada.MainActivity;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;
import springboot.juseong.anabada.screen.frag2;
import springboot.juseong.anabada.screen.getPostAcitivty;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ItemViewHolder> {
    private ArrayList<PostModel> listData=new ArrayList<>();
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_listitem,parent,false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.onBind(listData.get(position));
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context=view.getContext();
                final Intent intent;
                intent=new Intent(context, getPostAcitivty.class);
                intent.putExtra("title",holder.textView1.getText().toString());
                intent.putExtra("content",holder.textView2.getText().toString());
                intent.putExtra("id",Long.parseLong(holder.textView3.getText().toString()));
                intent.putExtra("price",holder.textViewPrice.getText().toString());
                intent.putExtra("writer",holder.textwriter.getText().toString());
                intent.putExtra("type",holder.textView4.getText().toString());
                context.startActivity(intent);
            }
        });
        holder.mview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final Context context=view.getContext();
                if(holder.textwriter.getText().toString().equals(MainActivity.globalUsername))
                {
                    new FancyGifDialog.Builder((Activity)context)
                            .setTitle("해당 포스트를 삭제할까요?")
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
                                    retrofitService.deletePost(Long.parseLong(holder.textView3.getText().toString())).enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Toast.makeText(context, "해당 포스트를 삭제합니다.", Toast.LENGTH_SHORT).show();
                                            Log.e("성공",call.request().url().toString());
                                            Intent refresh = new Intent(context, MainActivity.class);
                                            ((MainActivity)context).finish();
                                            context.startActivity(refresh);

                                          //  frag2.refreshFragment();

                                        }
                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
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
        private TextView textwriter;

        ItemViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
            textView4=itemView.findViewById(R.id.textViewtype);
            textViewPrice=itemView.findViewById(R.id.textViewPrice);
            textwriter=itemView.findViewById(R.id.textViewwriter);
            imageView = itemView.findViewById(R.id.imageView);
        }
        void onBind(PostModel data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            textView3.setText(String.valueOf(data.getId()));  /// pk
            textViewPrice.append(data.getPrice()+"(원)");
            textView4.setText(data.getType());
            textwriter.setText(data.getWriter());
            byte[] imageByte= Base64.decode(data.getThumbnailImage(),Base64.DEFAULT);

            Glide.with(itemView.getContext()).load(imageByte).thumbnail(Glide.with(itemView.getContext()).load(R.drawable.noimage))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .crossFade()
                    .into(imageView);
        }
    }
}
