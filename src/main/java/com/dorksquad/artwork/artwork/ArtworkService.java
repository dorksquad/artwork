package com.dorksquad.artwork.artwork;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArtworkService
{

    @Autowired
    private IArtworkRepositoryMongo artworkRepo;

    public String addArtwork(String name, String album, MultipartFile file) throws IOException
    {
        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);
        artwork = artworkRepo.insert(artwork);
        return artwork.getName();
    }

    public Artwork getArtworkByName(String name)
    {
        return artworkRepo.findByName(name);
    }

    public Artwork getArtworkByAlbum(String album)
    {
        return artworkRepo.findByAlbum(album);
    }
}
