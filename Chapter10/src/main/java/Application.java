import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.course.server")
public class Application {

    public static void main(String[] args){
        SpringApplication.run ( Application.class,args );    //固定写法入口类

    }
}
