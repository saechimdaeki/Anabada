package springboot.juseong.anabada.screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.Adapter.FileAdapter;
import springboot.juseong.anabada.DataModel.FileModel;
import springboot.juseong.anabada.MainActivity;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.retrofitModel.FileUrl;
import springboot.juseong.anabada.retrofitModel.Post;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;

/* 글쓰기화면 */
public class frag2 extends Fragment {
    int count=0;
    long postid;
    Toolbar toolbar;
    FileAdapter fileAdapter;
    AppCompatSpinner spinner;
    ImageView test;
    String category;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    CircleImageView photoimage;
    FileModel model=new FileModel();
    ArrayList<Bitmap> bitmaps=new ArrayList<>();
    Post post;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2, container, false);
        final EditText editTitle,editPrice,editContent;
        test=view.findViewById(R.id.test);
        TextView textwrite=view.findViewById(R.id.toolbar_write); //완료.
        toolbar=view.findViewById(R.id.write_toolbar);
        spinner=view.findViewById(R.id.write_spinner);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        editTitle=view.findViewById(R.id.edit_title);
        editPrice=view.findViewById(R.id.eidt_price);
        recyclerView=view.findViewById(R.id.recycler_file);
        editContent=view.findViewById(R.id.edit_content);
        photoimage=view.findViewById(R.id.write_post_gallery);
        /* ========================================================   */
        photoimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotravel();
            }
        });
        fileAdapter=new FileAdapter();
        linearLayoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(fileAdapter);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        /*
        FileModel data=new FileModel();
        data.setData(String.valueOf(R.string.noimage));
        fileAdapter.addItem(data);
        fileAdapter.notifyDataSetChanged();
         */
        textwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTitle.getText().toString().matches("")|| editContent.getText().toString().matches("")||editPrice.getText().toString().matches(""))
                    Toast.makeText(getActivity(), "값을 입력해주세요!!", Toast.LENGTH_SHORT).show();
                else
                createPoST(editTitle.getText().toString(), editContent.getText().toString(), editPrice.getText().toString(),MainActivity.globalUsername);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category= spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return view;
    }
    public void createPoST(String title, String content,String price, String writer){
        BigDecimal big;
        post=new Post(title,content,big=new BigDecimal(Integer.parseInt(price)),writer,category);
        RetrofitService retrofitService= RetrofitFactory.create();
        Log.e("값 뭐들어가는지 확인", "타이틀: "+title);
        retrofitService.createPost(post).enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post model=response.body();
                postid=model.getId();
                nextisFilelist();
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("글쓰기 오류",call.request().url().toString());
            }
        });
    }
    public void gotravel(){
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.putExtra("crop", "true");
        i.putExtra("aspectX", 100);
        i.putExtra("aspectY", 100);
        i.putExtra("outputX", 256);
        i.putExtra("outputY", 356);
        try {
            i.putExtra("return-data", true);
            startActivityForResult(
                    Intent.createChooser(i, "Select Picture"), 0);
        }catch (ActivityNotFoundException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode == Activity.RESULT_OK){
            try {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");
                bitmaps.add(bitmap);
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes=stream.toByteArray();
                additem(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void additem(byte[] data){
        model=new FileModel();
        model.setData(data);
        fileAdapter.addItem(model);
        fileAdapter.notifyDataSetChanged();
    }
    public void nextisFilelist(){
        for(int i=0; i<bitmaps.size(); i++) {
            File file = new File(saveBitmapToJpeg(getActivity(), bitmaps.get(i), "file",count++));
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            RetrofitService retrofitService = RetrofitFactory.create();
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            retrofitService.uploadfiles(postid,body).enqueue(new Callback<FileUrl>() {
                @Override
                public void onResponse(Call<FileUrl> call, Response<FileUrl> response) {
                    Log.e("성공",call.request().url().toString());
                }
                @Override
                public void onFailure(Call<FileUrl> call, Throwable t) {
                    Log.e("오류",call.request().url().toString());
                }
            });

        }
        ((MainActivity)getActivity()).setfrag(0);
    }
    public static String saveBitmapToJpeg(Context context, Bitmap bitmap, String name,int count){
        File storage = context.getCacheDir();
        String fileName = name + count+".jpg";
        File tempFile = new File(storage,fileName);
        try{
            tempFile.createNewFile();
            FileOutputStream out = new FileOutputStream(tempFile);
           bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile.getAbsolutePath();
    }



}
