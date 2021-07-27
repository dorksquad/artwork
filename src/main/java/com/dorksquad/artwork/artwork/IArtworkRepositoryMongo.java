package com.dorksquad.artwork.artwork;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IArtworkRepositoryMongo extends MongoRepository<Artwork, String> {

    Artwork findByName(String name);

    Artwork findByAlbum(String album);


}
