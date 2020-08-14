package springboot.juseong.anabada.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.dto.Post;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;

/*홈화면 */
public class frag1 extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        final TextView text=view.findViewById(R.id.textPost);
        RetrofitService retrofitService= RetrofitFactory.create();
        retrofitService.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> model=response.body();
                for(int i=0; i<model.size(); i++)
                {
                    text.append("Id: "+model.get(i).getId()+"\n");
                    text.append("title: "+model.get(i).getTitle()+"\n");
                    text.append("content: "+model.get(i).getContent()+"\n");
                    text.append("price: "+model.get(i).getPrice()+"\n");
                    text.append("writer: "+model.get(i).getWriter()+"\n");
                    text.append("-----------------------------------\n");
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("오류",call.request().url().toString());
            }
        });
        return view;

    }
}
