package springboot.juseong.anabada.screen.regist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import springboot.juseong.anabada.MainActivity;
import springboot.juseong.anabada.R;
import springboot.juseong.anabada.retrofit2.RetrofitFactory;
import springboot.juseong.anabada.retrofit2.RetrofitService;
import springboot.juseong.anabada.retrofitModel.Account;

public class loginActivity extends AppCompatActivity {
    Button login,sign;
    EditText emailText,usernameText,passwordText;
    MaterialCheckBox checkBox;
    RetrofitService retrofitService;
    String emailauto,usernameauto,pwdauto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        sign=findViewById(R.id.signin);
        checkBox=findViewById(R.id.autologin);
        emailText=findViewById(R.id.email);
        usernameText=findViewById(R.id.username);
        passwordText=findViewById(R.id.password);
        retrofitService= RetrofitFactory.create();
        SharedPreferences auto=getSharedPreferences("auto", Activity.MODE_PRIVATE);
        emailauto=auto.getString("inputemail",null);
        usernameauto=auto.getString("inputuser",null);
        pwdauto=auto.getString("inputpwd",null);
        if(emailauto!=null && usernameauto!=null && pwdauto !=null)
        {
            login(emailauto,usernameauto,pwdauto);
        }
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siginin();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(emailText.getText().toString(),usernameText.getText().toString(),passwordText.getText().toString());
            }
        });
    }
    private void siginin(){
        if(emailText.getText().toString().equals("")||usernameText.getText().toString().equals("")||passwordText.getText().toString().equals(""))
            Toast.makeText(loginActivity.this, "누락된 요소가 있다능!", Toast.LENGTH_SHORT).show();
        else{
            Account account=new Account(usernameText.getText().toString(),emailText.getText().toString(),passwordText.getText().toString());
            retrofitService.createAccount(account).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String model= null;
                    try {
                        if(response.body()!=null)
                        model = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(model.equals("성공"))
                        Toast.makeText(loginActivity.this, "회원가입 성공 이제로그인해봅시다", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(loginActivity.this, "이미 등록되거나 개발자가 서버를 내려서 문제가있습니다", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("회원가입오류 요청 request:",call.request().url().toString());
                }
            });
        }
    }
    private void login(final String email, final String username, final String pwd){
        if(email.equals("")||username.equals("")||pwd.equals(""))
            Toast.makeText(loginActivity.this, "누락된 요소가 있다능!", Toast.LENGTH_SHORT).show();
        else{
            Account account=new Account(username,email,pwd);
            retrofitService.loginAccount(account).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String model= null;
                    try {
                        if(response.body()!=null)
                        model = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(model!=null) {
                        if (model.equals("성공")) {
                            if (checkBox.isChecked()) {
                                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor autoLogin = auto.edit();
                                autoLogin.putString("inputemail", email);
                                autoLogin.putString("inputuser", username);
                                autoLogin.putString("inputpwd", pwd);
                                autoLogin.apply();
                            }
                            MainActivity.globalUsername = username;
                            Toast.makeText(loginActivity.this, "아나바다에 오신것을 환영합니다" + username + "님", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(loginActivity.this, "저런 로그인 실팹니다!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(loginActivity.this, "저런 통신상태 불량잉에용", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

