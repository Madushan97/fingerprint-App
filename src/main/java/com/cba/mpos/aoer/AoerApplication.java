package com.cba.mpos.aoer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class AoerApplication {

	private static final String DEFAULT_TIME_ZONE = "Asia/Colombo";

	@Value("${spring.application.timeZone}")
	private String timeZone;

	public static void main(String[] args) {
		SpringApplication.run(AoerApplication.class, args);
	}

	@PostConstruct
	void started() {
		ZoneId zoneId = ZoneId.of(timeZone.isEmpty() ? DEFAULT_TIME_ZONE : timeZone);
		TimeZone.setDefault(TimeZone.getTimeZone(zoneId));
	}
}
