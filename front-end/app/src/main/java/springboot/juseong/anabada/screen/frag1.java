package springboot.juseong.anabada.screen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
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
    List<String> typelist=new ArrayList<>();
    List<String> writerlist=new ArrayList<>();
    TextView text,spinnertext;
    EditText editText;
    ImageView search;
    LinearLayoutManager linearLayoutManager;
    RetrofitService retrofitService;
    AppCompatSpinner spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        text=view.findViewById(R.id.textPost);
        editText=view.findViewById(R.id.edit_search);
        recyclerView=view.findViewById(R.id.recycle_post);
        search=view.findViewById(R.id.post_search);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter=new PostAdapter();
        spinnertext=view.findViewById(R.id.textspinner);
        recyclerView.setAdapter(postAdapter);
        retrofitService= RetrofitFactory.create();
        spinner=view.findViewById(R.id.post_spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnertext.setText((CharSequence) adapterView.getItemAtPosition(i));
                if(spinnertext.getText().toString().equals("전체"))
                    getAllPost();
                else
                gettype();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i==EditorInfo.IME_ACTION_DONE)
                    clickSerach();
                return false;
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clickSerach();
            }
        });
        return view;
    }

    private void getAllPost(){
        linearLayoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter=new PostAdapter();
        recyclerView.setAdapter(postAdapter);
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
                        writerlist.add(model.get(i).getWriter());
                        pricelist.add(model.get(i).getPrice());
                        idlist.add(model.get(i).getId());
                        typelist.add(model.get(i).getType());

                        data.setTitle(titlelist.get(i));
                        data.setContent(contentlist.get(i));
                        data.setId(idlist.get(i));
                        data.setType(typelist.get(i));
                        data.setThumbnailImage(datalist.get(i));
                        data.setPrice(pricelist.get(i));
                        data.setWriter(writerlist.get(i));
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
    }


    private void clickSerach(){
        linearLayoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter=new PostAdapter();
        recyclerView.setAdapter(postAdapter);
        InputMethodManager mInputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        if(editText.getText().toString().equals(""))
            Toast.makeText(getActivity(), "검색어를 입력해주세요!", Toast.LENGTH_SHORT).show();
        else{
            linearLayoutManager =new LinearLayoutManager(getActivity());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            postAdapter=new PostAdapter();
            recyclerView.setAdapter(postAdapter);
            retrofitService.getPostByTitle(editText.getText().toString()).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    Post model=response.body();
                    PostModel data=new PostModel();
                    data.setContent(model.getContent());
                    data.setTitle(model.getTitle());
                    data.setType(model.getType());
                    data.setPrice(model.getPrice());
                    data.setWriter(model.getWriter());
                    if(model.getFiles().size()==0) {
                        data.setThumbnailImage(String.valueOf(R.string.noimage));
                    }
                    else{
                        data.setThumbnailImage(model.getFiles().get(0).getData()); //썸네일
                    }
                    data.setId(model.getId());
                    postAdapter.addItem(data);
                    postAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Toast.makeText(getActivity(), "해당 게시글이 없어요요요요!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void gettype(){
            linearLayoutManager =new LinearLayoutManager(getActivity());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            postAdapter=new PostAdapter();
            recyclerView.setAdapter(postAdapter);
            retrofitService.getAllPostByType(spinnertext.getText().toString()).enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> model=response.body();
                    List<String> titlesearch=new ArrayList<>();
                    List<String> contensearch=new ArrayList<>();
                    List<String> datasearch=new ArrayList<>();
                    List<Long> idsearch=new ArrayList<>();
                    List<BigDecimal> pricesearch=new ArrayList<>();
                    List<String> typesearch=new ArrayList<>();
                    List<String> writersearch=new ArrayList<>();
                    if(model.size()==0){
                        Toast.makeText(getActivity(), "검색에 대한 정보가 1도없습니다!", Toast.LENGTH_SHORT).show();
                    }else{
                        for (int i = 0; i < model.size(); i++) {
                            PostModel data=new PostModel();
                            titlesearch.add(model.get(i).getTitle());
                            contensearch.add(model.get(i).getContent());
                            if(model.get(i).getFiles().size()==0) {
                                datasearch.add(String.valueOf(R.string.noimage));
                            }
                            else{
                                datasearch.add(model.get(i).getFiles().get(0).getData()); //썸네일
                            }
                            writersearch.add(model.get(i).getWriter());
                            pricesearch.add(model.get(i).getPrice());
                            idsearch.add(model.get(i).getId());
                            typesearch.add(model.get(i).getType());
                            data.setTitle(titlesearch.get(i));
                            data.setContent(contensearch.get(i));
                            data.setId(idsearch.get(i));
                            data.setWriter(writersearch.get(i));
                            data.setType(typesearch.get(i));
                            data.setThumbnailImage(datasearch.get(i));
                            data.setPrice(pricesearch.get(i));
                            postAdapter.addItem(data);
                            postAdapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
        }

    @Override
    public void onResume() {
        super.onResume();

    }
}
