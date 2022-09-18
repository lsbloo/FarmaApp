package com.farmawebservice.delivery;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.Banner;
@EnableJpaRepositories
@EnableFeignClients
@SpringBootApplication
@Configuration
public class DeliveryApplication {

	public static void main(String[] args) {
		String bannerLocationProperty = SpringApplication.BANNER_LOCATION_PROPERTY;
		SpringApplication.run(DeliveryApplication.class, args);
	}

	@Bean
	public FlywayMigrationStrategy cleanMigrateStrategy() {
		FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
			@Override
			public void migrate(Flyway flyway) {
				flyway.repair();
				flyway.migrate();
			}
		};
		return strategy;
	}
}
