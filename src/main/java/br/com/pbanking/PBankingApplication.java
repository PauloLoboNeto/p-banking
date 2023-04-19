package br.com.pbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class PBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PBankingApplication.class, args);
	}

}
