package springboot.juseong.anabada.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.Adapter.PostAdapter;
import springboot.juseong.anabada.Adapter.commentAdpater;
import springboot.juseong.anabada.Adapter.getFileAdpater;
import springboot.juseong.anabada.DataModel.CommentModel;
import springboot.juseong.anabada.DataModel.FileModel;
import springboot.juseong.anabada.DataModel.getFileModel;
import springboot.juseong.anabada.MainActivity;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;
import springboot.juseong.anabada.retrofitModel.Comment;
import springboot.juseong.anabada.retrofitModel.FileUrl;

public class getPostAcitivty extends AppCompatActivity {
    TextView gettitle,getcontent,gettype,getwriter,getprice;
    EditText editcomment;
    ImageView send;
    RecyclerView filerecycle;
    RecyclerView commentrecycle;
    ArrayList<String> datalist=new ArrayList<>();
    ArrayList<Long> idlist=new ArrayList<>();
    ArrayList<Long> postidlist=new ArrayList<>();
    RetrofitService retrofitService;
    long id; //pk
    LinearLayoutManager linearLayoutManager,commentManager;
    private getFileAdpater Adapter;
    private commentAdpater commentAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_post_acitivty);
        gettitle=findViewById(R.id.get_title);
        getcontent=findViewById(R.id.get_content);
        gettype=findViewById(R.id.get_type);
        getwriter=findViewById(R.id.get_writer);
        getprice=findViewById(R.id.get_price);
        editcomment=findViewById(R.id.edit_comment);
        send=findViewById(R.id.comment_post);
        filerecycle=findViewById(R.id.get_recycler_file);
        commentrecycle=findViewById(R.id.commentList);
        Intent intent=getIntent();
        id=intent.getLongExtra("id",1);
        gettitle.setText(intent.getStringExtra("title"));
        getwriter.setText(intent.getStringExtra("writer"));
        getcontent.setText(intent.getStringExtra("content"));
        getprice.setText(intent.getStringExtra("price"));
        gettype.setText(intent.getStringExtra("type"));
        linearLayoutManager = new LinearLayoutManager(this);
        filerecycle.setHasFixedSize(true);
        filerecycle.setLayoutManager(linearLayoutManager);
        Adapter=new getFileAdpater();
        filerecycle.setAdapter(Adapter);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        commentManager=new LinearLayoutManager(this);
        commentrecycle.setHasFixedSize(true);
        commentrecycle.setLayoutManager(commentManager);
        commentAdpater=new commentAdpater();
        commentrecycle.setAdapter(commentAdpater);
        getfilelist();
        getcommentlist();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commenthanging();
                editcomment.setText("");
            }
        });
    }

    private void getfilelist(){
        retrofitService=RetrofitFactory.create();
        retrofitService.findAllfileByPostid(id).enqueue(new Callback<List<FileUrl>>() {
            @Override
            public void onResponse(Call<List<FileUrl>> call, Response<List<FileUrl>> response) {
                List<FileUrl> model = response.body();
                if(model.size()==0)
                    Toast.makeText(getPostAcitivty.this, "사진을 첨부하는거 어떠세용", Toast.LENGTH_SHORT).show();
                else {
                    Log.e("뀨","모델크기"+model.size());
                    for (int i = 0; i < model.size(); i++) {
                        getFileModel data=new getFileModel();
                        datalist.add(model.get(i).getData());
                        idlist.add(model.get(i).getId());
                        postidlist.add(model.get(i).getPostid());

                        data.setData(datalist.get(i));
                        data.setId(idlist.get(i));
                        data.setPostid(postidlist.get(i));

                        Adapter.addItem(data);
                        Adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<FileUrl>> call, Throwable t) {
                Toast.makeText(getPostAcitivty.this, "실패다능 ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void commenthanging(){
        if(editcomment.getText().toString().equals(""))
            Toast.makeText(getPostAcitivty.this, "댓글을 입력하세요!!!!", Toast.LENGTH_SHORT).show();
        else {
            Comment comment = new Comment("", editcomment.getText().toString(), MainActivity.globalUsername, id);
            retrofitService.createComment(id, comment).enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    Toast.makeText(getPostAcitivty.this, "깨끗한 댓글을 달았습니다.^_^", Toast.LENGTH_SHORT).show();
                    getcommentlist();
                }
                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    Toast.makeText(getPostAcitivty.this, "댓글달기 실패", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void getcommentlist(){
        commentManager=new LinearLayoutManager(this);
        commentrecycle.setHasFixedSize(true);
        commentrecycle.setLayoutManager(commentManager);
        commentAdpater=new commentAdpater();
        commentrecycle.setAdapter(commentAdpater);
        retrofitService.getallcomment(id).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> model=response.body();
                if(model.size()==0)
                    Log.e("댓글수","댓글이없서용");
                else {
                    for (int i = 0; i < model.size(); i++){
                        CommentModel data=new CommentModel();
                        data.setId(model.get(i).getId());
                        data.setContent(model.get(i).getContent());
                        data.setPostid(model.get(i).getPostid());
                        data.setTitle(model.get(i).getTitle());
                        data.setWriter(model.get(i).getWriter());
                        commentAdpater.addItem(data);
                        commentAdpater.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }



}
