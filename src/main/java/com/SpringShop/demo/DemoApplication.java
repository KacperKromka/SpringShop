package com.SpringShop.demo;

import com.SpringShop.demo.Entity.Design;
import com.SpringShop.demo.Data.DesignRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(DesignRepository repository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repository.save(new Design(1, "czarne kropki", Design.Type.BLACKWHITE));
				repository.save(new Design(2, "czarne paski", Design.Type.BLACKWHITE));
				repository.save(new Design(3, "kolorowe  paski", Design.Type.COLOR));
				repository.save(new Design(4, "kolorowe kropki", Design.Type.COLOR));
			}
		};
	}
}
