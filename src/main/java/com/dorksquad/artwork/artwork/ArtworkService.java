package com.dorksquad.artwork.artwork;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

@Service
public class ArtworkService {

    @Autowired
    private IArtworkRepositoryMongo artworkRepo;

    public String addArtwork(String name, String album, MultipartFile image) throws IOException {
        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, image.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);
        artworkRepo.insert(artwork);
        
        return artwork.getName();
    }

    public Artwork getArtworkByName(String name) {
        return artworkRepo.findByName(name);
    }

    public List<Artwork> getArtworks() {
        return artworkRepo.findAll();
    }

    public List<Artwork> getArtworks(String sort) {
        return artworkRepo.findAll(Sort.by(sort).ascending());
    }

    public Artwork getArtworkByAlbum(String album) {
        return artworkRepo.findByAlbum(album);
    }
}
