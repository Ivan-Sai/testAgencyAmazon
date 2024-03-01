package test.testamazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestAmazonApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestAmazonApplication.class, args);
    }

}
