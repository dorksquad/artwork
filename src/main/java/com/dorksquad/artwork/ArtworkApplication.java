package com.dorksquad.artwork;


import com.dorksquad.artwork.artwork.ArtworkService;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.apache.logging.log4j.Logger;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ArtworkApplication
{
	/**
	 * This is the entry point for spring-boot to create the application
	 * @param args
	 */

	private static final Logger log = LogManager.getLogger(ArtworkService.class);

	public static void main(String[] args) {
		log.debug("Hello this is a debug message");
		log.info("Hello this is an info message");
		SpringApplication.run(ArtworkApplication.class, args);
	}

}
