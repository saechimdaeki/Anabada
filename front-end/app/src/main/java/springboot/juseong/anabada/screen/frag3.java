package springboot.juseong.anabada.screen;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.retrofitModel.FileUrl;
import springboot.juseong.anabada.retrofitModel.Post;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;

public class frag3 extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag3, container, false);
        final TextView text=view.findViewById(R.id.texttest);
        final ImageView imageView=view.findViewById(R.id.testimage);
        RetrofitService retrofitService= RetrofitFactory.create();
       retrofitService.getPostById(1).enqueue(new Callback<Post>() {
           @Override
           public void onResponse(Call<Post> call, Response<Post> response) {
               Post model=response.body();
               text.append(model.getComments().get(0).getTitle()+"\n");

           }

           @Override
           public void onFailure(Call<Post> call, Throwable t) {

           }
       });

       retrofitService.getAllFile(1L).enqueue(new Callback<List<FileUrl>>() {
           @Override
           public void onResponse(Call<List<FileUrl>> call, Response<List<FileUrl>> response) {

               Log.e("성공",call.request().url().toString());
               List<FileUrl> model=response.body();
               text.append(model.get(0).getFileName()+"\n");
                byte[] imageByte=Base64.decode(model.get(0).getData(),Base64.DEFAULT);
                Glide.with(getActivity()).load(imageByte).thumbnail(Glide.with(getActivity()).load(R.drawable.load))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .crossFade()
                        .into(imageView);
              // InputStream is=new ByteArrayInputStream(imageByte);
               // Bitmap bitmap=BitmapFactory.decodeStream(is);
                //imageView.setImageBitmap(bitmap);
           }
           @Override
           public void onFailure(Call<List<FileUrl>> call, Throwable t) {
               Log.e("오류",call.request().url().toString());
           }
       });

        return view;
    }

}
