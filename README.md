# Anabada
### front-end: Android , backend: Spring  
### A project that provides services to buy and sell unused used goods.
### 아껴쓰고 나눠쓰고 바꿔쓰고 다시쓰자

---
### 해당프로젝트의의 개발진행과정은 [블로그](http://blog.naver.com/PostSearchList.nhn?blogId=anima94&categoryNo=0&SearchText=anabada&orderBy=date&range=all)에서 볼 수 있으며 매일 개발 기록을 볼수있습니다.
#### `물건을 사거나 팔거나 바꿀수있는 프로젝트입니다.` 


### `How the frontend and backend exchange information`
![image](https://user-images.githubusercontent.com/40031858/90135086-18994500-ddad-11ea-8e44-236c88e6d5f9.png)


### Technology used
<details>
<summary>back-end</summary>
<img src=https://user-images.githubusercontent.com/40031858/90135416-952c2380-ddad-11ea-9760-b88e362cc882.png width=600px>
</details>
<details>
<summary>front-end</summary>
<img src=https://user-images.githubusercontent.com/40031858/90136028-8134f180-ddae-11ea-9122-f171f4505c8e.png width=600px>
</details>
</details>

---
## Running Anabada locally
Anabada is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Gradle](https://spring.io/guides/gs/gradle/). 

## The [h2](https://h2database.com/h2-2019-03-13.zip) database must be executed before performing the following command.
#### (JDBC URL: jdbc:h2:tcp://localhost/~/anabada , username : sa)

```
1. git clone https://github.com/saechimdaeki/Anabada.git
2. cd backend
3. ./gradle build
4. cd build\libs
5. java -jar target/*.jar
ex)java -jar Anabada-0.0.1-SNAPSHOT.jar
```

#### Once the above process is finished, you can launch the Android app in the frontend folder:bee:
```
1.open the Android Studio
2.open proejct front-end And gradle Sync
3. Goto RetrofitFactory.class And enter YourIPAddress with PortNumber

public class RetrofitFactory {
    private static String BASE_URL= "YourIpAdress!!!!!! ";
    public static RetrofitService create(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetrofitService.class);
    }

}
4. Launch App (h2 and spring must be in execution state.)

```


## backend directory structure
```
---src
    +---main
    |   +---generated
    |   +---java
    |   |   \---Anabada
    |   |       \---Anabada
    |   |           |   AnabadaApplication.java
    |   |           |
    |   |           +---controller
    |   |           |       CommentController.java
    |   |           |       FileController.java
    |   |           |       PostController.java
    |   |           |       RegistController.java
    |   |           |       TestController.java
    |   |           |
    |   |           +---domain
    |   |           |       Account.java
    |   |           |       AttachmentFile.java
    |   |           |       Comment.java
    |   |           |       FileUrl.java
    |   |           |       Member.java
    |   |           |       Post.java
    |   |           |
    |   |           +---dto
    |   |           |       AccountDto.java
    |   |           |       UploadFileResponse.java
    |   |           |
    |   |           +---exception
    |   |           |       FileStorageException.java
    |   |           |       MyFileNotFoundException.java
    |   |           |       PostNotFoundException.java
    |   |           |
    |   |           +---repository
    |   |           |       AccountRepository.java
    |   |           |       AttachmentFileRepository.java
    |   |           |       CommentRepository.java
    |   |           |       FileUriRepository.java
    |   |           |       PostRepository.java
    |   |           |
    |   |           +---secret
    |   |           |       Hashing.java
    |   |           |
    |   |           \---service
    |   |                   AccountService.java
    |   |                   AttachmentFileService.java
    |   |                   CommentService.java
    |   |                   FileUriService.java
    |   |                   PostService.java
    |   |                   PostServiceImpl.java
    |   |
    |   \---resources
    |       |   application.yml
    |       |
    |       +---static
    |       \---templates

```

## frontend directory structure
```
-main
|       |   |   AndroidManifest.xml
|       |   |
|       |   +---java
|       |   |   \---springboot
|       |   |       \---juseong
|       |   |           \---anabada
|       |   |               |   MainActivity.java
|       |   |               |   Secret.java
|       |   |               |
|       |   |               +---Adapter
|       |   |               |       commentAdpater.java
|       |   |               |       FileAdapter.java
|       |   |               |       getFileAdpater.java
|       |   |               |       PostAdapter.java
|       |   |               |
|       |   |               +---DataModel
|       |   |               |       CommentModel.java
|       |   |               |       FileModel.java
|       |   |               |       getFileModel.java
|       |   |               |       PostModel.java
|       |   |               |
|       |   |               +---retrofit2
|       |   |               |       RetrofitFactory.java
|       |   |               |       RetrofitService.java
|       |   |               |
|       |   |               +---retrofitModel
|       |   |               |       Account.java
|       |   |               |       Comment.java
|       |   |               |       FileUrl.java
|       |   |               |       Post.java
|       |   |               |
|       |   |               \---screen
|       |   |                   |   frag1.java
|       |   |                   |   frag2.java
|       |   |                   |   frag3.java
|       |   |                   |   getPostAcitivty.java
|       |   |                   |   Loading.java
|       |   |                   |   ZoomPicActivity.java
|       |   |                   |
|       |   |                   \---regist
|       |   |                           loginActivity.java

```

## Requirements

For building and running the application you need:

- [Java 8 or newer](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 6.3 or newer](https://docs.gradle.org/6.3/release-notes.html)
- [H2 1.4.199](http://www.h2database.com/html/download.html)

---




### Let's look at some simple functions. (addpost, deletepost ,real-time comments , imageZoom ) 



![1](https://user-images.githubusercontent.com/40031858/90912481-a2808800-e415-11ea-8bb6-bf53c9ad1937.gif)


![2](https://user-images.githubusercontent.com/40031858/90912525-b926df00-e415-11ea-85b7-07679e34110e.gif)


![3](https://user-images.githubusercontent.com/40031858/90912565-c93ebe80-e415-11ea-8aef-1aa22702303c.gif)




![4](https://user-images.githubusercontent.com/40031858/90912662-f3907c00-e415-11ea-9d98-908eecd889d2.gif)


![5](https://user-images.githubusercontent.com/40031858/90912667-f4291280-e415-11ea-8a16-ff4dae330119.gif)



#### Detailed functions can be viewed [here](http://blog.naver.com/PostSearchList.nhn?blogId=anima94&categoryNo=0&SearchText=anabada&orderBy=date&range=all) or directly through the program.

---
## 📝 License
This project is released under the MIT license.
See [LICENSE](./LICENSE) for details.

```
MIT License

Copyright (c) 2020 김준성

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
