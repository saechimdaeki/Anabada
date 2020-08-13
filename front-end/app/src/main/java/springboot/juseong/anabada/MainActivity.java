package springboot.juseong.anabada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.dto.Post;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=findViewById(R.id.text123);
        RetrofitService retrofitService= RetrofitFactory.create();
        retrofitService.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> model=response.body();
                for(int i=0; i<model.size(); i++)
                {
                    textView.append("Id: "+model.get(i).getId()+"\n");
                    textView.append("title: "+model.get(i).getTitle()+"\n");
                    textView.append("content: "+model.get(i).getContent()+"\n");
                    textView.append("price: "+model.get(i).getPrice()+"\n");
                    textView.append("writer: "+model.get(i).getWriter()+"\n");
                    textView.append("-----------------------------------\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("오류",call.request().url().toString());

            }
        });
    }
}
