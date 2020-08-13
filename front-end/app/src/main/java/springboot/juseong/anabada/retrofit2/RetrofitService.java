package springboot.juseong.anabada.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import springboot.juseong.anabada.dto.Post;

public interface RetrofitService {
    @GET("/post")
    Call<List<Post>> getAllPosts();
}
