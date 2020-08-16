package springboot.juseong.anabada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import springboot.juseong.anabada.screen.frag1;
import springboot.juseong.anabada.screen.frag2;
import springboot.juseong.anabada.screen.frag3;

public class MainActivity extends AppCompatActivity {
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
}
