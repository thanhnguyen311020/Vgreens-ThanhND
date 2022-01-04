package edu.poly.vgreens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class VgreensApplication {

	public static void main(String[] args) {
		SpringApplication.run(VgreensApplication.class, args);
	}

}
