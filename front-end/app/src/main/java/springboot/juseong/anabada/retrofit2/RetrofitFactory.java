package springboot.juseong.anabada.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFactory {
    private static String BASE_URL= "YourIpAdress!!!!!! ";
    public static RetrofitService create(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetrofitService.class);
    }

}
