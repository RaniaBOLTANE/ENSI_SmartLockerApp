package ensi.smartlocker.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.ensi.smartlocker.domain" })
@EnableJpaRepositories(basePackages = { "com.ensi.smartlocker.repositories" })
@ComponentScan({ "com.ensi.smartlocker.controllers", "com.ensi.smartlocker.services" })
public class SmartLockerAppDarkTApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartLockerAppDarkTApplication.class, args);
	}

}
