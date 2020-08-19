package springboot.juseong.anabada.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import de.hdodenhof.circleimageview.CircleImageView;
import springboot.juseong.anabada.R;

public class Loading extends AppCompatActivity {
    int time;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        image=findViewById(R.id.loadingimage);
        time=getIntent().getIntExtra("where",1000);
        Glide.with(this).load(R.drawable.cuteloading).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(image);
        StartLoading();
    }
    private void StartLoading(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, time);
    }

}
