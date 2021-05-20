package com.dorksquad.artwork;

import com.dorksquad.artwork.datastore.IArtworkRepositoryMongo;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtworkApplication implements CommandLineRunner {

	@Autowired
	private IArtworkRepositoryMongo repository;

	public static void main(String[] args) {
		SpringApplication.run(ArtworkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Artwork("farm", "what now", new Binary("farm".getBytes())));
		repository.save(new Artwork("berlin", new Binary("berlin".getBytes())));

		// fetch all customers
		System.out.println("Artworks found with findAll():");
		System.out.println("-------------------------------");
		for (Artwork artwork : repository.findAll()) {
			System.out.println(artwork);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Artwork found with findByAlbum('what now'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByAlbum("what now"));
	}
}
