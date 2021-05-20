package com.dorksquad.artwork.datastore;

import com.dorksquad.artwork.Artwork;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IArtworkRepositoryMongo extends MongoRepository<Artwork, String> {

    public Artwork findByAlbum(String album);

}
