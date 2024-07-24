package site.brucewu.aha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class AhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AhaApplication.class, args);
	}

}
