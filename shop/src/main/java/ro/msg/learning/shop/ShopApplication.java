package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
		"ro.msg.learning.shop.Model",
		"ro.msg.learning.shop.Service",
		"ro.msg.learning.shop.Controller",
		"ro.msg.learning.shop.Persistence"
})
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"ro.msg.learning.shop.Persistence"})
@EntityScan("ro.msg.learning.shop.Model")
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
