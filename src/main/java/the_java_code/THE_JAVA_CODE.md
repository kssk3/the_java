# 1. JVM 이해하기

## 자바, JVM, JRE, JDK
  
![](https://media.geeksforgeeks.org/wp-content/uploads/20210218150010/JDK.png)  
출처 : https://media.geeksforgeeks.org/wp-content/uploads/20210218150010/JDK.png  
  
### JVM
- 자바 가상 머신으로 자바 바이트 코드(.class 파일)를 OS에 특화된 코드로 변환  
  (인터프리터와 JIT 컴파일러)하여 실행한다.
- 바이크 코드를 실행하는 표준(JVM 자체는 표준)이자 구현체(특정 벤더가 구현한 JVM)다.
- JVM 스팩 : https://docs.oracle.com/javase/specs/jvms/se11/html/  
- JVM 밴더 : 오라클, 아마존, Azul, ...
- 특정 플랫폼에 종속적
  
### JRE
- 자바 애플리케이션을 실행할 수 있도록 구성된 배포판
- JVM과 핵심 라이브러리 및 자바 런타임 환경에서 사용하는 프로퍼티 세팅이나 리소스 파일을 가지고 있다.  
- 개발 관련 도구는 포함하지 않는다. (JDK에서 제공)  
  
### JDK
- JRE + 개발에 필요한 툴
- 소스 코드를 작성할 때 사용하는 자바 언어는 플랫폼에 독립적
- 오라클은 자바 11부터는 JDK만 제공하며 JRE를 따로 제공하지 않는다.  
- Write Once Run Anywhere
  
### 자바
- 프로그래밍 언어
- JDK에 들어있는 자바 컴파일러(javac)를 사용하여 바이트코드(.class 파일)로 컴파일 할 수 있다.
- 자바 유료화? 오라클에서 만든 Oracle JDK 11 버전부터 상용으로 사용할 때 유료.  
  https://medium.com/@javachampions/java-is-still-free-c02aef8c9e04  
  
### JVM 언어
- JVM 기반으로 동작하는 프로그래밍 언어
- 클로저, 그루비, JRuby, Jython, Kotlin, Scala 
  
### 참고
- JIT 컴파일러 : https://aboullaite.me/understanding-jit-compiler-just-in-time-compiler/  
- JDK, JRE, 그리고 JVM : https://howtodoinjava.com/java/basics/jdk-jre-jvm/    
- https://en.wikipedia.org/wiki/List_of_JVM_languages  

참고 사이트 : https://www.geeksforgeeks.org/differences-jdk-jre-jvm/  
  
## 2. JVM 구조

![](https://media.geeksforgeeks.org/wp-content/uploads/20250328171009480921/jvm.webp)  
출처 : https://media.geeksforgeeks.org/wp-content/uploads/20250328171009480921/jvm.webp  
  
**클래스 로더 시스템**  
- .class 에서 바이트코드를 읽고 메모리에 저장
- 로딩 : 클래스에서 읽어오는 과정   
- 링크 : 레퍼런스를 연결하는 과정
- 초기화 : static 값들 초기화 및 변수에 할당
  
**메모리**  
- 메소드 영역에는 클래스 수준의 정보 (클래스 이름, 부모 클래스 이름, 메소드, 변수) 저장 공유 자원이다.
- 힙 영역에는 객체를 저장, 공유 자원이다.
- 스택 영역에는 쓰레드 마다 런타임 스택을 만들고, 그 안에 메소드 호출을 스택 프레임이라 부르는 블럭으로 쌓는다.  
쓰레드 종료하면 런타임 스택도 사라진다.  
- PC(Program Counter) 레지스터 : 쓰레드 마다 쓰레드 내 현재 실행할 스택 프레임을 가리키는 포인터가 생성된다.  
- 네이티브 메소드 스택 : https://javapapers.com/core-java/java-jvm-run-time-data-areas/#Program_Counter_PC_Register  
  
**실행 엔진**  
- 인터프리터 : 바이트 코드를 한줄 씩 실행
- JIT 컴파일러 : 인터프리터 효율을 높이기 위해, 인터프리터가 반복되는 코드를 발견하면  
JIT 컴파일러로 반복되는 코드를 모두 네이티브 코드로 바꿔둔다.  
그 다음부터 인터프리터는 네이티브 코드로 컴파일된 코드를 바로 사용한다.  
- GC (Garbage Collector) : 더 이상 참조되지 않는 객체를 모아서 정리한다.  
  
**JNI(Java Native Interface)**  
- 자바 애플리케이션에서 C, C++, 어셈블리로 작성된 함수를 사용할 수 있는 방법 제공
- Native 키워드를 사용한 메소드 호출
- https://schlining.medium.com/a-simple-java-native-interface-jni-example-in-java-and-scala-68fdafe76f5f  
  
**네이티브 메소드 라이브러리**  
- C, C++로 작성된 라이브러리
  
참고 사이트   
- https://www.geeksforgeeks.org/differences-jdk-jre-jvm/
- https://inpa.tistory.com/entry/JAVA-%E2%98%95-JVM-%EB%82%B4%EB%B6%80-%EA%B5%AC%EC%A1%B0-%EB%A9%94%EB%AA%A8%EB%A6%AC-%EC%98%81%EC%97%AD-%EC%8B%AC%ED%99%94%ED%8E%B8#%EB%84%A4%EC%9D%B4%ED%8B%B0%EB%B8%8C_%EB%A9%94%EC%84%9C%EB%93%9C_%EC%8A%A4%ED%83%9D_native_method_stack  
  
## 3. 클래스 로더 
  
![](https://media.geeksforgeeks.org/wp-content/uploads/jvmclassloader.jpg)  
출처 : https://media.geeksforgeeks.org/wp-content/uploads/jvmclassloader.jpg  
  
**클래스 로더**  
- 로딩, 링크, 초기화 순으로 진행된다. 
- 로딩  
  - 클래스 로더가 .class 파일을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들고 "메소드" 영역에 저장  
  - 이때 메소드 영역에 저장하는 데이터
    - **FQCN** : (Full Qualified Class Name)
    - Class | Interface | Enum
    - 메소드와 변수
  - 로딩이 끝나면 해당 클래스 타입의 Class 객체를 생성하여 "힙" 영역에 저장 
- 링크 
  - Verify, Prepare, Resolve(Optional) 세 단계로 나눠져 있다.
  - 검증 : .class 파일 형식이 유효한지 체크한다.
  - Preparation : 클래스 변수 (static 변수)와 기본값에 필요한 메모리
  - Resolve : 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체한다.  
- 초기화  
  - static 변수의 값을 할당한다. (static 블럭이 있다민 이때 실행된다.)
- 클래스 로더는 계층 구조로 이뤄져 있으면 기본적으로 세가지 클래스 로더가 제공된다.  
  - 부트 스트랩 클래스로더 - JAVA-HOME\lib에 있는 코어 자바 API를 제공한다. 최상위 우선순위를 가진 클래스 로더 
  - 플랫폼 클래스로더 - JAVA-HOME\lib\ext 폴더 또는 java.ext.dirs 시스템 변수에 해당하는 위치에 있는 클래스를 읽는다.
  - 애플리 케이션 클래스로더 - 애플리케이션 클래스패스 (애플리케이션을 실행할 때 주는 -classpath 옵션 또는 java.class.path  
환경 변수의 값에 해당하는 위치) 에서 클래스를 읽는다.  
  
  
# 2. 바이트 코드 조작
  
### 4. 코드 커버리지는 어떻게 측정할까?  
  
코드커버리지란 테스트 코드가 확인한 소스 코드를 %로 보여주는 API 다.
- 대표적으로 JaCoCo를 써보자.
- https://www.eclemma.org/jacoco/trunk/doc/index.html
- http://www.semdesigns.com/Company/Publications/TestCoverage.pdf
  
pom.xml에 플러그인 추가  
```
<build>
        <plugins>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.8</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```  
  
메이븐 빌드 
```
mvc clean verify
```

커버리지 만족 못할시 빌드 실패하도록 설정  
```
<execution>
  <id>jacoco-check</id>
      <goals>
        <goal>check</goal>
      </goals>
    <configuration>
  <rules>
<rule>
  <element>PACKAGE</element> 
    <limits>
      <limit>
        <counter>LINE</counter> 
        <value>COVEREDRATIO</value> 
        <minimum>0.50</minimum>
    </limit> </limits>
  </rule> </rules>
</configuration> </execution>
```
  
### 5. 모자에서 토끼를 꺼내는 마술
아무것도 없는 Moja에서 "Rabbit"을 꺼내는 마술  
  
- Moja.java  
```java
public class Moja {
    public String pullOut() {
        return "";
    }
}
```  
- Masulsa.java
```java
public class Masulsa {
    public static void main(String[] args) {
        System.out.println(new Moja().pullOut());
    }
}
```
  
바이트코드 조작 라이브러리  
- **ASM** : https://asm.ow2.io  
예전부터 사용하던 고전적 방식으로 사용된 라이브러리다. 사용하기 위해선 코드에 대한 깊이가 요구 된다.
- **Javassist** : https://www.javassist.org/  
- **ByteBuddy** : https://bytebuddy.net/#/  
최신 라이브러리로 사용하기가 편리하다.  
  
```java
import static net.bytebuddy.matcher.ElementMatchers.named;
import java.io.File;
import java.io.IOException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

public class Masulsa {

    public static void main(String[] args) {
        try {
            new ByteBuddy().redefine(Moja.class)
                    .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
                    .make().saveIn(new File("/Users/kssk3-/Desktop/study/the_java/target/classes/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(new Moja().pullOut());
    }
}
```  
- ByteBuddy를 사용하여 바이트코드를 조작해 본다. 
- 위에 방식이 제대로 동작하기 위해서는 `System.out.println(new Moja().pullOut())`의 코드 new Moja()가 먼저 컴파일이 읽으면 동작하지 않는다.  
- Moja.class 컴파일된 절대 경로를 `new File("/Users/kssk3-/Desktop/study/the_java/target/classes/")`로 읽어온다.
- 이 동작은 클래스로더에서 Moja.class를 읽어 오는 과정에서 ByteBuddy가 `method(named("pullOut"))` 메소드를 확인 후 같은 이름의 메서드가 있으면  
값 `(Rabbit!)`을 넣어 놓는다.
- 그래서 Moja.java 파일에는 빈 문자열이 있어도 클래스로더에서 Rabbit! 문자열이 이미 삽입 되어 있어 정상적으로 출력된다.  
  
```java
import static net.bytebuddy.matcher.ElementMatchers.named;
import java.io.File;
import java.io.IOException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

public class Masulsa {
    public static void main(String[] args) {
//        try {
//            new ByteBuddy().redefine(Moja.class)
//                    .method(named("pullOut")).intercept(FixedValue.value("Rabbit!"))
//                    .make().saveIn(new File("/Users/kssk3-/Desktop/study/the_java/target/classes/"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(new Moja().pullOut());
    }
}

Rabbit!
```


  

