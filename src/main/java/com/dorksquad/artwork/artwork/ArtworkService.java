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
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtworkService {

    @Autowired
    private IArtworkRepositoryMongo artworkRepo;

    private static final Logger logger = LogManager.getLogger(ArtworkService.class);


    public String addArtwork(String name, String album, MultipartFile image) throws IOException {
        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, image.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);
        artworkRepo.insert(artwork);
        logger.debug("add artwork was called");
        return artwork.getName();
    }

    public List<Artwork> getArtwork(String sort,String name, String album){

        if(name == null && album == null){
            return (sort == null) ? artworkRepo.findAll() : artworkRepo.findAll(Sort.by(sort).ascending());
        }


        List<Artwork> artworks = new ArrayList<>();

        artworks.add( (name != null) ? artworkRepo.findByName(name) : artworkRepo.findByAlbum(album));

        return artworks;
    }

//    public Artwork getArtworkByName(String name) {
//        return artworkRepo.findByName(name);
//    }
//
//    public List<Artwork> getArtworks() {
//        return artworkRepo.findAll();
//    }
//
//    public List<Artwork> getArtworks(String sort) {
//        return artworkRepo.findAll(Sort.by(sort).ascending());
//    }
//
//    public Artwork getArtworkByAlbum(String album) {
//        return artworkRepo.findByAlbum(album);
//    }
}
