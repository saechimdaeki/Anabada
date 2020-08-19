package springboot.juseong.anabada.retrofit2;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import springboot.juseong.anabada.retrofitModel.Account;
import springboot.juseong.anabada.retrofitModel.FileUrl;
import springboot.juseong.anabada.retrofitModel.Post;

public interface RetrofitService {
    @GET("/post")
    Call<List<Post>> getAllPosts();

    @POST("/post")
    Call<Post> createPost(@Body Post post);

    @GET("/post/{postid}")
    Call<Post> getPostById(@Path("postid") long id);

    @GET("/post/{postid}/download")
    Call<List<FileUrl>> getAllFile(@Path("postid") Long postid);

    @GET("/post/")
    Call<List<Post>> getAllPostByType(
            @Query("type") String type
    );

    @GET("/post/title")
    Call<Post> getPostByTitle(
            @Query("title") String title
    );

    @POST("/register")
    Call<ResponseBody> createAccount(@Body Account account);

    @POST("/login")
    Call<ResponseBody> loginAccount(@Body Account account);

    @Multipart
    @POST("/post/{postid}/upload")
    Call<FileUrl> uploadfiles (@Path("postid") long id,@Part MultipartBody.Part file);

    @Multipart
    @POST("/post/{id}/uploadfiles")
    Call<List<FileUrl>> uploadmultifiles (@Path("postid") long id,@Part MultipartBody.Part... file);
}
