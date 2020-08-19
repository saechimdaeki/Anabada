package springboot.juseong.anabada;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;


import springboot.juseong.anabada.screen.Loading;
import springboot.juseong.anabada.screen.frag1;
import springboot.juseong.anabada.screen.frag2;
import springboot.juseong.anabada.screen.frag3;

public class MainActivity extends AppCompatActivity {
    public static String globalUsername="";
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private frag1 frag1;
    private frag2 frag2;
    private frag3 frag3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(MainActivity.this, Loading.class);
                            intent.putExtra("where",3000);
                            startActivity(intent);
                        }
                    });
                }
            }).start();
        }
        bottomNavigationView=findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemReselectedListener(
                new BottomNavigationView.OnNavigationItemReselectedListener() {
                    @Override
                    public void onNavigationItemReselected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_home:
                                frag1=new frag1();
                                setfrag(0);
                                break;
                            case R.id.action_write:
                                frag2=new frag2();
                                setfrag(1);
                                break;
                            case R.id.action_test:
                                frag3=new frag3();
                                setfrag(2);
                                break;
                        }
                    }
                }
        );
        frag1=new frag1();
        frag2=new frag2();
        setfrag(0);
    }
    public void setfrag(int n){
       fm=getSupportFragmentManager();
       ft=fm.beginTransaction();
       switch (n){
           case 0:
               frag1=new frag1();
               ft.replace(R.id.main_frame,frag1);
               ft.commit();
               break;
           case 1:
               frag2=new frag2();
               ft.replace(R.id.main_frame,frag2);
               ft.commit();
               break;
           case 2:
               frag3=new frag3();
               ft.replace(R.id.main_frame,frag3);
               ft.commit();
               break;
       }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onBackPressed() {
        new FancyGifDialog.Builder(this)
                .setTitle("아나바다를 종료하실래요??\n\n 아나바다 많이 이용해주세용")
                .setMessage("아나바다 많이 이용해주세용 ")
                .setNegativeBtnText("아니요")
                .setPositiveBtnBackground("#FF4081")
                .setPositiveBtnText("네 다음에 봐요")
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.cute)   //Pass your Gif here
                .isCancellable(true)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(MainActivity.this,"담에봐융",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(MainActivity.this,"취소하였습니다.",Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }


}
