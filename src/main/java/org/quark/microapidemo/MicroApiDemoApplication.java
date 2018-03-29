package org.quark.microapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableKafka
public class MicroApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroApiDemoApplication.class, args);
	}
}
