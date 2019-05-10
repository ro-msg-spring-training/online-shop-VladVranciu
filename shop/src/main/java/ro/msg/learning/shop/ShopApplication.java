package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}
//scanBasePackages = {
//		"ro.msg.learning.shop.model",
//		"ro.msg.learning.shop.service",
//		"ro.msg.learning.shop.controller",
//		"ro.msg.learning.shop.repository"
//})
)
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
