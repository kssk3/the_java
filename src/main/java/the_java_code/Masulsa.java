package the_java_code;

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
