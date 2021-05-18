package mx.com.engen.activedirectoryws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties
public class ActiveDirectoryWsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ActiveDirectoryWsApplication.class, args);
  }

}
