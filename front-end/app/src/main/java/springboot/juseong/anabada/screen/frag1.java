package springboot.juseong.anabada.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.Adapter.PostAdapter;
import springboot.juseong.anabada.DataModel.PostModel;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.retrofitModel.Post;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;

/*홈화면 */
public class frag1 extends Fragment {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    List<String> titlelist=new ArrayList<>();
    List<String> contentlist=new ArrayList<>();
    List<String> datalist=new ArrayList<>();
    List<Long> idlist=new ArrayList<>();
    List<BigDecimal> pricelist=new ArrayList<>();

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
        recyclerView=view.findViewById(R.id.recycle_post);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter=new PostAdapter();
        recyclerView.setAdapter(postAdapter);

        RetrofitService retrofitService= RetrofitFactory.create();
        retrofitService.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> model=response.body();
                if(model.size()==0)
                {
                    text.setVisibility(View.VISIBLE);
                    text.append("글이 아무것도 존재하지 않습니다\n");
                }else{
                    for (int i = 0; i < model.size(); i++) {
                        PostModel data=new PostModel();
                        titlelist.add(model.get(i).getTitle());
                        contentlist.add(model.get(i).getContent());
                        if(model.get(i).getFiles().size()==0) {
                            datalist.add(String.valueOf(R.string.noimage));
                        }
                        else{
                            datalist.add(model.get(i).getFiles().get(0).getData()); //썸네일
                        }
                        pricelist.add(model.get(i).getPrice());
                        idlist.add(model.get(i).getId());
                        data.setTitle(titlelist.get(i));
                        data.setContent(contentlist.get(i));
                        data.setId(idlist.get(i));
                        data.setThumbnailImage(datalist.get(i));
                        data.setPrice(pricelist.get(i));
                        postAdapter.addItem(data);
                        postAdapter.notifyDataSetChanged();
                    }
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
