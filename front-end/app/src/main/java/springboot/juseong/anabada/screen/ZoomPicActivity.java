package springboot.juseong.anabada.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import springboot.juseong.anabada.R;

public class ZoomPicActivity extends AppCompatActivity {
    CircleImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_pic);
        imageView=findViewById(R.id.circleimageviewzoom);
        Intent intent=getIntent();

        byte[] imageByte= Base64.decode(intent.getStringExtra("data"),Base64.DEFAULT);
        Glide.with(this).load(imageByte).error(R.drawable.noimage)
                .fitCenter()
                .crossFade()
                .into(imageView);
    }
}
