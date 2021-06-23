package com.dorksquad.artwork;

import com.dorksquad.artwork.artwork.Artwork;
import com.dorksquad.artwork.artwork.IArtworkRepositoryMongo;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ArtworkApplication implements CommandLineRunner
{

	@Autowired
	private IArtworkRepositoryMongo repository;

	public static void main(String[] args) {
		SpringApplication.run(ArtworkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
