# Anabada
#### front-end :Android , backend:Spring  
#### 아껴쓰고 나눠쓰고 바꿔쓰고 다시쓰자

---
### 해당프로젝트의의 개발진행과정은 [블로그](http://blog.naver.com/PostSearchList.nhn?blogId=anima94&categoryNo=0&SearchText=anabada&orderBy=date&range=all)에서 볼 수 있으며 매일 개발 기록을 볼수있습니다.


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
## Running petclinic locally
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

## Requirements

For building and running the application you need:

- [Java 8 or newer](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 6.3 or newer](https://docs.gradle.org/6.3/release-notes.html)
- [H2 1.4.199](http://www.h2database.com/html/download.html)

---

### Let's look at some simple functions. (deletepost and real-time comments)
![image](https://postfiles.pstatic.net/MjAyMDA4MjBfMTYz/MDAxNTk3OTE4MTg1NDQy.ur93Lqf725E2gNP5dqphBHOzAWNE8ly0yoIqynl6WKEg.gvejUs5EPUUUfzPYeuwxoTeHVFblb_tbcbBW5yN5JxYg.GIF.anima94/포스팅삭제.gif)
![image](https://postfiles.pstatic.net/MjAyMDA4MjBfMTU0/MDAxNTk3OTE4NTgzMDQ5.EV4jGOydbj99qga3Z18SI8i8VxgPYI2g8grgsqDSZEkg.t_HXQv8Yj36rHogwnmNXgDe7a2GE-6QHAmONc9yVb0gg.GIF.anima94/댓글.gif)

#### Detailed functions can be viewed [here](http://blog.naver.com/PostSearchList.nhn?blogId=anima94&categoryNo=0&SearchText=anabada&orderBy=date&range=all) or directly through the program.


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