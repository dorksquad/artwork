package com.dorksquad.artwork.artwork;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@Service
public class ArtworkService {

    @Autowired
    private IArtworkRepositoryMongo artworkRepo;

    private static final Logger logger = LogManager.getLogger(ArtworkService.class);


    public String addArtwork(String name, String album, MultipartFile image) throws IOException {
        logger.debug("ArtworkService: addArtwork was called. Calling MongoRepository to insert artwork...");
        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, image.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);
        artworkRepo.insert(artwork);
        return artwork.getName();
    }

    public Artwork getArtworkByName(String name) {
        logger.debug(String.format("ArtworkService: getArtworkByName was called. " +
                "Calling MongoRepository to find an artwork with name='%s'...", name));
        return artworkRepo.findByName(name);
    }

    public List<Artwork> getArtworks() {
        logger.debug("ArtworkService: getArtworks with not sort was called. " +
                "Calling MongoRepository to get artworks...");
        return artworkRepo.findAll();
    }

    public List<Artwork> getArtworks(String sort) {
        logger.debug(String.format("ArtworkService: getArtworks with sort='%s' was called. " +
                "Calling MongoRepository to get sorted artworks...", sort));
        return artworkRepo.findAll(Sort.by(sort).ascending());
    }

    public Artwork getArtworkByAlbum(String album) {
        logger.debug(String.format("ArtworkService: getArtworkByAlbum was called. " +
                "Calling MongoRepository to find an artwork with album='%s'...", album));
        return artworkRepo.findByAlbum(album);
    }
}
