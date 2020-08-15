package springboot.juseong.anabada.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.MainActivity;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.dto.Post;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;

/* 글쓰기화면 */
public class frag2 extends Fragment {
    RetrofitService retrofitService;
    Toolbar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2, container, false);
        final EditText editTitle,editWriter,editPrice,editContent;
        TextView textwrite=view.findViewById(R.id.toolbar_write); //완료.
        toolbar=view.findViewById(R.id.write_toolbar);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        editTitle=view.findViewById(R.id.edit_title);

        editWriter=view.findViewById(R.id.edit_writer);
        editPrice=view.findViewById(R.id.eidt_price);
        editContent=view.findViewById(R.id.edit_content);
        textwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTitle.getText().toString().matches("")|| editContent.getText().toString().matches("")||editPrice.getText().toString().matches("")||editWriter.getText().toString().matches(""))
                    Toast.makeText(getActivity(), "값을 입력해주세요!!", Toast.LENGTH_SHORT).show();
                else
                createPoST(editTitle.getText().toString(), editContent.getText().toString(), editPrice.getText().toString(),editWriter.getText().toString());
            }
        });


        return view;
    }
    public void createPoST(String title, String content,String price, String writer){
        BigDecimal big;
        Post post=new Post(title,content,big=new BigDecimal(Integer.parseInt(price)),writer);
        RetrofitService retrofitService= RetrofitFactory.create();
        Log.e("값 뭐들어가는지 확인", "타이틀: "+title);
        retrofitService.createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                ((MainActivity)getActivity()).setfrag(0);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("글쓰기 오류",call.request().url().toString());
            }
        });
    }


}
