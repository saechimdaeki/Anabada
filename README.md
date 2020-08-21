# Anabada
#### front-end :Android , backend:Spring  
#### 아껴쓰고 나눠쓰고 바꿔쓰고 다시쓰자

---
### 해당프로젝트의의 개발진행과정은 [블로그](http://blog.naver.com/PostSearchList.nhn?blogId=anima94&categoryNo=0&SearchText=anabada&orderBy=date&range=all)에서 볼 수 있으며 매일 개발 기록을 볼수있습니다.
##### `물건을 사거나 팔거나 바꿀수있는 프로젝트입니다.` 
#### `(프로젝트의 설명에 사용된 예시는 주제와 달리 연예인의 사진을 사용하였습니다)`

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
git clone https://github.com/saechimdaeki/Anabada.git
cd backend
./gradle build
cd build\libs
java -jar target/*.jar
ex)java -jar Anabada-0.0.1-SNAPSHOT.jar
```

### Once the above process is finished, you can launch the Android app in the frontend folder.:bee: 


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


```

```

### Let's look at some simple functions. (addpost, deletepost ,real-time comments , imageZoom ) 
#### ( ※ The image used is a project to buy and sell products, although it used pictures of celebrities. ※ )


![post](https://user-images.githubusercontent.com/40031858/90849825-28b3b480-e3ab-11ea-99a6-9f6680c71edf.gif)

![글삭제](https://user-images.githubusercontent.com/40031858/90849398-1b49fa80-e3aa-11ea-8016-72f275f5d8ec.gif)

![댓글달기](https://user-images.githubusercontent.com/40031858/90849438-3583d880-e3aa-11ea-8290-d76f1c029e9d.gif)



![댓글삭제](https://user-images.githubusercontent.com/40031858/90849113-81824d80-e3a9-11ea-8fa1-84064e84eae8.gif)


![루다이미지](https://user-images.githubusercontent.com/40031858/90849086-6c0d2380-e3a9-11ea-8267-63eb47d9ab19.gif)


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
