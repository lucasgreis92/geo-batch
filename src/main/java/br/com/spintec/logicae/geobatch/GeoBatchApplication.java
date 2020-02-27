package br.com.spintec.logicae.geobatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableScheduling
@EnableTransactionManagement
public class GeoBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoBatchApplication.class, args);
	}

}
